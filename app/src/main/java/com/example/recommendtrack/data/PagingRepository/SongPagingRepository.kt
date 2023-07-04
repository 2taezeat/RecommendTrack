package com.example.recommendtrack.data.PagingRepository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recommendtrack.data.datasource.SongRemoteDataSource
import com.example.recommendtrack.data.mapper.ErrorEnvelopeMapper
import com.example.recommendtrack.data.mapper.SearchMetaMapper
import com.example.recommendtrack.data.mapper.SongMapper
import com.example.recommendtrack.domain.entity.Song
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class SongPagingRepository @Inject constructor(
    private val dataSource: SongRemoteDataSource,
    private val accessToken: String,
    private val songName: String,
    private val onError: (String) -> Unit
) : PagingSource<Int, Song>() {

    override fun getRefreshKey(state: PagingState<Int, Song>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
        return try {
            val currentPageNumber = params.key ?: STARTING_PAGE_INDEX
            var songs = listOf<Song>()

            val prevKey = if (currentPageNumber == STARTING_PAGE_INDEX) null else currentPageNumber - 1
            var nextKey : Int? = currentPageNumber + 1
            val offset = (currentPageNumber - 1) * 10

            val response = dataSource.searchSongs(
                accessToken = accessToken,
                songName = songName,
                limit = SEARCH_LIMIT,
                offset = offset
            )

            response.suspendOnSuccess(SearchMetaMapper) {
                if (this.next == null) { nextKey = null }
            }


            response.suspendOnSuccess(SongMapper) {
                songs = this
                Timber.d("hello123, ${songs }")

            }.suspendOnFailure {
                onError(this.message())
            }.suspendOnError(ErrorEnvelopeMapper) {
                val errorMessage = this.message
                Timber.tag("error").d("$errorMessage")
            }

            Timber.d("pagingNum: ${currentPageNumber}, ${ prevKey }, ${nextKey}")

            LoadResult.Page(
                data = songs,
                prevKey = prevKey,
                nextKey = nextKey,
            )

        } catch (exception: IOException) {
            onError("IOException")
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            onError("HttpException")
            LoadResult.Error(exception)
        }


    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val SEARCH_LIMIT = 10
    }

}
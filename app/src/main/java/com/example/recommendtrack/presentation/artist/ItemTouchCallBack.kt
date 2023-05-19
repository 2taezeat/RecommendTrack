package com.example.recommendtrack.presentation.artist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recommendtrack.R

class ItemTouchCallback(val context: Context, private val listener: ItemTouchHelperListener): ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.LEFT

        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val myArtistViewHolder = viewHolder as MyArtistViewHolder
        myArtistViewHolder.itemMyArtistBinding.myArtistFavoriteIV.callOnClick()
    }

    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val icon: Bitmap
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val itemView = viewHolder.itemView
            val height = (itemView.bottom - itemView.top).toFloat()
            val width = height / 4
            val paint = Paint()
            if (dX < 0) {  // 왼쪽으로 스와이프하는지 확인
                // ViewHolder의 백그라운드에 깔아줄 사각형의 크기와 색상을 지정
                paint.color = Color.parseColor("#4DE91E63")
                val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                c.drawRect(background, paint)

                // 휴지통 아이콘과 표시될 위치를 지정하고 비트맵을 그려줌
                // 비트맵 이미지는 Image Asset 기능으로 추가하고 drawable 폴더에 위치하도록 함
                icon = BitmapFactory.decodeResource(viewHolder.itemView.resources, R.drawable.ic_delete)
                val iconDst = RectF(itemView.right.toFloat() - 3 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                c.drawBitmap(icon, null, iconDst, null)
            }
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}



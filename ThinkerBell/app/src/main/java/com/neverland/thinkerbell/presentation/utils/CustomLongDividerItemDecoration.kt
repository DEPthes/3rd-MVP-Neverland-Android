package com.neverland.thinkerbell.presentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R

class CustomLongDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val paint: Paint = Paint().apply {
        color = context.getColor(R.color.content_tertiary)
        strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 1f, context.resources.displayMetrics
        )
    }
    private val dividerWidth: Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 380f, context.resources.displayMetrics
    ).toInt()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = (parent.width - dividerWidth) / 2f
        val right = left + dividerWidth

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.top - params.topMargin - paint.strokeWidth).toFloat()
            val bottom = top + paint.strokeWidth

            // 첫 번째 아이템 위에 구분선을 추가
            if (i == 0) {
                c.drawRect(left, top, right, bottom, paint)
            }

            // 각 아이템 아래에 구분선을 추가
            val itemBottom = (child.bottom + params.bottomMargin).toFloat()
            val itemBottomEnd = itemBottom + paint.strokeWidth
            c.drawRect(left, itemBottom, right, itemBottomEnd, paint)
        }
    }

    override fun getItemOffsets(outRect: android.graphics.Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.top = paint.strokeWidth.toInt() // 첫 번째 아이템 위에 구분선을 그릴 공간을 추가
        }
        outRect.bottom = paint.strokeWidth.toInt() // 각 아이템 아래에 구분선을 그릴 공간을 추가
    }
}

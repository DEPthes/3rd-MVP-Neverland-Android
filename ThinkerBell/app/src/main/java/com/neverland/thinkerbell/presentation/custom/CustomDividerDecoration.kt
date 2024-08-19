package com.neverland.thinkerbell.presentation.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomDividerDecoration(
    context: Context,
    private val colorHex: String, // Divider 색상 Hex 코드
    private val thicknessDp: Float // Divider 두께(dp)
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()
    private val thicknessPx: Int

    init {
        paint.color = android.graphics.Color.parseColor(colorHex)
        paint.style = Paint.Style.FILL
        // dp를 px로 변환
        thicknessPx = (context.resources.displayMetrics.density * thicknessDp).toInt()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + thicknessPx

            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(0, 0, 0, thicknessPx) // 밑줄의 두께만큼 아래쪽에 패딩 추가
    }
}

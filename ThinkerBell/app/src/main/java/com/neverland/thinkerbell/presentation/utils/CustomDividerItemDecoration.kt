package com.neverland.thinkerbell.presentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R

class CustomDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val divider: Drawable?
    private val dividerWidth: Int

    init {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.listDivider, typedValue, true)
        divider = ContextCompat.getDrawable(context, R.drawable.category_divider_home)
        dividerWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 274f, context.resources.displayMetrics
        ).toInt()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = (parent.width - dividerWidth) / 2
        val right = left + dividerWidth

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + (divider?.intrinsicHeight ?: 0)

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
        }
    }
}

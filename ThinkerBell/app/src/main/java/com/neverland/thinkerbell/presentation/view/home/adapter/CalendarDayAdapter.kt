package com.neverland.thinkerbell.presentation.view.home.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.domain.model.MjuSchedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarDayAdapter(
    val tempMonth: Int,
    val dayList: MutableList<Date>,
    val scheduleList: List<MjuSchedule>
) : RecyclerView.Adapter<CalendarDayAdapter.DayView>() {

    class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    private val dateFormat = SimpleDateFormat("MM.dd", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        // 초기화
        val dayText: TextView = holder.layout.findViewById(R.id.tv_day)
        val date = dayList[position]

        // 날짜 표시
        dayText.text = date.date.toString()
        if (tempMonth != date.month) {
            dayText.alpha = 0f
        } else {
            dayText.alpha = 1f
        }

        // 일정이 있는 날짜 강조
        val dateString = dateFormat.format(date)
        val scheduleForTheDay = scheduleList.find { it.date == dateString }
        if (scheduleForTheDay != null) {
            dayText.setTextColor(holder.layout.context.getColor(R.color.primary1))
            TextViewCompat.setTextAppearance(dayText, R.style.Label_Small)
            dayText.setTypeface(dayText.typeface, Typeface.BOLD)
        } else {
            dayText.setTextColor(holder.layout.context.getColor(R.color.content_secondary))
        }
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}

package com.neverland.thinkerbell.presentation.view.home.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.databinding.ItemCalendarDayBinding
import com.neverland.thinkerbell.domain.model.univ.AcademicSchedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarDayAdapter(
    val tempMonth: Int,
    val dayList: MutableList<Date>,
    val scheduleList: List<AcademicSchedule>
) : RecyclerView.Adapter<CalendarDayAdapter.DayView>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    inner class DayView(val binding: ItemCalendarDayBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(date: Date){
            if(dateFormat.format(date) == dateFormat.format(Date())){
                binding.ivDot.visibility = View.VISIBLE
            } else {
                binding.ivDot.visibility = View.INVISIBLE
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        val binding = ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayView(binding)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        // 초기화

        val date = dayList[position]

        // 날짜 표시
        holder.binding.tvDay.text = date.date.toString()
        if (tempMonth != date.month) {
            holder.binding.tvDay.alpha = 0f
        } else {
            holder.binding.tvDay.alpha = 1f
        }

        // 일정이 있는 날짜 강조
        val dateString = dateFormat.format(date)
        val scheduleForTheDay = scheduleList.find { it.startDate == dateString }
        if (scheduleForTheDay != null) {
            holder.binding.tvDay.setTextColor(holder.itemView.context.getColor(R.color.primary1))
            TextViewCompat.setTextAppearance(holder.binding.tvDay, R.style.Label_Small)
            holder.binding.tvDay.setTypeface(holder.binding.tvDay.typeface, Typeface.BOLD)
        } else {
            holder.binding.tvDay.setTextColor(holder.itemView.context.getColor(R.color.content_secondary))
        }

        holder.bind(date)
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}

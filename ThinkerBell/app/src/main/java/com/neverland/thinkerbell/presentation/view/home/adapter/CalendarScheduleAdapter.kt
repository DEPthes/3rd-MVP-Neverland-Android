package com.neverland.thinkerbell.presentation.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.domain.model.univ.AcademicSchedule

class CalendarScheduleAdapter : RecyclerView.Adapter<CalendarScheduleAdapter.ScheduleViewHolder>() {

    private var schedules: List<AcademicSchedule> = listOf()

    fun updateSchedules(newSchedules: List<AcademicSchedule>) {
        schedules = newSchedules
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_schedule, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules[position]
        holder.scheduleDate.text = schedule.startDate
        holder.scheduleTitle.text = schedule.title
//        holder.favoriteButton.setImageResource(
//            if (schedule.isFavorite) R.drawable.ic_clipping_filled else R.drawable.ic_clipping_outline
//        )
//
//        holder.favoriteButton.setOnClickListener {
//            schedule.isFavorite = !schedule.isFavorite
//            notifyItemChanged(position)
//        }
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scheduleDate: TextView = itemView.findViewById(R.id.tv_schedule_date)
        val scheduleTitle: TextView = itemView.findViewById(R.id.tv_schedule_title)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.btn_favorite)
    }
}

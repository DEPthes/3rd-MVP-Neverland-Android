package com.neverland.thinkerbell.presentation.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.domain.model.univ.AcademicSchedule
import java.util.Calendar
import java.util.Date

class CalendarMonthAdapter(
    private var scheduleList: List<AcademicSchedule>,
    private val onMonthChange: (Int) -> Unit
) : RecyclerView.Adapter<CalendarMonthAdapter.Month>() {
    val baseCalendar: Calendar = Calendar.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Month {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_month, parent, false)
        return Month(view)
    }

    override fun onBindViewHolder(holder: Month, position: Int) {
        // 리사이클러뷰 초기화
        val listLayout: RecyclerView = holder.view.findViewById(R.id.rv_month)

        // 달 구하기
        val calendar = baseCalendar.clone() as Calendar
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.MONTH, position - Int.MAX_VALUE / 2) // 정확한 월 계산

        // title 텍스트 초기화
        val titleText: TextView = holder.view.findViewById(R.id.tv_month)
        titleText.text = "${calendar.get(Calendar.MONTH) + 1}월"

        val tempMonth = calendar.get(Calendar.MONTH)

        // 일 구하기
        val dayList: MutableList<Date> = MutableList(6 * 7) { Date() }
        for (i in 0..5) {
            for (k in 0..6) {
                calendar.set(Calendar.DAY_OF_WEEK, k + 1)
                dayList[i * 7 + k] = calendar.time
            }
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
        }

        listLayout.layoutManager = GridLayoutManager(holder.view.context, 7)
        listLayout.adapter = CalendarDayAdapter(tempMonth, dayList, scheduleList)
        // 화살표 버튼 클릭 이벤트 처리
        val btnPrevMonth: ImageButton = holder.view.findViewById(R.id.btn_prev_month)
        val btnNextMonth: ImageButton = holder.view.findViewById(R.id.btn_next_month)

        btnPrevMonth.setOnClickListener {
            if (position > 0) {
                onMonthChange(position - 1)
            }
        }

        btnNextMonth.setOnClickListener {
            onMonthChange(position + 1)
        }
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    fun setData(newList: List<AcademicSchedule>) {
        scheduleList = newList
        notifyDataSetChanged()
    }

    class Month(val view: View) : RecyclerView.ViewHolder(view)
}

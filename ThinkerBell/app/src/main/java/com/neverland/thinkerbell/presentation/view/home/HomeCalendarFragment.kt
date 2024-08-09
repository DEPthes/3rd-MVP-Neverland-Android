package com.neverland.thinkerbell.presentation.view.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentHomeCalendarBinding
import com.neverland.thinkerbell.domain.model.MjuSchedule
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.CustomLongDividerItemDecoration
import com.neverland.thinkerbell.presentation.view.home.adapter.CalendarMonthAdapter
import com.neverland.thinkerbell.presentation.view.home.adapter.CalendarScheduleAdapter
import java.util.Calendar

class HomeCalendarFragment : BaseFragment<FragmentHomeCalendarBinding>(R.layout.fragment_home_calendar) {
    private lateinit var monthAdapter: CalendarMonthAdapter
    private lateinit var scheduleAdapter: CalendarScheduleAdapter

    private val scheduleList = listOf(
        MjuSchedule("08.06", "학사 일정 1", false),
        MjuSchedule("08.09", "학사 일정 2", false),
        MjuSchedule("08.11", "학사 일정 3", true),
        MjuSchedule("07.18", "학사 일정 3", true),
        MjuSchedule("09.18", "학사 일정 3", true),
        MjuSchedule("06.18", "학사 일정 3", true)
    )

    override fun initView() {
        setCalendarRv()
        setScheduleRv()
    }

    private fun setScheduleRv() {
        binding.rvSchedule.layoutManager = LinearLayoutManager(context)
        scheduleAdapter = CalendarScheduleAdapter()
        binding.rvSchedule.adapter = scheduleAdapter

        binding.rvSchedule.addItemDecoration(CustomLongDividerItemDecoration(requireContext()))

        val position = Int.MAX_VALUE / 2
        updateSchedule(position)
    }

    private fun setCalendarRv() {
        monthAdapter = CalendarMonthAdapter(scheduleList) { newPosition ->
            binding.rvCalendar.smoothScrollToPosition(newPosition)
            updateSchedule(newPosition)
        }
        binding.rvCalendar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCalendar.adapter = monthAdapter

        val position = Int.MAX_VALUE / 2
        binding.rvCalendar.scrollToPosition(position)

        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.rvCalendar)

        binding.rvCalendar.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (visiblePosition != RecyclerView.NO_POSITION) {
                        updateSchedule(visiblePosition)
                    }
                }
            }
        })
    }

    private fun updateSchedule(position: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, position - Int.MAX_VALUE / 2)
        val month = calendar.get(Calendar.MONTH) + 1

        val filteredSchedules = scheduleList.filter { it.date.startsWith(String.format("%02d", month)) }
        scheduleAdapter.updateSchedules(filteredSchedules)
    }
}

package com.neverland.thinkerbell.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.FragmentHomeCalendarBinding
import com.neverland.thinkerbell.domain.model.MjuSchedule
import com.neverland.thinkerbell.presentation.adapter.CalendarMonthAdapter
import com.neverland.thinkerbell.presentation.adapter.CalendarScheduleAdapter
import java.util.Calendar

class HomeCalendarFragment : Fragment() {

    private var _binding: FragmentHomeCalendarBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var monthAdapter: CalendarMonthAdapter
    private lateinit var scheduleAdapter: CalendarScheduleAdapter

    private val scheduleList = listOf(
        MjuSchedule("07.06", "학사 일정 1", false),
        MjuSchedule("07.09", "학사 일정 2", false),
        MjuSchedule("07.18", "학사 일정 3", true)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        recyclerView = binding.rvCalendar
        monthAdapter = CalendarMonthAdapter(scheduleList) { newPosition ->
            recyclerView.smoothScrollToPosition(newPosition)
            updateSchedule(newPosition)
        }
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = monthAdapter

        // item의 위치 지정
        val position = Int.MAX_VALUE / 2
        recyclerView.scrollToPosition(position)

        // 항목씩 스크롤 지정
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(recyclerView)

        // 학사 일정 리사이클러뷰 초기화
        binding.rvSchedule.layoutManager = LinearLayoutManager(context)
        scheduleAdapter = CalendarScheduleAdapter()
        binding.rvSchedule.adapter = scheduleAdapter

        // 구분선 추가
        binding.rvSchedule.addItemDecoration(CustomLongDividerItemDecoration(requireContext()))

        updateSchedule(position)
    }

    private fun updateSchedule(position: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, position - Int.MAX_VALUE / 2)
        val month = calendar.get(Calendar.MONTH) + 1

        val filteredSchedules = scheduleList.filter { it.date.startsWith(String.format("%02d", month)) }
        scheduleAdapter.updateSchedules(filteredSchedules)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

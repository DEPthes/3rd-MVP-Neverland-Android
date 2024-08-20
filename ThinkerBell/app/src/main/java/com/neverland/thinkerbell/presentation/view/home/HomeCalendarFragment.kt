package com.neverland.thinkerbell.presentation.view.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentHomeCalendarBinding
import com.neverland.thinkerbell.domain.model.univ.AcademicSchedule
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.CustomLongDividerItemDecoration
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import com.neverland.thinkerbell.presentation.view.home.adapter.CalendarMonthAdapter
import com.neverland.thinkerbell.presentation.view.home.adapter.CalendarScheduleAdapter
import java.util.Calendar

class HomeCalendarFragment : BaseFragment<FragmentHomeCalendarBinding>(R.layout.fragment_home_calendar) {
    private val viewModel: HomeCalendarViewModel by viewModels()
    private lateinit var monthAdapter: CalendarMonthAdapter
    private lateinit var scheduleAdapter: CalendarScheduleAdapter

    override fun initView() {
        fetchSchedulesForCurrentMonth()
        setCalendarRv()
        setScheduleRv()
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // Handle loading state if necessary
                }
                is UiState.Success -> {
                    // 성공 상태에서만 업데이트 수행
                    monthAdapter.setData(state.data)
                    updateSchedules(state.data)
                }
                is UiState.Error -> {
                    // Handle error state if necessary
                }
                UiState.Empty -> {
                    // Handle empty state if necessary
                }
            }
        }

        viewModel.toastState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}
                is UiState.Error -> {}
                is UiState.Empty -> {}
                is UiState.Success -> {
                    showToast(state.data)
                }
            }
        }
    }

    private fun fetchSchedulesForCurrentMonth() {
        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH) + 1
        viewModel.fetchData(currentMonth)
    }

    private fun setScheduleRv() {
        binding.rvSchedule.layoutManager = LinearLayoutManager(context)
        scheduleAdapter = CalendarScheduleAdapter().apply {
            setRvItemClickListener(object : OnRvItemClickListener<Pair<Int, Boolean>>{
                override fun onClick(item: Pair<Int, Boolean>) {
                    if(item.second) viewModel.postBookmark(item.first) else viewModel.deleteBookmark(item.first)
                }
            })
        }
        binding.rvSchedule.adapter = scheduleAdapter

        binding.rvSchedule.addItemDecoration(CustomLongDividerItemDecoration(requireContext()))

        val position = Int.MAX_VALUE / 2
        updateSchedule(position)
    }

    private fun setCalendarRv() {
        monthAdapter = CalendarMonthAdapter(emptyList()) { newPosition ->
            binding.rvCalendar.scrollToPosition(newPosition)
            val calendar = monthAdapter.baseCalendar.clone() as Calendar
            calendar.add(Calendar.MONTH, newPosition - Int.MAX_VALUE / 2)
            val month = calendar.get(Calendar.MONTH) + 1
            viewModel.fetchData(month)
        }
        binding.rvCalendar.layoutManager = object : LinearLayoutManager(context, HORIZONTAL, false) {
            override fun canScrollHorizontally(): Boolean = false
        }

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

    private fun updateSchedules(schedules: List<AcademicSchedule>) {
        scheduleAdapter.updateSchedules(schedules)
    }

    private fun updateSchedule(position: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, position - Int.MAX_VALUE / 2)
        val month = calendar.get(Calendar.MONTH) + 1

        val filteredSchedules = when (val state = viewModel.uiState.value) {
            is UiState.Success -> state.data.filter { it.startDate.startsWith(String.format("%02d", month)) }
            else -> emptyList() // 성공 상태가 아니면 빈 리스트 반환
        }
        scheduleAdapter.updateSchedules(filteredSchedules)
    }
}

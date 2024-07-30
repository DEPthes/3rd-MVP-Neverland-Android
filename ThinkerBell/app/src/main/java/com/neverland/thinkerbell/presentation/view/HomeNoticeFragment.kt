package com.neverland.thinkerbell.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.databinding.FragmentHomeNoticeBinding
import com.neverland.thinkerbell.domain.model.Notice
import com.neverland.thinkerbell.presentation.adapter.HomeNoticeAdapter


class HomeNoticeFragment : Fragment() {

    companion object {
        private const val ARG_NOTICES = "notices"

        fun newInstance(notices: List<Notice>): HomeNoticeFragment {
            val fragment = HomeNoticeFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_NOTICES, ArrayList(notices))
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentHomeNoticeBinding
    private lateinit var noticeList: List<Notice>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            noticeList = it.getParcelableArrayList(ARG_NOTICES) ?: listOf()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNoticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHomeNotice.layoutManager = LinearLayoutManager(context)
        binding.rvHomeNotice.adapter = HomeNoticeAdapter(noticeList)

        // 커스텀 구분선 추가
        val customDividerItemDecoration = CustomDividerItemDecoration(requireContext())
        binding.rvHomeNotice.addItemDecoration(customDividerItemDecoration)
    }
}
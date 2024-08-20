package com.neverland.thinkerbell.presentation.view.myPage

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentKeywordAddBinding
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import java.util.regex.Pattern

class KeywordAddFragment: BaseFragment<FragmentKeywordAddBinding>(R.layout.fragment_keyword_add) {

    private val viewModel: MyPageViewModel by viewModels()

    override fun initView() {
        (requireActivity() as HomeActivity).apply {
            setStatusBarColor(R.color.primary2, false)
            hideBottomNavigation()
        }

        // 천지인 키보드를 고려하여 자음, 모음, 숫자, 공백만 허용
        val koreanPattern = Pattern.compile("[ㄱ-ㅎ가-힣ㅏ-ㅣa-zA-Z0-9 ]")

        binding.etKeywordAdd.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                val char = source[i].toString()
                // 특수문자 필터링
                if (!koreanPattern.matcher(char).matches()) {
                    return@InputFilter ""
                }
            }
            null
        })
    }

    override fun setObserver() {
        super.setObserver()
        viewModel.postState.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Loading -> {
                    // Handle loading state
                }

                is UiState.Success -> {
                    showToast("키워드 등록 성공")
                    binding.etKeywordAdd.text.clear()
                }

                is UiState.Error -> {
                    showToast("키워드 등록 실패")
                }

                UiState.Empty -> {

                }
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.etKeywordAdd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if(s.length >= 2) {
                        binding.tvMsg.text = "2~9글자, 특수기호/이모지 X"
                        binding.tvMsg.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_gray_800))
                    }
                }
            }
        })

        binding.btnKeywordAdd.setOnClickListener {
            if(binding.etKeywordAdd.text.length >= 2) {
                viewModel.postKeyword(binding.etKeywordAdd.text.toString())
            } else {
                binding.tvMsg.text = "두 글자 이상 입력해주세요."
                binding.tvMsg.setTextColor(ContextCompat.getColor(requireContext(), R.color.negative))
            }
        }

        val chipClickListener = OnClickListener { view ->
            // 각 chip TextView의 클릭 동작 정의
            val chipText = (view as? TextView)?.text.toString()
            binding.etKeywordAdd.setText(chipText)
        }

        // chip1부터 chip9까지의 TextView에 클릭 리스너 추가
        val chipIds = arrayOf(
            binding.chip1,
            binding.chip2,
            binding.chip3,
            binding.chip4,
            binding.chip5,
            binding.chip6,
            binding.chip7,
            binding.chip8,
            binding.chip9
        )

        for (chip in chipIds) {
            chip.setOnClickListener(chipClickListener)
        }
    }
}
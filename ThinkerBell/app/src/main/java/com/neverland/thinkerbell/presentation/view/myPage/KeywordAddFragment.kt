package com.neverland.thinkerbell.presentation.view.myPage

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
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

        val koreanPattern = Pattern.compile("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$")

        binding.etKeywordAdd.filters = arrayOf(
            InputFilter { source, start, end, dest, dstart, dend ->
                for (i in start until end) {
                    val char = source[i].toString()
                    if (!koreanPattern.matcher(char).matches()) {
                        return@InputFilter ""
                    }
                }
                null
            },
            LengthFilter(9)
        )
    }

    override fun setObserver() {
        super.setObserver()
        viewModel.postState.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    showToast("키워드 등록 성공")
                    binding.etKeywordAdd.text.clear()
                    requireActivity().supportFragmentManager.popBackStack()
                }
                is UiState.Error -> {
                    showToast(it.exception.message.toString())
                    if(it.exception.message.toString() == "해당 키워드는 존재하지 않습니다.") {
                        binding.tvMsg.text = "키워드를 정확하게 입력해주세요."
                        binding.tvMsg.setTextColor(ContextCompat.getColor(requireContext(), R.color.negative))
                    }
                }
                UiState.Empty -> {}
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.etKeywordAdd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    binding.tvMsg.text = ""

                    if(s.isEmpty()){
                        binding.btnKeywordAdd.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_gray_100))
                        binding.btnKeywordAdd.background = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.red_gray_300))
                    } else {
                        binding.btnKeywordAdd.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary2))
                        binding.btnKeywordAdd.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_keyword_add_button_bg)
                    }
                }
            }
        })

        binding.btnKeywordAdd.setOnClickListener {
            if(binding.btnKeywordAdd.currentTextColor != ContextCompat.getColor(requireContext(), R.color.primary2)) return@setOnClickListener

            if (binding.etKeywordAdd.text.length >= 2) {
                if (containsChosungOrVowel(binding.etKeywordAdd.text.toString())) {
                    binding.tvMsg.text = "키워드를 정확하게 입력해주세요."
                    binding.tvMsg.setTextColor(ContextCompat.getColor(requireContext(), R.color.negative))
                } else {
                    viewModel.postKeyword(binding.etKeywordAdd.text.toString())
                }
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

    private fun containsChosungOrVowel(input: String): Boolean {
        // 초성 (받침)
        val chosung = setOf('ㄱ', 'ㄴ', 'ㄷ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')
        // 모음
        val vowels = setOf('ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ')

        for (char in input) {
            if (char in chosung || char in vowels) {
                return true
            }
        }
        return false
    }
}
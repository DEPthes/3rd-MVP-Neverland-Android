package com.neverland.thinkerbell.presentation.view.myPage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.neverland.thinkerbell.databinding.DialogKeywordDeleteBinding
import com.neverland.thinkerbell.presentation.utils.DisplayUtils

class KeywordDeleteDialog(
    private val keyword: String,
    private val onDeleteKeyword: () -> Unit
) : DialogFragment() {

    companion object {
        fun newInstance(keyword: String, onDeleteKeyword: () -> Unit): KeywordDeleteDialog {
            return KeywordDeleteDialog(keyword, onDeleteKeyword)
        }
    }

    private var mBinding: DialogKeywordDeleteBinding? = null
    private val binding get() = mBinding!!

    override fun onStart() {
        super.onStart()

        dialog?.let {
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val widthInDp = 304f
            val heightInDp = 162f

            val widthInPx = DisplayUtils.dpToPx(requireContext(), widthInDp).toInt()
            val heightInPx = DisplayUtils.dpToPx(requireContext(), heightInDp).toInt()

            it.window?.setLayout(widthInPx, heightInPx)
        }

        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DialogKeywordDeleteBinding.inflate(requireActivity().layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDialogTitle.text = "'$keyword' 을(를)\n삭제하시겠습니까?"

        binding.btnDialogDelete.setOnClickListener {
            onDeleteKeyword.invoke()
            dismiss()
        }

        binding.btnDialogCancel.setOnClickListener {
            dismiss()
        }
    }
}


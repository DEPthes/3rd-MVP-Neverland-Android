package com.neverland.thinkerbell.presentation.view.contact

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.neverland.thinkerbell.databinding.DialogContactBinding
import com.neverland.thinkerbell.domain.model.univ.DeptContact
import com.neverland.thinkerbell.presentation.custom.CustomToast
import com.neverland.thinkerbell.presentation.utils.DisplayUtils

class ContactDialog(
    private val contactItem: DeptContact
) : DialogFragment() {

    companion object {
        fun newInstance(contactItem: DeptContact): ContactDialog {
            return ContactDialog(contactItem)
        }
    }

    private var mBinding: DialogContactBinding? = null
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

        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DialogContactBinding.inflate(requireActivity().layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDialogContactTitle.text = contactItem.major
        binding.tvDialogContactContact.text = contactItem.contact
        binding.btnDialogContactCopy.setOnClickListener { copyToClipboard() }
        binding.btnDialogContactCall.setOnClickListener { callPhone() }
    }

    private fun copyToClipboard() {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clip = ClipData.newPlainText("${contactItem.major} 연락처", contactItem.contact)
        clipboard.setPrimaryClip(clip)
        CustomToast.makeToast(requireContext(), "클립보드에 복사되었습니다.")
    }

    private fun callPhone(){
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${contactItem.contact.replace("-", "")}")
        }

        startActivity(intent)
    }
}

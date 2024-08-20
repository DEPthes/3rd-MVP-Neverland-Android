package com.neverland.thinkerbell.presentation.view.contact

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentContactsBinding
import com.neverland.thinkerbell.domain.model.group.Group
import com.neverland.thinkerbell.domain.model.univ.DeptContact
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.custom.CustomDividerDecoration
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import com.neverland.thinkerbell.presentation.view.home.HomeActivity

class ContactsFragment: BaseFragment<FragmentContactsBinding>(R.layout.fragment_contacts) {
    private val viewModel: ContactsViewModel by viewModels()
    private lateinit var expandableAdapter: ContactsExpandableAdapter

    override fun initView() {
        (requireActivity() as HomeActivity).apply {
            showBottomNavigation()
            setStatusBarColor(R.color.primary1, true)
        }

        viewModel.fetchData()
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {}
                is UiState.Empty -> {}
                is UiState.Error -> {}
                is UiState.Success -> {
                    setRvAdapter(it.data)
                }
            }
        }
    }

    private fun setRvAdapter(groups: List<Group<DeptContact>>){
        expandableAdapter = ContactsExpandableAdapter(groups).apply {
            setOnRvItemClickListener(object : OnRvItemClickListener<DeptContact>{
                override fun onClick(item: DeptContact) {
                    ContactDialog.newInstance(item).show(requireActivity().supportFragmentManager, "")
                }
            })
        }
        binding.rvContacts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContacts.addItemDecoration(CustomDividerDecoration(requireContext(), "#404040", 1f ))
        binding.rvContacts.adapter = expandableAdapter
    }
}
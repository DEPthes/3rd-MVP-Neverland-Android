package com.neverland.thinkerbell.presentation.view.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.data.repository.DataStoreRepositoryImpl
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.group.ContactItem
import com.neverland.thinkerbell.domain.model.group.Group
import com.neverland.thinkerbell.domain.model.group.SubGroup
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class ContactsViewModel: ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<Group<ContactItem>>>>(UiState.Loading)
    val uiState: LiveData<UiState<List<Group<ContactItem>>>> get() = _uiState


    fun fetchData() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val groups = listOf(
                Group("Group 1", listOf(
                    SubGroup("Subgroup 1-1", listOf(ContactItem("Item 1-1-1", "031-0000-0000"), ContactItem("Item 1-1-2", "031-0000-0000"))),
                    SubGroup("Subgroup 1-2", listOf(ContactItem("Item 1-2-1", "031-0000-0000"), ContactItem("Item 1-2-2", "031-0000-0000")))
                )),
                Group("Group 2", listOf(
                    SubGroup("Subgroup 2-1", listOf(ContactItem("Item 2-1-1", "031-0000-0000"), ContactItem("Item 2-1-2", "031-0000-0000"))),
                    SubGroup("Subgroup 2-2", listOf(ContactItem("Item 2-2-1", "031-0000-0000"), ContactItem("Item 2-2-2", "031-0000-0000")))
                ))
            )

            _uiState.value = UiState.Success(groups)
        }
    }
}
package com.neverland.thinkerbell.presentation.view.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.group.Group
import com.neverland.thinkerbell.domain.model.group.SubGroup
import com.neverland.thinkerbell.domain.model.univ.DeptContact
import com.neverland.thinkerbell.domain.usecase.univ.GetDeptContactUseCase
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {

    private val getDeptContactUseCase = GetDeptContactUseCase()

    private val _uiState = MutableLiveData<UiState<List<Group<DeptContact>>>>(UiState.Loading)
    val uiState: LiveData<UiState<List<Group<DeptContact>>>> get() = _uiState

    fun fetchData() {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            getDeptContactUseCase.invoke()
                .onSuccess { deptContacts ->
                    val groups = mutableListOf<Group<DeptContact>>()
                    deptContacts.forEach { (campus, colleges) ->
                        val subGroups = colleges.map { (college, contacts) ->
                            SubGroup(
                                name = college,
                                items = contacts.map { deptContact ->
                                    DeptContact(
                                        major = deptContact.major,
                                        contact = deptContact.contact,
                                        college = deptContact.college,
                                        campus = deptContact.campus
                                    )
                                }
                            )
                        }
                        groups.add(Group(name = campus, subGroups = subGroups))
                    }

                    _uiState.value = UiState.Success(groups)
                }
                .onFailure {
                    _uiState.value = UiState.Error(it)
                }
        }
    }
}

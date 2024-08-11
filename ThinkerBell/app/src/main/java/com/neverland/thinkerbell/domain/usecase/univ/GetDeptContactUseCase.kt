package com.neverland.thinkerbell.domain.usecase.univ

import com.neverland.thinkerbell.data.repository.UnivRepositoryImpl
import com.neverland.thinkerbell.domain.model.univ.DeptContact


class GetDeptContactUseCase() {
    private val repository: UnivRepositoryImpl = UnivRepositoryImpl()

    suspend operator fun invoke(): Result<Map<String, Map<String, List<DeptContact>>>> {
        val res = repository.getDeptContact()

        return if (res.isSuccess) {
            val deptContacts = res.getOrNull().orEmpty()

            val groupedByCampus = deptContacts.groupBy { it.campus }
            val groupedByCampusAndCollege = groupedByCampus.mapValues { (_, contacts) ->
                contacts.groupBy { it.college }
            }

            Result.success(groupedByCampusAndCollege)
        } else {
            Result.failure(res.exceptionOrNull() ?: Exception("Unknown error occurred"))
        }
    }
}
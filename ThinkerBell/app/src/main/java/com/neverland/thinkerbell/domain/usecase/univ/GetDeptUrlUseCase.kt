package com.neverland.thinkerbell.domain.usecase.univ

import com.neverland.thinkerbell.data.repository.UnivRepositoryImpl
import com.neverland.thinkerbell.domain.model.univ.DeptUrl


class GetDeptUrlUseCase() {
    private val repository: UnivRepositoryImpl = UnivRepositoryImpl()

    suspend operator fun invoke(): Result<Map<String, List<DeptUrl>>> {
        val res = repository.getDeptUrl()

        return if(res.isSuccess){
            Result.success(res.getOrNull().orEmpty().groupBy { deptUrl -> deptUrl.school })
        } else {
            Result.failure(res.exceptionOrNull() ?: Exception())
        }
    }
}
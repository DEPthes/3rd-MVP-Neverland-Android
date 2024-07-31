package com.neverland.thinkerbell.presentation.view.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class CommonNoticeViewModel: ViewModel() {
    
    private var _currentPage = MutableLiveData(1)
    val currentPage: LiveData<Int> get() = _currentPage

    var totalPage = 30
    var currentNotice = listOf<CommonNotice>()

    private val _uiState = MutableLiveData<UiState<List<CommonNotice>>>(UiState.Loading)
    val uiState: LiveData<UiState<List<CommonNotice>>> get() = _uiState

    private val _searchState = MutableLiveData<UiState<List<CommonNotice>>>(UiState.Loading)
    val searchState: LiveData<UiState<List<CommonNotice>>> get() = _searchState

    private val dummyNotice = listOf(
        CommonNotice(
            title = "[도서관] 자치위원회 46기 모집 안내",
            date = "2024.07.26",
            isImportant = true,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "[혁신사업] 8월 월간 비교과프로그램 안내",
            date = "2024.07.26",
            isImportant = true,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzElMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "[인권센터] 명지대학교 인권센터 연구원 채용 공고",
            date = "2024.07.25",
            isImportant = false,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "[명지통합치료연구센터] 2024년 2학기 돌봄 시간제 근로장학생 (아르바이트)를 모집 안내",
            date = "2024.07.25",
            isImportant = false,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "[시설관리팀] 자연캠퍼스 정전 일정 안내[2024. 8. 17.(토)]",
            date = "2024.07.25",
            isImportant = false,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "2024학년도 하계 집중휴무제 시행 안내[2024. 7. 29.(월)~8. 2.(금)]",
            date = "2024.07.25",
            isImportant = false,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "2024-2학기 대학 재학생 등록금 분할납부 안내",
            date = "2024.07.25",
            isImportant = false,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "2024-2학기 대학 재학생 등록금 납부 안내",
            date = "2024.07.25",
            isImportant = false,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "[인문캠퍼스] 2024학년도 2학기 취업서포터즈단 [명지내일 13기] 모집 안내",
            date = "2024.07.25",
            isImportant = false,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "[자연캠퍼스] 2023학년도 후기 학위수여식 학위가운 대여 및 포토존 운영 안내",
            date = "2024.07.23",
            isImportant = false,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        )
    )

    private val dummyNotice2 = listOf(
        CommonNotice(
            title = "[도서관] 자치위원회 46기 모집 안내",
            date = "2024.07.26",
            isImportant = true,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        ),
        CommonNotice(
            title = "[혁신사업] 8월 월간 비교과프로그램 안내",
            date = "2024.07.26",
            isImportant = true,
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzElMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D",
            classification = null
        )
    )


    fun fetchData(noticeType: NoticeType, page: Int){
        if(page > totalPage || page < 1) return

        _currentPage.value = page
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            when (noticeType) {
                NoticeType.GENERAL -> generalNotice(page)
                NoticeType.EVENT -> eventNotice(page)
                NoticeType.ACADEMIC -> academicNotice(page)
                NoticeType.SCHOLARSHIP_STUDENT_LOAN -> scholarshipStudentLoanNotice(page)
                NoticeType.CAREER_EMPLOYMENT -> careerEmploymentNotice(page)
                NoticeType.STUDENT_ACTIVITIES -> studentActivitiesNotice(page)
                NoticeType.BIDDING -> biddingNotice(page)
                NoticeType.UNIV_SAFETY -> univSafetyNotice(page)
                NoticeType.REGULATION -> regulationNotice(page)
                NoticeType.DORMITORY -> dormitoryNotice(page)
                NoticeType.DORMITORY_ENTRANCE_EXIT -> dormitoryEntranceExitNotice(page)
                NoticeType.LIBRARY -> libraryNotice(page)
                NoticeType.TEACHING -> teachingNotice(page)
                NoticeType.JOB_TRAINING -> jobTrainingNotice(page)
            }
        }
    }

    fun searchNotice(noticeType: NoticeType){
        _searchState.value = UiState.Loading

        _searchState.value = UiState.Success(dummyNotice2)
    }

    private fun generalNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun eventNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun academicNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun scholarshipStudentLoanNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun careerEmploymentNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun studentActivitiesNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun biddingNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun univSafetyNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun regulationNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun dormitoryNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun dormitoryEntranceExitNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun libraryNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun teachingNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }

    private fun jobTrainingNotice(page: Int){
        _uiState.value = UiState.Success(if(page%2 == 0) dummyNotice2 else dummyNotice)
    }
}
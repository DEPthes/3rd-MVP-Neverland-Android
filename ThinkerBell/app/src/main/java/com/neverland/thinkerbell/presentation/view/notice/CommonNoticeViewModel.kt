package com.neverland.thinkerbell.presentation.view.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.usecase.notice.GetDormitoryEntryNoticesUseCase
import com.neverland.thinkerbell.domain.usecase.notice.GetDormitoryNoticesUseCase
import com.neverland.thinkerbell.domain.usecase.notice.GetJobTrainingNoticesUseCase
import com.neverland.thinkerbell.domain.usecase.notice.GetLibraryNoticesUseCase
import com.neverland.thinkerbell.domain.usecase.notice.GetTeachingNoticesUseCase
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class CommonNoticeViewModel: ViewModel() {
    
    private var _currentPage = MutableLiveData(1)
    val currentPage: LiveData<Int> get() = _currentPage

    var totalPage = 30
    var currentNotice = listOf<NoticeItem>()

    private val _uiState = MutableLiveData<UiState<List<NoticeItem>>>(UiState.Loading)
    val uiState: LiveData<UiState<List<NoticeItem>>> get() = _uiState

    private val _searchState = MutableLiveData<UiState<List<NoticeItem>>>(UiState.Loading)
    val searchState: LiveData<UiState<List<NoticeItem>>> get() = _searchState

    private val dummyNotice = listOf(
        NoticeItem.CommonNotice(
            campus = "Library",
            id = 1,
            pubDate = "2024-07-26",
            title = "[도서관] 자치위원회 46기 모집 안내",
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D"
        ),
        NoticeItem.CommonNotice(
            campus = "Innovation",
            id = 2,
            pubDate = "2024-07-26",
            title = "[혁신사업] 8월 월간 비교과프로그램 안내",
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzElMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D"
        ),
        NoticeItem.CommonNotice(
            campus = "Human Rights Center",
            id = 3,
            pubDate = "2024-07-25",
            title = "[인권센터] 명지대학교 인권센터 연구원 채용 공고",
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D"
        ),
        NoticeItem.CommonNotice(
            campus = "Facilities",
            id = 4,
            pubDate = "2024-07-25",
            title = "[시설관리팀] 자연캠퍼스 정전 일정 안내[2024. 8. 17.(토)]",
            url = "https://www.mju.ac.kr/mjukr/255/subview.do?enc=Zm5jdDF8QEB8JTJGYmJzJTJGbWp1a3IlMkYxNDElMkYyMTM3NzMlMkZhcnRjbFZpZXcuZG8lM0ZwYWdlJTNEMSUyNnNyY2hDb2x1bW4lM0QlMjZzcmNoV3JkJTNEJTI2YmJzQ2xTZXElM0QlMjZiYnNPcGVuV3JkU2VxJTNEJTI2cmdzQmduZGVTdHIlM0QlMjZyZ3NFbmRkZVN0ciUzRCUyNmlzVmlld01pbmUlM0RmYWxzZSUyNmlzVmlldyUzRHRydWUlMjZwYXNzd29yZCUzRCUyNg%3D%3D"
        )
    )

    private val dummyNotice2 = listOf(

        NoticeItem.JobNotice(
            company = "크리에이티브 솔루션스",
            year = "2024",
            semester = "2학기",
            recruitingNum = "2",
            major = "국제학부/미디어커뮤니케이션학과/상경계열/예체능 디자인계열",
            deadline = "2024.07.26. 17시00분",
            period = "24.09.01~24.12.13",
            jobName = "그래픽 디자이너"
        ),
        NoticeItem.JobNotice(
            company = "그린테크",
            year = "2024",
            semester = "2학기",
            recruitingNum = "4",
            major = "국제학부/미디어커뮤니케이션학과/상경계열/예체능 디자인계열",
            deadline = "2024.07.26. 17시00분",
            period = "24.09.01~24.12.13",
            jobName = "[채용연계] 데이터센터 엔지니어 모집"
        ),
        NoticeItem.JobNotice(
            company = "테크코프",
            year = "2024",
            semester = "2학기",
            recruitingNum = "5",
            major = "컴퓨터 공학",
            deadline = "2024.07.26. 17시00분",
            period = "24.09.01~24.12.13",
            jobName = "소프트웨어 개발자"
        ),
        NoticeItem.JobNotice(
            company = "이노테크",
            year = "2024",
            semester = "2학기",
            recruitingNum = "3",
            major = "전기 공학",
            deadline = "2024.07.26. 17시00분",
            period = "24.09.01~24.12.13",
            jobName = "전기 엔지니어 인턴"
        )
    )


    fun fetchData(noticeType: NoticeType, page: Int){
        if(page > totalPage || page < 1) return

        _currentPage.value = page
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            when (noticeType) {
                NoticeType.NORMAL_NOTICE -> normalNotice(page)
                NoticeType.EVENT_NOTICE -> eventNotice(page)
                NoticeType.ACADEMIC_NOTICE -> academicNotice(page)
                NoticeType.SCHOLARSHIP_NOTICE -> scholarshipNotice(page)
                NoticeType.CAREER_NOTICE -> careerNotice(page)
                NoticeType.STUDENT_ACTS_NOTICE -> studentActsNotice(page)
                NoticeType.BIDDING_NOTICE -> biddingNotice(page)
                NoticeType.SAFETY_NOTICE -> safetyNotice(page)
                NoticeType.REVISION_NOTICE -> revisionNotice(page)
                NoticeType.DORMITORY_NOTICE -> dormitoryNotice(page)
                NoticeType.DORMITORY_ENTRY_NOTICE -> dormitoryEntryNotice(page)
                NoticeType.LIBRARY_NOTICE -> libraryNotice(page)
                NoticeType.TEACHING_NOTICE -> teachingNotice(page)
                NoticeType.JOB_TRAINING_NOTICE -> jobTrainingNotice(page)
            }
        }
    }

    fun searchNotice(noticeType: NoticeType){
        _searchState.value = UiState.Loading

        _searchState.value = UiState.Success(dummyNotice2)
    }

    private fun normalNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)
    }

    private fun eventNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)

    }

    private fun academicNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)

    }

    private fun scholarshipNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)

    }

    private fun careerNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)

    }

    private fun studentActsNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)

    }

    private fun biddingNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)

    }

    private fun safetyNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)

    }

    private fun revisionNotice(page: Int){
        _uiState.value = UiState.Success(dummyNotice)

    }

    private fun dormitoryNotice(page: Int){
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            GetDormitoryNoticesUseCase().invoke()
                .onFailure {
                    _uiState.value = UiState.Error(it)
                }
                .onSuccess {
                    _uiState.value = UiState.Success(it.ifEmpty { dummyNotice })
                }
        }
    }

    private fun dormitoryEntryNotice(page: Int){
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            GetDormitoryEntryNoticesUseCase().invoke()
                .onFailure {
                    _uiState.value = UiState.Error(it)
                }
                .onSuccess {
                    _uiState.value = UiState.Success(it.ifEmpty { dummyNotice })
                }
        }
    }

    private fun libraryNotice(page: Int){
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            GetLibraryNoticesUseCase().invoke()
                .onFailure {
                    _uiState.value = UiState.Error(it)
                }
                .onSuccess {
                    _uiState.value = UiState.Success(it.ifEmpty { dummyNotice })
                }
        }
    }

    private fun teachingNotice(page: Int){
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            GetTeachingNoticesUseCase().invoke()
                .onFailure {
                    _uiState.value = UiState.Error(it)
                }
                .onSuccess {
                    _uiState.value = UiState.Success(it.ifEmpty { dummyNotice })
                }
        }
    }

    private fun jobTrainingNotice(page: Int){
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            GetJobTrainingNoticesUseCase().invoke()
                .onFailure {
                    _uiState.value = UiState.Error(it)
                }
                .onSuccess {
                    _uiState.value = UiState.Success(it.ifEmpty { dummyNotice2 })
                }
        }

    }
}
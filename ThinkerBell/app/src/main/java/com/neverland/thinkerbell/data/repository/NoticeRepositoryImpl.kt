package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.model.PageableResponse
import com.neverland.thinkerbell.data.remote.model.notice.CommonNoticeDTO
import com.neverland.thinkerbell.data.remote.service.NoticeService
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.PageableNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.model.univ.DeptContact
import com.neverland.thinkerbell.domain.repository.NoticeRepository
import org.json.JSONObject

class NoticeRepositoryImpl : NoticeRepository {
    private val service = RetrofitClient.getInstance().create(NoticeService::class.java)

    override suspend fun getDormitoryNotices(
        page: Int,
        ssaId: String,
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getDormitoryNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(PageableNotice(
                        page = data.page,
                        size = data.size,
                        totalItems = data.totalItems,
                        items = data.items.map {
                            NoticeItem.CommonNotice(
                                id = it.id,
                                url = it.url,
                                campus = it.campus,
                                title = it.title,
                                pubDate = it.pubDate,
                                important = it.important ?: false,
                                marked = it.marked
                            )
                        }
                    ))
                } else {
                    Result.failure(Exception("Get DormitoryNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get DormitoryNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDormitoryEntryNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getDormitoryEntryNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(PageableNotice(
                        page = data.page,
                        size = data.size,
                        totalItems = data.totalItems,
                        items = data.items.map {
                            NoticeItem.CommonNotice(
                                id = it.id,
                                url = it.url,
                                campus = it.campus,
                                title = it.title,
                                pubDate = it.pubDate,
                                important = it.important ?: false,
                                marked = it.marked
                            )
                        }
                    ))
                } else {
                    Result.failure(Exception("Get DormitoryNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get DormitoryNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getJobTrainingNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.JobNotice>> {
        return try {
            val res = service.getJobTrainingNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(PageableNotice(
                        page = data.page,
                        size = data.size,
                        totalItems = data.totalItems,
                        items = data.items.map {
                            NoticeItem.JobNotice(
                                id = it.id,
                                company = it.company,
                                year = it.year,
                                semester = it.semester,
                                period = it.period,
                                major = it.major,
                                recruitingNum = it.recrutingNum,
                                deadline = it.deadline,
                                jobName = it.jobName,
                                marked = it.marked
                            )
                        }
                    ))
                } else {
                    Result.failure(Exception("Get JobTrainingNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get JobTrainingNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getLibraryNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getLibraryNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(PageableNotice(
                        page = data.page,
                        size = data.size,
                        totalItems = data.totalItems,
                        items = data.items.map {
                            NoticeItem.CommonNotice(
                                id = it.id,
                                url = it.url,
                                campus = it.campus,
                                title = it.title,
                                pubDate = it.pubDate,
                                important = it.important ?: false,
                                marked = it.marked
                            )
                        }
                    ))
                } else {
                    Result.failure(Exception("Get LibraryNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get LibraryNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTeachingNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getTeachingNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(PageableNotice(
                        page = data.page,
                        size = data.size,
                        totalItems = data.totalItems,
                        items = data.items.map {
                            NoticeItem.CommonNotice(
                                id = it.id,
                                url = it.url,
                                campus = it.campus,
                                title = it.title,
                                pubDate = it.pubDate,
                                important = it.important ?: false,
                                marked = it.marked
                            )
                        }
                    ))
                } else {
                    Result.failure(Exception("Get TeachingNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get TeachingNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAcademicNotices(
        page: Int, ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getAcademicNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get AcademicNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get AcademicNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEventNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getEventNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get EventNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get EventNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getNormalNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getNormalNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get NormalNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get NormalNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRevisionNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getRevisionNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get RevisionNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get RevisionNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSafetyNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getSafetyNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get SafetyNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get SafetyNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getScholarshipNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getScholarship(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get ScholarshipNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get ScholarshipNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStudentActsNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getStudentActsNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get StudentActsNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get StudentActsNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCareerNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getCareerNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get CareerNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get CareerNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBiddingNotices(
        page: Int,
        ssaId: String
    ): Result<PageableNotice<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getBiddingNotices(page, ssaId)
            if (res.isSuccessful) {
                if (res.body() != null) {
                    val data = res.body()!!.data

                    Result.success(
                        PageableNotice(
                            page = data.page,
                            size = data.size,
                            totalItems = data.totalItems,
                            items = data.items.map {
                                NoticeItem.CommonNotice(
                                    id = it.id,
                                    title = it.title,
                                    pubDate = it.pubDate,
                                    url = it.url,
                                    campus = it.campus,
                                    important = it.important ?: false,
                                    marked = it.marked
                                )
                            }
                        )
                    )
                } else {
                    Result.failure(Exception("Get BiddingNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get BiddingNotices failed: $msg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchNoticesByCategory(
        noticeType: NoticeType,
        keyword: String,
        ssaId: String
    ): Result<List<NoticeItem>> {
        return try {
            val res = service.searchNotices(keyword = keyword, ssaId = ssaId)

            if (res.isSuccessful) {
                val data = res.body()?.data
                val noticeItems: List<NoticeItem>

                if (data != null) {
                    if(noticeType != NoticeType.JOB_TRAINING_NOTICE){
                        val notices = when (noticeType) {
                            NoticeType.NORMAL_NOTICE -> data.normalNotices
                            NoticeType.EVENT_NOTICE -> data.eventNotices
                            NoticeType.ACADEMIC_NOTICE -> data.academicNotices
                            NoticeType.SCHOLARSHIP_NOTICE -> data.scholarshipNotices
                            NoticeType.CAREER_NOTICE -> data.careerNotices
                            NoticeType.STUDENT_ACTS_NOTICE -> data.studentActsNotices
                            NoticeType.BIDDING_NOTICE -> data.biddingNotices
                            NoticeType.SAFETY_NOTICE -> data.safetyNotices
                            NoticeType.REVISION_NOTICE -> data.revisionNotices
                            NoticeType.DORMITORY_NOTICE -> data.dormitoryNotices
                            NoticeType.DORMITORY_ENTRY_NOTICE -> data.dormitoryEntryNotices
                            NoticeType.LIBRARY_NOTICE -> data.libraryNotices
                            NoticeType.TEACHING_NOTICE -> data.teachingNotices
                            else -> null
                        }

                        noticeItems = (notices?.map { NoticeItem.CommonNotice(
                            id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, campus = it.campus, marked = it.marked
                        ) } ?: emptyList())
                    } else {
                        noticeItems = (data.jobTrainingNotices?.map { NoticeItem.JobNotice(
                            id = it.id, company = it.company, year = it.year, semester = it.semester, deadline = it.deadline, jobName = it.jobName, major = it.major, period = it.period, recruitingNum = it.recrutingNum
                        ) } ?: emptyList())
                    }

                    Result.success(noticeItems)
                } else {
                    Result.failure(Exception("Failed to Search notices: response data is null"))
                }
            } else {
                val errorMsg = JSONObject(res.errorBody()?.string() ?: "{}").optString("message", "Unknown error")
                Result.failure(Exception("Failed to Search notices: $errorMsg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
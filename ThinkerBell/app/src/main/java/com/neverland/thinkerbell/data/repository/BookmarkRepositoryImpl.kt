package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.service.BookmarkService
import com.neverland.thinkerbell.domain.model.notice.BookmarkNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.model.notice.RecentBookmarkNotice
import com.neverland.thinkerbell.domain.repository.BookmarkRepository
import org.json.JSONObject

class BookmarkRepositoryImpl: BookmarkRepository {
    private val service = RetrofitClient.getInstance().create(BookmarkService::class.java)

    override suspend fun getNoticeBookmark(ssaId: String): Result<BookmarkNotice> {
        return try {
            val res = service.getNoticeBookmark(ssaId = ssaId )
            if(res.isSuccessful){
                if(res.body() != null){
                    val data = res.body()!!.data
                    if(data != null){
                        val bookmarkNotice = BookmarkNotice(
                            safetyNotice = data.safetyNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked)},
                            revisionNotice = data.revisionNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked)},
                            libraryNotice = data.libraryNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked, important = it.important?:false, campus = it.campus)},
                            dormitoryNotice = data.dormitoryNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked, important = it.important?:false, campus = it.campus)},
                            teachingNotice = data.teachingNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked, important = it.important?:false)},
                            jobTrainingNotice = data.jobTrainingNotice?.map{NoticeItem.JobNotice(id = it.id, company = it.company, year = it.year, semester = it.semester, recruitingNum = it.recrutingNum, major = it.major, deadline = it.deadline, period = it.period, jobName = it.jobName, marked = it.marked)},
                            eventNotice = data.eventNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked)},
                            studentActsNotice = data.studentActsNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked)},
                            academicNotice = data.academicNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked, important = it.important?:false)},
                            careerNotice = data.careerNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked)},
                            biddingNotice = data.biddingNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked)},
                            dormitoryEntryNotice = data.dormitoryEntryNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked, important = it.important?:false, campus = it.campus)},
                            scholarshipNotice = data.scholarshipNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked)},
                            normalNotice = data.normalNotice?.map{NoticeItem.CommonNotice(id = it.id, title = it.title, pubDate = it.pubDate, url = it.url, marked = it.marked)},
                        )
                        Result.success(bookmarkNotice)
                    } else {
                        Result.failure(Exception("Get BookmarkNotices failed: response is null data"))
                    }
                } else {
                    Result.failure(Exception("Get BookmarkNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get BookmarkNotices failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getRecentNoticeBookmark(ssaId: String): Result<List<RecentBookmarkNotice>> {
        return try {
            val res = service.getRecentNoticeBookmark(ssaId)
            if(res.isSuccessful){
                if(res.body() != null) {
                    val data = res.body()!!.data
                    if(data != null) {
                        Result.success(res.body()!!.data.map { RecentBookmarkNotice(category = it.category, title = it.title, pubDate = it.pubDate, url = it.url) })
                    } else {
                        Result.success(emptyList())
                    }
                } else {
                    Result.failure(Exception("Get Recent Bookmark failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get Recent Bookmark failed: $msg"))
            }
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    override suspend fun postNoticeBookmark(
        category: String,
        noticeId: Int,
        ssaId: String
    ): Result<Boolean> {
        return try {
            val res = service.postNoticeBookmark(category, noticeId, ssaId)
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(true)
                } else {
                    Result.failure(Exception("Post Bookmark failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Post Bookmark failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun deleteNoticeBookmark(
        category: String,
        noticeId: Int,
        ssaId: String
    ): Result<Boolean> {
        return try {
            val res = service.deleteNoticeBookmark(category, noticeId, ssaId)
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(true)
                } else {
                    Result.failure(Exception("Delete Bookmark failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Delete Bookmark failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

}
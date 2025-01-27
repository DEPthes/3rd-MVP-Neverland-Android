package com.neverland.thinkerbell.core.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

object LoggerUtil {
    private const val TAG = "LOGGER"

    init {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    fun d(message: String) {
        Logger.t(TAG).d(message)
    }

    fun i(message: String) {
        Logger.t(TAG).i(message)
    }

    fun w(message: String) {
        Logger.t(TAG).w(message)
    }

    fun e(message: String) {
        Logger.t(TAG).e(message)
    }

    fun json(message: String){
        Logger.t(TAG).json(message)
    }

    fun xml(message: String){
        Logger.t(TAG).xml(message)
    }
}
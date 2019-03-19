package ru.ifmo.ctddev.gromov.vkphotos

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

class ServiceReceiver(handler: Handler, private var receiver: Receiver? = null) : ResultReceiver(handler) {
    fun setReceiver(receiver: Receiver) {
        this.receiver = receiver
    }

    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
        receiver?.onReceiveResult(resultCode, resultData)
    }

    interface Receiver {
        fun onReceiveResult(resultCode: Int, resultData: Bundle)
    }
}

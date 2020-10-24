package com.labsidea.mypetapp.utils

data class RequestData<out T>(val status: RequestStatus?, val data: T?,  val remaining_pages: Int? = 0, val messageError: String? = null ) {

    companion object {
        fun <T> loading(data: T?) = RequestData(RequestStatus.LOADING, data)

        fun <T> success(data: T?, remaining_pages: Int): RequestData<T> = RequestData(RequestStatus.SUCCESS, data, remaining_pages)

        fun <T> error(messageError: String?, data: T?) = RequestData(RequestStatus.ERROR, data, messageError = messageError)
    }
}
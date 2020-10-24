package com.labsidea.mypetapp.data.model

import com.google.gson.annotations.SerializedName
import com.labsidea.mypetapp.utils.RequestStatus

data class RequestResponse<T>(
    @SerializedName("status_code")
    val status: RequestStatus?,

    @SerializedName("objects")
    val data: List<T>?,

    @SerializedName("remaining_pages")
    val remaining_pages: Int?)
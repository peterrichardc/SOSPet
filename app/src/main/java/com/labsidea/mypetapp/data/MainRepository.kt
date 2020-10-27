package com.labsidea.mypetapp.data

import com.labsidea.mypetapp.data.api.IApiService
import com.labsidea.mypetapp.data.model.RequestResponse
import com.labsidea.mypetapp.data.model.User
import io.reactivex.Single
import javax.inject.Inject


class MainRepository @Inject constructor(private val apiService: IApiService){

    fun getUsers(): Single<RequestResponse<User>>? = apiService.getInstitutions()
}
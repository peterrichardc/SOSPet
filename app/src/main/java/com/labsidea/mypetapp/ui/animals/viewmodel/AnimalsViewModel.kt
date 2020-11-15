package com.labsidea.mypetapp.ui.animals.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.labsidea.mypetapp.data.MainRepository
import com.labsidea.mypetapp.data.model.User
import com.labsidea.mypetapp.utils.RequestData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class AnimalsViewModel(private val mainRepository: MainRepository): ViewModel(){

    private val users = MutableLiveData<RequestData<ArrayList<User>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }


    private fun fetchUsers() {
        users.postValue(RequestData.loading(null))
        compositeDisposable.add(
            mainRepository.getUsers()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
            !!.subscribe({ userList ->
                    val requestData = RequestData.success(userList.data, userList.remaining_pages!!)

                    try {
                        users.postValue(requestData as RequestData<ArrayList<User>>)
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }, {
                    users.postValue(RequestData.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUsers() = users
}
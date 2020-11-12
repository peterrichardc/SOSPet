package com.labsidea.mypetapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.labsidea.mypetapp.data.MainRepository
import com.labsidea.mypetapp.data.api.IApiService
import com.labsidea.mypetapp.ui.main.viewmodel.MainViewModel

class ViewModelFactory : ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(MainRepository(IApiService.create())) as T

        throw IllegalArgumentException("Unknown class name")
    }
}
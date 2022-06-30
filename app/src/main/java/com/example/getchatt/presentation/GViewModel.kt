package com.example.getchatt.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getchatt.data.cacheUid.IdDB
import com.example.getchatt.data.cacheUid.IdDao
import com.example.getchatt.data.cacheUid.IdEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GViewModel(application: Application):AndroidViewModel(application) {

    val readUid: LiveData<IdEntity>
    val idDao = IdDB.getInstance(application).IdDao()

    init {
        val idDao = IdDB.getInstance(application).IdDao()
        readUid= idDao.getUid()
    }

    suspend fun deleteUid(){
        viewModelScope.launch(Dispatchers.IO) {
            idDao.deleteUid()
        }
    }

}
package com.example.getchatt.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.getchatt.data.cacheUid.IdDB
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

    fun deleteUid(){
        viewModelScope.launch(Dispatchers.IO) {
            idDao.deleteUid()
        }
    }

    fun addUid(item: IdEntity){
        viewModelScope.launch(Dispatchers.IO) {
            idDao.insert(item)
        }
    }

}

class GViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(GViewModel::class.java)) {
            return GViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
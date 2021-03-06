package com.tsu.roman_encoder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tsu.roman_encoder.room.Database
import com.tsu.roman_encoder.room.Entity
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val encoder = Encoder()
    private val dao = Database.getInstance(app).getDao()

    private var _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    fun getResult(input: String) {
        val output = dao.getOutput(input)
        if (output.isNullOrEmpty()) {
            getResultFromModel(input)
        } else {
            _result.postValue(output)
        }
    }

    private fun getResultFromModel(input: String) {
        try {
            val output = encoder.encode(input)
            _result.postValue(output)
            saveResult(Entity(input, output))
        } catch (e: Exception) {
            _result.postValue(e.message)
        }
    }

    private fun saveResult(data: Entity) {
        dao.insert(data)
    }
}
package org.d3if2036.hitungkecepatan.ui.konsepglb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2036.hitungkecepatan.R
import org.d3if2036.hitungkecepatan.network.KonsepGlbApi
import org.d3if2036.hitungkecepatan.ui.KonsepGlb

class KonsepGlbViewModel: ViewModel() {

    private val data =MutableLiveData<List<KonsepGlb>>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(KonsepGlbApi.service.getKonsepGlb())
            } catch (e: Exception) {
                Log.d("KonsepGlbViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<KonsepGlb>> = data
}
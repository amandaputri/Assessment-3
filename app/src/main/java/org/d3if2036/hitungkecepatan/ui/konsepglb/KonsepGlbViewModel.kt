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
        data.value = initData()
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val result = KonsepGlbApi.service.getKonsepGlb()
                Log.d("KonsepGlbViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.d("KonsepGlbViewModel", "Failure: ${e.message}")
            }
        }
    }

    private fun initData(): List<KonsepGlb>{
        return listOf(
            KonsepGlb("coba", R.drawable.coba),
            KonsepGlb("coba2", R.drawable.coba),
            KonsepGlb("coba3", R.drawable.coba),
            KonsepGlb("coba4", R.drawable.coba)
        )
    }

    fun getData(): LiveData<List<KonsepGlb>> = data
}
package org.d3if2036.hitungkecepatan.ui.konsepglb

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2036.hitungkecepatan.MainActivity
import org.d3if2036.hitungkecepatan.R
import org.d3if2036.hitungkecepatan.network.ApiStatus
import org.d3if2036.hitungkecepatan.network.KonsepGlbApi
import org.d3if2036.hitungkecepatan.network.UpdateWorker
import org.d3if2036.hitungkecepatan.ui.KonsepGlb
import java.util.concurrent.TimeUnit

class KonsepGlbViewModel: ViewModel() {

    private val data =MutableLiveData<List<KonsepGlb>>()
    private val status = MutableLiveData<ApiStatus>()


    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(KonsepGlbApi.service.getKonsepGlb())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("KonsepGlbViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<KonsepGlb>> = data

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}
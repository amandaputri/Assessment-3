package org.d3if2036.hitungkecepatan.ui.konsepglb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2036.hitungkecepatan.R
import org.d3if2036.hitungkecepatan.ui.KonsepGlb

class KonsepGlbViewModel: ViewModel() {

    private val data =MutableLiveData<List<KonsepGlb>>()

    init {
        data.value = initData()
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
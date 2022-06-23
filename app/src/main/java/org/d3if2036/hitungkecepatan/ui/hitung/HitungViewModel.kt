package org.d3if2036.hitungkecepatan.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2036.hitungkecepatan.db.KecepatanDao
import org.d3if2036.hitungkecepatan.db.KecepatanDb
import org.d3if2036.hitungkecepatan.db.KecepatanEntity
import org.d3if2036.hitungkecepatan.model.HasilKecepatan
import org.d3if2036.hitungkecepatan.model.hitungKec

class HitungViewModel(private val db: KecepatanDao): ViewModel (){

    private val hasilKecepatan = MutableLiveData<HasilKecepatan?>()

    fun hitungKec(jarak: Float, waktu:Float){
        val dataKecepatan = KecepatanEntity(
            jarak = jarak,
            waktu = waktu
        )
        hasilKecepatan.value = dataKecepatan.hitungKec()

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.insert(dataKecepatan)
            }
        }
    }

    fun getHasilKecepatan(): LiveData<HasilKecepatan?> = hasilKecepatan
}
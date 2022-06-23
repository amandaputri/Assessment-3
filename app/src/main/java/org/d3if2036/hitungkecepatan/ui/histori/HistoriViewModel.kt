package org.d3if2036.hitungkecepatan.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2036.hitungkecepatan.db.KecepatanDao

class HistoriViewModel(private val db: KecepatanDao) : ViewModel() {
    val data = db.getLastKecepatan()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}
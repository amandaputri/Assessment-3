package org.d3if2036.hitungkecepatan.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2036.hitungkecepatan.db.KecepatanDao
import java.lang.IllegalArgumentException

class HistoriViewModelFactory(
    private val db: KecepatanDao
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HistoriViewModel::class.java)){
            return HistoriViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
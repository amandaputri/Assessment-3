package org.d3if2036.hitungkecepatan.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KecepatanDao {

    @Insert
    fun insert(hKecepatan: KecepatanEntity)

    @Query("SELECT * FROM hKecepatan ORDER BY id DESC ")
    fun getLastKecepatan(): LiveData<List<KecepatanEntity>>

    @Query("DELETE FROM hKecepatan")
    fun clearData()
}
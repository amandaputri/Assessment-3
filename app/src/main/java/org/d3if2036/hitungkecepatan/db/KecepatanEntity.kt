package org.d3if2036.hitungkecepatan.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hKecepatan")
class KecepatanEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var jarak: Float,
    var waktu: Float
)
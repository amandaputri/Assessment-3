package org.d3if2036.hitungkecepatan.model

import org.d3if2036.hitungkecepatan.db.KecepatanEntity

fun KecepatanEntity.hitungKec(): HasilKecepatan{
    val kecepatan = jarak / waktu
    return HasilKecepatan(kecepatan)
}
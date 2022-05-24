package com.example.students

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class PersonModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "fio") val fio: String = "",
    @ColumnInfo(name = "flg") var flg: String?,
    @ColumnInfo(name = "v_kori") var vKori: String?,
    @ColumnInfo(name = "rv_kori") var rvKori: String?,
    @ColumnInfo(name = "covid") var covid: String?
)
package com.example.students

import androidx.room.*

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: PersonModel)

    @Delete
    fun deletePerson(person: PersonModel)

    @Query("SELECT * FROM persons")
    fun getAllPersons(): List<PersonModel>

    @Query("SELECT * FROM persons WHERE fio LIKE '%' || :fio  || '%'")
    fun findByName(fio: String): List<PersonModel>

}
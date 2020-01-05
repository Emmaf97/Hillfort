package org.wit.hillfortapp.room


import androidx.room.*
import org.wit.hillfortapp.models.HillfortModel

@Dao
interface HillfortDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(Hillfort: HillfortModel)

    @Query("SELECT * FROM HillfortModel")
    fun findAll(): List<HillfortModel>

    @Query("select * from HillfortModel where id = :id")
    fun findById(id: Long): HillfortModel

    @Update
    fun update(Hillfort: HillfortModel)

    @Delete
    fun deleteHillfort(Hillfort: HillfortModel)
}
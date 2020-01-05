package org.wit.hillfortapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.wit.hillfortapp.models.HillfortModel

import org.wit.hillfortapp.room.HillfortDao

@Database(entities = arrayOf(HillfortModel::class), version = 1,  exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun HillfortDao(): HillfortDao
}
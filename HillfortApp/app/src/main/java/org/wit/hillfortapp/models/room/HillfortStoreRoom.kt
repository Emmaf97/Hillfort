package org.wit.hillfortapp.room

import android.content.Context
import androidx.room.Room
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.HillfortStore


class HillfortStoreRoom(val context: Context) : HillfortStore {

    var dao: HillfortDao

    init {
        val database = Room.databaseBuilder(context, Database::class.java, "room_sample.db")
            .fallbackToDestructiveMigration()
            .build()
        dao = database.HillfortDao()
    }

    override fun findAll(): List<HillfortModel> {
        return dao.findAll()
    }

    override fun findById(id: Long): HillfortModel? {
        return dao.findById(id)
    }

    override fun create(Hillfort: HillfortModel) {
        dao.create(Hillfort)
    }

    override fun update(Hillfort: HillfortModel) {
        dao.update(Hillfort)
    }

    override fun delete(Hillfort: HillfortModel) {
        dao.deleteHillfort(Hillfort)
    }

    override fun clear() {
    }
}
package org.wit.hillfortapp.models.mem

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortModel

import org.wit.hillfortapp.models.HillfortStore

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HillfortAppMemStore : HillfortStore, AnkoLogger {

    val hillforts = ArrayList<HillfortModel>()

    override fun findAll(): List<HillfortModel> {
        return hillforts
    }

    override fun create(hillfort: HillfortModel) {
        hillfort.id = getId()
        hillforts.add(hillfort)
        logAll()
    }

    override fun update(hillfort: HillfortModel) {
        var foundhillfort: HillfortModel? = hillforts.find { p -> p.id == hillfort.id }
        if (foundhillfort != null) {
            foundhillfort.title = hillfort.title
            foundhillfort.description = hillfort.description
            foundhillfort.image = hillfort.image
            foundhillfort.location = hillfort.location
            logAll();
        }
    }

    override fun delete(hillfort: HillfortModel) {
        hillforts.remove(hillfort)
    }

    override fun findById(id:Long) : HillfortModel? {
        val foundhillfort: HillfortModel? = hillforts.find { it.id == id }
        return foundhillfort
    }

    fun logAll() {
        hillforts.forEach { info("${it}") }
    }

    override fun clear() {
        hillforts.clear()
    }
}
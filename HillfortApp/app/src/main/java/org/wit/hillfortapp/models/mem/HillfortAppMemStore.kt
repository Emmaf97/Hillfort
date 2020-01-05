package org.wit.hillfortapp.models.mem

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.HillfortStore

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HillfortMemStore : HillfortStore, AnkoLogger {

    val Hillforts = ArrayList<HillfortModel>()

    override fun findAll(): List<HillfortModel> {
        return Hillforts
    }

    override fun create(Hillfort: HillfortModel) {
        Hillfort.id = getId()
        Hillforts.add(Hillfort)
        logAll()
    }

    override fun update(Hillfort: HillfortModel) {
        var foundHillfort: HillfortModel? = Hillforts.find { p -> p.id == Hillfort.id }
        if (foundHillfort != null) {
            foundHillfort.title = Hillfort.title
            foundHillfort.description = Hillfort.description
            foundHillfort.image = Hillfort.image
            foundHillfort.location = Hillfort.location
            logAll()
        }
    }

    override fun delete(Hillfort: HillfortModel) {
        Hillforts.remove(Hillfort)
    }

    override fun findById(id:Long) : HillfortModel? {
        val foundHillfort: HillfortModel? = Hillforts.find { it.id == id }
        return foundHillfort
    }

    fun logAll() {
        Hillforts.forEach { info("${it}") }
    }

    override fun clear() {
        Hillforts.clear()
    }
}
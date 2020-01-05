package org.wit.hillfortapp.models.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.hillfortapp.helpers.exists
import org.wit.hillfortapp.helpers.read
import org.wit.hillfortapp.helpers.write
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.HillfortStore
import java.util.*

val JSON_FILE = "Hillforts.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<HillfortModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class HillfortJSONStore : HillfortStore, AnkoLogger {

    val context: Context
    var Hillforts = mutableListOf<HillfortModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<HillfortModel> {
        return Hillforts
    }

    override fun create(Hillfort: HillfortModel) {
        Hillfort.id = generateRandomId()
        Hillforts.add(Hillfort)
        serialize()
    }

    override fun update(Hillfort: HillfortModel) {
        val HillfortsList = findAll() as ArrayList<HillfortModel>
        var foundHillfort: HillfortModel? = HillfortsList.find { p -> p.id == Hillfort.id }
        if (foundHillfort != null) {
            foundHillfort.title = Hillfort.title
            foundHillfort.description = Hillfort.description
            foundHillfort.image = Hillfort.image
            foundHillfort.location = Hillfort.location
        }
        serialize()
    }

    override fun delete(Hillfort: HillfortModel) {
        Hillforts.remove(Hillfort)
        serialize()
    }

    override fun findById(id:Long) : HillfortModel? {
        val foundHillfort: HillfortModel? = Hillforts.find { it.id == id }
        return foundHillfort
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(Hillforts,
            listType
        )
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        Hillforts = Gson().fromJson(jsonString, listType)
    }

    override fun clear() {
        Hillforts.clear()
    }
}

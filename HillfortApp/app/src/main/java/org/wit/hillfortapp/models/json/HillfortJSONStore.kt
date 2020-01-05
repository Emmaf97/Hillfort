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

val JSON_FILE = "hillforts.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<HillfortModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class hillfortJSONStore : HillfortStore, AnkoLogger {

    val context: Context
    var hillforts = mutableListOf<HillfortModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<HillfortModel> {
        return hillforts
    }

    override fun create(hillfort: HillfortModel) {
        hillfort.id = generateRandomId()
        hillforts.add(hillfort)
        serialize()
    }

    override fun update(hillfort: HillfortModel) {
        val hillfortsList = findAll() as ArrayList<HillfortModel>
        var foundhillfort: HillfortModel? = hillfortsList.find { p -> p.id == hillfort.id }
        if (foundhillfort != null) {
            foundhillfort.title = hillfort.title
            foundhillfort.description = hillfort.description
            foundhillfort.image = hillfort.image
            foundhillfort.location = hillfort.location
        }
        serialize()
    }

    override fun delete(hillfort: HillfortModel) {
        hillforts.remove(hillfort)
        serialize()
    }

    override fun findById(id:Long) : HillfortModel? {
        val foundhillfort: HillfortModel? = hillforts.find { it.id == id }
        return foundhillfort
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(hillforts,
            listType
        )
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        hillforts = Gson().fromJson(jsonString, listType)
    }

    override fun clear() {
        hillforts.clear()
    }
}

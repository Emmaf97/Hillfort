package org.wit.hillfortapp

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortAppJSONStore
import org.wit.hillfortapp.models.HillfortAppMemStore
import org.wit.hillfortapp.models.HillfortStore

class MainApp : Application(), AnkoLogger {

    lateinit var hillforts: HillfortStore

    //val placemarks = PlacemarkMemStore()

    override fun onCreate() {
        super.onCreate()
        hillforts = HillfortAppJSONStore(applicationContext)
        info("Hillfort started")
        //info("Placemark started")
    }
}
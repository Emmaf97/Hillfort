package org.wit.hillfortapp.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortStore
import org.wit.hillfortapp.models.firebase.HillfortFireStore

class MainApp : Application(), AnkoLogger {

    lateinit var Hillforts: HillfortStore

    override fun onCreate() {
        super.onCreate()
        Hillforts = HillfortFireStore(applicationContext)
        info("Hillfort started")
    }
}
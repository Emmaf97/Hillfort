package org.wit.hillfortapp

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfortapp.models.HillfortAppJSONStore
import org.wit.hillfortapp.models.HillfortAppMemStore
import org.wit.hillfortapp.models.HillfortStore
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import sun.jvm.hotspot.utilities.IntArray



class MainApp : Application(), AnkoLogger  {

    lateinit var hillforts: HillfortStore


    override fun onCreate() {
        super.onCreate()
        hillforts = HillfortAppJSONStore(applicationContext)
        info("Hillfort started")
        //setContentView(R.layout.activity_login)

    }
}
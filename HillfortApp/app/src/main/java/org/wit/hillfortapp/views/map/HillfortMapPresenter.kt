package org.wit.hillfortapp.views.map


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.views.BasePresenter
import org.wit.hillfortapp.views.views.BaseView

class HillfortMapPresenter(view: BaseView) : BasePresenter(view) {


    fun doPopulateMap(map: GoogleMap, Hillforts: List<HillfortModel>) {
        map.uiSettings.isZoomControlsEnabled = true
        Hillforts.forEach {
            val loc = LatLng(it.location.lat, it.location.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options).tag = it
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.location.zoom))
        }
    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as Long
        doAsync {
            val Hillfort = marker.tag as HillfortModel
            uiThread {
                if (Hillfort != null) view?.showHillfort(Hillfort)
            }
        }
    }

    fun loadHillforts() {
        doAsync {
            val Hillforts = app.Hillforts.findAll()
            uiThread {
                view?.showHillforts(Hillforts)
            }
        }
    }
}
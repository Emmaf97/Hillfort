package org.wit.hillfortapp.views.map

import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_hillfort_maps.*
import kotlinx.android.synthetic.main.content_hillfort_maps.*
import org.wit.hillfort.views.map.hillfortMapPresenter
import org.wit.hillfortapp.R
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.views.views.BaseView

class HillfortMapView : BaseView(), GoogleMap.OnMarkerClickListener {

    lateinit var presenter: hillfortMapPresenter
    lateinit var map : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort_maps)
        super.init(toolbar, true)

        presenter = initPresenter (hillfortMapPresenter(this)) as hillfortMapPresenter

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            map = it
            map.setOnMarkerClickListener(this)
            presenter.loadhillforts()
        }
    }

    override fun showhillfort(hillfort: HillfortModel) {
        currentTitle.text = hillfort.title
        currentDescription.text = hillfort.description
        Glide.with(this).load(hillfort.image).into(currentImage);
    }

    override fun showhillforts(hillforts: List<HillfortModel>) {
        presenter.doPopulateMap(map, hillforts)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doMarkerSelected(marker)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}

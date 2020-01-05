package org.wit.hillfortapp.views.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import kotlinx.android.synthetic.main.content_hillfort_maps.*
import org.wit.hillfortapp.R
import org.wit.hillfortapp.helpers.readImageFromPath
import org.wit.hillfortapp.models.HillfortModel


class HillfortMapView : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    lateinit var presenter: HillfortMapPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort_maps)
        setSupportActionBar(toolbar)
        presenter = HillfortMapPresenter(this)

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            presenter.doPopulateMap(it)
        }
    }

    fun showPlacemark(placemark: HillfortModel) {
        currentTitle.text = placemark.title
        currentDescription.text = placemark.description
        currentImage.setImageBitmap(readImageFromPath(this, placemark.image))
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
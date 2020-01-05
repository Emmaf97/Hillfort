package org.wit.hillfortapp.views.hillfort

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.hillfortapp.R
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.Location
import org.wit.hillfortapp.views.hillfort.HillfortPresenter
import org.wit.hillfortapp.views.views.BaseView

class HillfortView : BaseView(), AnkoLogger {

    lateinit var presenter: HillfortPresenter
    var Hillfort = HillfortModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort)
        super.init(toolbarAdd, true)

        presenter = initPresenter (HillfortPresenter(this)) as HillfortPresenter

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            presenter.doConfigureMap(it)
            it.setOnMapClickListener { presenter.doSetLocation() }
        }

        chooseImage.setOnClickListener { presenter.doSelectImage() }
    }

    override fun showhillfort(Hillfort: HillfortModel) {
        HillfortTitle.setText(Hillfort.title)
        description.setText(Hillfort.description)
        Glide.with(this).load(Hillfort.image).into(HillfortImage)
        if (Hillfort.image != null) {
            chooseImage.setText(R.string.change_Hillfort_image)
        }
        this.showLocation(Hillfort.location)
    }

    override fun showLocation(location: Location) {
        lat.text = "%.6f".format(location.lat)
        lng.text = "%.6f".format(location.lng)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_hillfort, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                presenter.doDelete()
            }
            R.id.item_save -> {
                if (HillfortTitle.text.toString().isEmpty()) {
                    toast(R.string.enter_Hillfort_title)
                } else {
                    presenter.doAddOrSave(HillfortTitle.text.toString(), description.text.toString())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            presenter.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        presenter.doCancel()
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
        presenter.doResartLocationUpdates()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}



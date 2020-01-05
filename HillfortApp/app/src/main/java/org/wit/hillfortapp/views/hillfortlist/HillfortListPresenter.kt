package org.wit.hillfortapp.views.hillfortlist

import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.views.base.BasePresenter
import org.wit.hillfortapp.views.base.BaseView
import org.wit.hillfortapp.views.base.VIEW

class HillfortListPresenter(view: BaseView) : BasePresenter(view) {

    fun doAddHillfort() {
        view?.navigateTo(VIEW.HILLFORT)
    }

    fun doEditHillfort(hillfort: HillfortModel) {
        view?.navigateTo(VIEW.HILLFORT, 0, "hillfort_edit", hillfort)
    }

    fun doShowHillfortsMap() {
        view?.navigateTo(VIEW.MAPS)
    }

    fun loadHillforts() {
        view?.showHillforts(app.hillforts.findAll())
    }
}
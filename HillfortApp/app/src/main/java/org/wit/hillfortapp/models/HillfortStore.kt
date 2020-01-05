package org.wit.hillfortapp.models

import org.wit.hillfortapp.models.HillfortModel

interface HillfortStore {
    fun findAll(): List<HillfortModel>
    fun create(Hillfort: HillfortModel)
    fun update(Hillfort: HillfortModel)
    fun delete(Hillfort: HillfortModel)
    fun findById(id:Long) : HillfortModel?
    fun clear()
}
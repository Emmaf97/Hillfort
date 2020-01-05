package org.wit.hillfortapp.models.firebase

import android.content.Context
import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.jetbrains.anko.AnkoLogger
import org.wit.hillfortapp.helpers.readImageFromPath
import org.wit.hillfortapp.models.HillfortModel
import org.wit.hillfortapp.models.HillfortStore
import java.io.ByteArrayOutputStream
import java.io.File

class HillfortFireStore(val context: Context) : HillfortStore, AnkoLogger {

    val Hillforts = ArrayList<HillfortModel>()
    lateinit var userId: String
    lateinit var db: DatabaseReference
    lateinit var st: StorageReference

    override fun findAll(): List<HillfortModel> {
        return Hillforts
    }

    override fun findById(id: Long): HillfortModel? {
        val foundHillfort: HillfortModel? = Hillforts.find { p -> p.id == id }
        return foundHillfort
    }

    override fun create(Hillfort: HillfortModel) {
        val key = db.child("users").child(userId).child("Hillforts").push().key
        key?.let {
            Hillfort.fbId = key
            Hillforts.add(Hillfort)
            db.child("users").child(userId).child("Hillforts").child(key).setValue(Hillfort)
            updateImage(Hillfort)
        }
    }

    override fun update(Hillfort: HillfortModel) {
        var foundHillfort: HillfortModel? = Hillforts.find { p -> p.fbId == Hillfort.fbId }
        if (foundHillfort != null) {
            foundHillfort.title = Hillfort.title
            foundHillfort.description = Hillfort.description
            foundHillfort.image = Hillfort.image
            foundHillfort.location = Hillfort.location
        }

        db.child("users").child(userId).child("Hillforts").child(Hillfort.fbId).setValue(Hillfort)
        if ((Hillfort.image.length) > 0 && (Hillfort.image[0] != 'h')) {
            updateImage(Hillfort)
        }
    }

    override fun delete(Hillfort: HillfortModel) {
        db.child("users").child(userId).child("Hillforts").child(Hillfort.fbId).removeValue()
        Hillforts.remove(Hillfort)
    }

    override fun clear() {
        Hillforts.clear()
    }

    fun updateImage(Hillfort: HillfortModel) {
        if (Hillfort.image != "") {
            val fileName = File(Hillfort.image)
            val imageName = fileName.name

            var imageRef = st.child(userId + '/' + imageName)
            val baos = ByteArrayOutputStream()
            val bitmap = readImageFromPath(context, Hillfort.image)

            bitmap?.let {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                val uploadTask = imageRef.putBytes(data)
                uploadTask.addOnFailureListener {
                    println(it.message)
                }.addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                        Hillfort.image = it.toString()
                        db.child("users").child(userId).child("Hillforts").child(Hillfort.fbId).setValue(Hillfort)
                    }
                }
            }
        }
    }

    fun fetchHillforts(HillfortsReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.mapNotNullTo(Hillforts) { it.getValue<HillfortModel>(HillfortModel::class.java) }
                HillfortsReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseDatabase.getInstance().reference
        st = FirebaseStorage.getInstance().reference
        Hillforts.clear()
        db.child("users").child(userId).child("Hillforts").addListenerForSingleValueEvent(valueEventListener)
    }
}
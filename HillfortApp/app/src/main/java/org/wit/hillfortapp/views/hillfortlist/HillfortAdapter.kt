package org.wit.hillfortapp.views.hillfortlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_hillfort.view.*
import org.wit.hillfortapp.R
import org.wit.hillfortapp.models.HillfortModel

interface HillfortListener {
    fun onHillfortClick(Hillfort: HillfortModel)
}

class HillfortAdapter constructor(private var Hillforts: List<HillfortModel>,
                                  private val listener: HillfortListener
) : RecyclerView.Adapter<HillfortAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_hillfort,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val Hillfort = Hillforts[holder.adapterPosition]
        holder.bind(Hillfort, listener)
    }

    override fun getItemCount(): Int = Hillforts.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(Hillfort: HillfortModel, listener: HillfortListener) {
            itemView.HillfortTitle.text = Hillfort.title
            itemView.description.text = Hillfort.description
            Glide.with(itemView.context).load(Hillfort.image).into(itemView.imageIcon)
            itemView.setOnClickListener { listener.onHillfortClick(Hillfort) }
        }
    }
}
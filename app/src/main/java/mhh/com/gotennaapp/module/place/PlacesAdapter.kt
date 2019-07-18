package mhh.com.gotennaapp.module.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mhh.com.gotennaapp.R
import mhh.com.gotennaapp.databinding.AdapterPlacesBinding
import mhh.com.gotennaapp.model.Place

class PlacesAdapter(private val listOfPlaces: List<Place>?) : RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val adapterPlacesBinding = DataBindingUtil.inflate<AdapterPlacesBinding>(LayoutInflater.from(parent.context),
                R.layout.adapter_places, parent, false)

        return PlacesViewHolder(adapterPlacesBinding)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return listOfPlaces?.size ?: 0
    }

    inner class PlacesViewHolder(internal var adapterPlacesBinding: AdapterPlacesBinding) : RecyclerView.ViewHolder(adapterPlacesBinding.root) {

        fun bind(position: Int) {
            val place = listOfPlaces!![position]
            adapterPlacesBinding.place = place
        }
    }
}

package mhh.com.gotennaapp.module.place

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mhh.com.gotennaapp.R
import mhh.com.gotennaapp.databinding.FragmentListBinding
import mhh.com.gotennaapp.model.Place
import java.util.*

class PlaceListFragment : Fragment() {
    private var placesAdapter: PlacesAdapter? = null

    companion object {
        private val PLACE_LIST_KEY = "PLACE_LIST"

        fun getInstance(placeList: List<Place>): PlaceListFragment {
            val placeListFragment = PlaceListFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(PLACE_LIST_KEY, ArrayList<Parcelable>(placeList))
            placeListFragment.arguments = bundle
            return placeListFragment
        }
    }

    override fun onCreateView(
            layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentListBinding = DataBindingUtil.inflate<FragmentListBinding>(layoutInflater, R.layout.fragment_list, container, false)

        //set an adapter
        val placeArrayList = arguments!!.getParcelableArrayList<Place>(PLACE_LIST_KEY)
        placesAdapter = PlacesAdapter(placeArrayList)
        fragmentListBinding.rvPlaces.layoutManager = LinearLayoutManager(context)
        fragmentListBinding.rvPlaces.adapter = placesAdapter
        fragmentListBinding.rvPlaces.addItemDecoration(
                DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))

        return fragmentListBinding.root
    }

}

package mhh.com.gotennaapp.module.place;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import mhh.com.gotennaapp.R;
import mhh.com.gotennaapp.databinding.FragmentListBinding;
import mhh.com.gotennaapp.model.Place;

public class PlaceListFragment extends Fragment {
    private PlacesAdapter placesAdapter;
    private static final String PLACE_LIST_KEY = "PLACE_LIST";

    public PlaceListFragment() {
    }

    public static PlaceListFragment getInstance(List<Place> placeList) {
        PlaceListFragment placeListFragment = new PlaceListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PLACE_LIST_KEY, new ArrayList<Parcelable>(placeList));
        placeListFragment.setArguments(bundle);
        return placeListFragment;
    }

    @Override public View onCreateView(
        LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentListBinding fragmentListBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_list, container, false);

        //set an adapter
        ArrayList<Place> placeArrayList = getArguments().getParcelableArrayList(PLACE_LIST_KEY);
        placesAdapter = new PlacesAdapter(placeArrayList);
        fragmentListBinding.rvPlaces.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentListBinding.rvPlaces.setAdapter(placesAdapter);
        fragmentListBinding.rvPlaces.addItemDecoration(
            new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return fragmentListBinding.getRoot();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

package mhh.com.gotennaapp.module.place;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import mhh.com.gotennaapp.R;
import mhh.com.gotennaapp.databinding.AdapterPlacesBinding;
import mhh.com.gotennaapp.model.Place;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder> {
    private List<Place> listOfPlaces;

    public PlacesAdapter(List<Place> listOfPlaces) {
        this.listOfPlaces = listOfPlaces;
    }

    @NonNull @Override public PlacesViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType) {
        AdapterPlacesBinding adapterPlacesBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_places, parent, false);

        return new PlacesViewHolder(adapterPlacesBinding);
    }

    @Override public void onBindViewHolder(@NonNull PlacesViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override public int getItemCount() {
        if (listOfPlaces != null) return listOfPlaces.size();
        return 0;
    }

    public class PlacesViewHolder extends RecyclerView.ViewHolder {
        AdapterPlacesBinding adapterPlacesBinding;

        public PlacesViewHolder(AdapterPlacesBinding adapterPlacesBinding) {
            super(adapterPlacesBinding.getRoot());
            this.adapterPlacesBinding = adapterPlacesBinding;
        }

        public void bind(int position) {
            Place place = listOfPlaces.get(position);
            adapterPlacesBinding.setPlace(place);
        }
    }
}

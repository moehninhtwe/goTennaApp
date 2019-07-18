package mhh.com.gotennaapp.module.place;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.List;
import javax.inject.Inject;
import mhh.com.gotennaapp.common.GoTennaApplication;
import mhh.com.gotennaapp.model.Place;

public class PlaceViewModel extends AndroidViewModel {
    @Inject PlaceInteractor placeInteractor;
    public MutableLiveData<List<Place>> liveDataPlaces = new MutableLiveData<>();

    public PlaceViewModel() {
        super(GoTennaApplication.getGoTennaApplication());
        GoTennaApplication.appComponent.inject(this);
    }

    public LiveData<List<Place>> getPlaces(boolean isFromDatabase) {
        final LiveData<List<Place>> listOfPlaces = placeInteractor.getPlaces(isFromDatabase);
        listOfPlaces.observeForever(new Observer<List<Place>>() {
            @Override public void onChanged(List<Place> placeList) {
                liveDataPlaces.postValue(placeList);
                savePlaces(placeList);
            }
        });
        return liveDataPlaces;
    }

    public void savePlaces(List<Place> places) {
        placeInteractor.savePlaces(places);
    }
}

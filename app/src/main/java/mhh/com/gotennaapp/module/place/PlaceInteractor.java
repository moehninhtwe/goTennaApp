package mhh.com.gotennaapp.module.place;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import javax.inject.Inject;
import mhh.com.gotennaapp.dagger.AppComponent;
import mhh.com.gotennaapp.model.Place;
import mhh.com.gotennaapp.repo.DataRepo;

public class PlaceInteractor {
    @Inject DataRepo dataRepo;

    public PlaceInteractor(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public LiveData<List<Place>> getPlaces(boolean isFromDatabase) {
        if (isFromDatabase) return dataRepo.getPlacesFromDatabase();
        return dataRepo.getPlacesFromNetwork();
    }

    public void savePlaces(final List<Place> placeList) {
        AsyncTask.execute(new Runnable() {
            @Override public void run() {
                dataRepo.savePlaces(placeList);
            }
        });
    }
}

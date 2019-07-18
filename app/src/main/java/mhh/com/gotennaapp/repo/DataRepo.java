package mhh.com.gotennaapp.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import mhh.com.gotennaapp.api.APIService;
import mhh.com.gotennaapp.model.Place;
import mhh.com.gotennaapp.repo.dao.AppDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepo {
    private static DataRepo dataRepo;
    private List<Place> listOfPlaces;
    private AppDatabase appDatabase;

    private DataRepo(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public static DataRepo getInstance(AppDatabase appDatabase) {
        if (dataRepo == null) {
            synchronized (DataRepo.class) {
                if (dataRepo == null) {
                    dataRepo = new DataRepo(appDatabase);
                }
            }
        }
        return dataRepo;
    }

    public void savePlaces(List<Place> listOfPlaces) {
        appDatabase.getPlacesDao().insertPlaces(listOfPlaces);
    }

    public LiveData<List<Place>> getPlacesFromDatabase() {
        return appDatabase.getPlacesDao().getPlaces();
    }

    public LiveData<List<Place>> getPlacesFromNetwork() {
        final MutableLiveData<List<Place>> liveDataPlaces = new MutableLiveData<>();
        Call<List<Place>> places = APIService.provideGetPlacesService().getPlaces();
        places.enqueue(new Callback<List<Place>>() {
            @Override public void onResponse(
                Call<List<Place>> call, Response<List<Place>> response) {
                listOfPlaces = response.body();
                liveDataPlaces.postValue(listOfPlaces);
            }

            @Override public void onFailure(Call<List<Place>> call, Throwable t) {
            }
        });
        return liveDataPlaces;
    }
}

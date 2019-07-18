package mhh.com.gotennaapp.api;

import java.util.List;
import mhh.com.gotennaapp.model.Place;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPlacesService {
    @GET("/development/scripts/get_map_pins.php") Call<List<Place>> getPlaces();
}

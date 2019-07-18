package mhh.com.gotennaapp.api

import mhh.com.gotennaapp.model.Place
import retrofit2.Call
import retrofit2.http.GET

interface GetPlacesService {
    @get:GET("/development/scripts/get_map_pins.php")
    val places: Call<List<Place>>
}

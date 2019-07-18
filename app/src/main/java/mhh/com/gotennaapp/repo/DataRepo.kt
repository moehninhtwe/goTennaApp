package mhh.com.gotennaapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mhh.com.gotennaapp.api.APIService
import mhh.com.gotennaapp.model.Place
import mhh.com.gotennaapp.repo.dao.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepo private constructor(private val appDatabase: AppDatabase) {
    private var listOfPlaces: List<Place>? = null

    val placesFromDatabase: LiveData<List<Place>>
        get() = appDatabase.placesDao.places

    val placesFromNetwork: LiveData<List<Place>>
        get() {
            val liveDataPlaces = MutableLiveData<List<Place>>()
            val places = APIService.provideGetPlacesService().places
            places.enqueue(object : Callback<List<Place>> {
                override fun onResponse(
                        call: Call<List<Place>>, response: Response<List<Place>>) {
                    listOfPlaces = response.body()
                    liveDataPlaces.postValue(listOfPlaces)
                }

                override fun onFailure(call: Call<List<Place>>, t: Throwable) {}
            })
            return liveDataPlaces
        }

    fun savePlaces(listOfPlaces: List<Place>) {
        appDatabase.placesDao.insertPlaces(listOfPlaces)
    }

    companion object {
        private var dataRepo: DataRepo? = null

        fun getInstance(appDatabase: AppDatabase): DataRepo? {
            if (dataRepo == null) {
                synchronized(DataRepo::class.java) {
                    if (dataRepo == null) {
                        dataRepo = DataRepo(appDatabase)
                    }
                }
            }
            return dataRepo
        }
    }
}

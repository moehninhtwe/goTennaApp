package mhh.com.gotennaapp.repo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mhh.com.gotennaapp.model.Place

@Dao
interface PlacesDao {

    @get:Query("SELECT * FROM places")
    val places: LiveData<List<Place>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaces(places: List<Place>)

    @Query("DELETE FROM places")
    fun deleteAllPlaces()
}

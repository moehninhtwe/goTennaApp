package mhh.com.gotennaapp.repo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import mhh.com.gotennaapp.model.Place;

@Dao public interface PlacesDao {

    @Query("SELECT * FROM places") LiveData<List<Place>> getPlaces();

    @Insert(onConflict = OnConflictStrategy.REPLACE) void insertPlaces(List<Place> places);

    @Query("DELETE FROM places") void deleteAllPlaces();
}

package mhh.com.gotennaapp.repo.dao;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import mhh.com.gotennaapp.model.Place;

@Database(entities = { Place.class }, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                            "place_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return appDatabase;
    }

    public abstract PlacesDao getPlacesDao();
}

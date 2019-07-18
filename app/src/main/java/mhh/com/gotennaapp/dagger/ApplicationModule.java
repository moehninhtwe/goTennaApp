package mhh.com.gotennaapp.dagger;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import mhh.com.gotennaapp.common.GoTennaApplication;
import mhh.com.gotennaapp.common.ViewModelFactory;
import mhh.com.gotennaapp.module.place.PlaceInteractor;
import mhh.com.gotennaapp.repo.DataRepo;
import mhh.com.gotennaapp.repo.dao.AppDatabase;

@Module public class ApplicationModule {
    private final GoTennaApplication goTennaApplication;

    public ApplicationModule(GoTennaApplication goTennaApplication) {
        this.goTennaApplication = goTennaApplication;
    }

    @Provides @Singleton public ViewModelFactory provideViewModelFactory() {
        return new ViewModelFactory();
    }

    @Provides @Singleton public DataRepo provideDataRepo(AppDatabase appDatabase) {
        return DataRepo.Companion.getInstance(appDatabase);
    }

    @Provides @Singleton public AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context);
    }

    @Provides @Singleton public Context provideContext() {
        return goTennaApplication;
    }

    @Provides @Singleton public PlaceInteractor providePlaceInteractor() {
        return new PlaceInteractor(goTennaApplication.getAppComponent());
    }
}

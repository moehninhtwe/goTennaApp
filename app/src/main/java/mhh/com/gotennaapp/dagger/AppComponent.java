package mhh.com.gotennaapp.dagger;

import dagger.Component;
import javax.inject.Singleton;
import mhh.com.gotennaapp.MainActivity;
import mhh.com.gotennaapp.module.place.PlaceInteractor;
import mhh.com.gotennaapp.module.place.PlaceViewModel;

@Singleton @Component(modules = ApplicationModule.class) public interface AppComponent {
    void inject(PlaceInteractor placeInteractor);

    void inject(PlaceViewModel placeViewModel);

    void inject(MainActivity mainActivity);
}

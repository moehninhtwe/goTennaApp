package mhh.com.gotennaapp.common;

import android.app.Activity;
import androidx.multidex.MultiDexApplication;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;
import mhh.com.gotennaapp.dagger.AppComponent;
import mhh.com.gotennaapp.dagger.ApplicationModule;
import mhh.com.gotennaapp.dagger.DaggerAppComponent;

public class GoTennaApplication extends MultiDexApplication implements HasActivityInjector {
    public static AppComponent appComponent;
    private static GoTennaApplication goTennaApplication;
    @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static synchronized GoTennaApplication getGoTennaApplication() {
        return goTennaApplication;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override public void onCreate() {
        super.onCreate();
        goTennaApplication = this;
        appComponent = DaggerAppComponent.builder()
            .applicationModule(new ApplicationModule(goTennaApplication))
            .build();
    }

    @Override public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}

package mhh.com.gotennaapp.common;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import mhh.com.gotennaapp.module.place.PlaceViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public ViewModelFactory() {
    }

    @Override public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PlaceViewModel.class)) {
            return (T) new PlaceViewModel();
        }

        throw new IllegalArgumentException("Unknown View model class: " + modelClass.getName());
    }
}

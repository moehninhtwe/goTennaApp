package mhh.com.gotennaapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.List;
import javax.inject.Inject;
import mhh.com.gotennaapp.common.GoTennaApplication;
import mhh.com.gotennaapp.common.ViewModelFactory;
import mhh.com.gotennaapp.databinding.ActivityMainBinding;
import mhh.com.gotennaapp.model.Place;
import mhh.com.gotennaapp.module.place.ButtonClickListener;
import mhh.com.gotennaapp.module.place.PlaceListFragment;
import mhh.com.gotennaapp.module.place.PlaceViewModel;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    @Inject ViewModelFactory viewModelFactory;
    private PlaceViewModel placeViewModel;
    private List<Place> placeList;
    private FusedLocationProviderClient fusedLocationClient;
    private Location currentLocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private ActivityMainBinding activityMainBinding;
    private boolean isDefaultView = true;
    private String strBtnText;
    private static final String BTN_TEXT_KEY = "btn_text";
    private boolean isFromDatabase = false;

    @Override protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        GoTennaApplication.appComponent.inject(this);

        //get button text to set after screen rotation
        if (null != savedInstanceState) {
            strBtnText = savedInstanceState.getString(BTN_TEXT_KEY);
            activityMainBinding.setBtnText(strBtnText);
        }

        //check if network available to determine data source - either network or database
        isFromDatabase = !isNetworkConnected();

        //get current location
        if (hasCurrentLocationPermission()) getCurrentLocation();

        placeViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PlaceViewModel.class);

        placeViewModel.getPlaces(isFromDatabase).observe(this, new Observer<List<Place>>() {
            @Override public void onChanged(List<Place> places) {
                placeList = places;
                placeViewModel.getPlaces(isFromDatabase).removeObserver(this);

                if (null == savedInstanceState) setDefaultMapView();
            }
        });
        activityMainBinding.setListener(new ButtonClickListener() {
            @Override public void onClick() {
                isDefaultView = !isDefaultView;

                if (isDefaultView) {
                    setDefaultMapView();
                } else {
                    setListView();
                }
            }
        });
    }

    @Override public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(BTN_TEXT_KEY, strBtnText);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void setDefaultMapView() {
        SupportMapFragment mapFragment = new SupportMapFragment();
        mapFragment.setRetainInstance(true);
        mapFragment.getMapAsync(MainActivity.this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ll_fragment_container, mapFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        strBtnText = "Show in list";
        activityMainBinding.setBtnText(strBtnText);
    }

    private void setListView() {
        PlaceListFragment placeListFragment = PlaceListFragment.getInstance(placeList);
        placeListFragment.setRetainInstance(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ll_fragment_container, placeListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        strBtnText = "Show in Map";
        activityMainBinding.setBtnText(strBtnText);
    }

    private boolean hasCurrentLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION
            }, LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }

        return true;
    }

    private void getCurrentLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
            .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override public void onSuccess(Location location) {
                    if (location != null) {
                        currentLocation = location;
                    }
                }
            });
    }

    @Override public void onRequestPermissionsResult(
        int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Place place : placeList) {
            LatLng location = new LatLng(place.getLatitude(), place.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(location).title(place.getName()))
                .showInfoWindow();
            builder.include(location);
        }

        if (currentLocation != null) {
            LatLng current =
                new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(current).title("your are here"))
                .showInfoWindow();
            builder.include(current);
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 0));
    }
}

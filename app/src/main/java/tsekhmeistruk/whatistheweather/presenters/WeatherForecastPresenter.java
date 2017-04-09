package tsekhmeistruk.whatistheweather.presenters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.List;
import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tsekhmeistruk.whatistheweather.Constants;
import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.models.remote.IWeatherForecastDataSource;
import tsekhmeistruk.whatistheweather.utils.InternetConnectivityUtil;
import tsekhmeistruk.whatistheweather.utils.rx.RxErrorAction;
import tsekhmeistruk.whatistheweather.utils.rx.RxRetryWithDelay;
import tsekhmeistruk.whatistheweather.views.WeatherForecastView;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public class WeatherForecastPresenter extends BasePresenter<WeatherForecastView> {

    private final IWeatherForecastDataSource weatherForecastDataSource;

    public WeatherForecastPresenter(IWeatherForecastDataSource weatherForecastDataSource) {
        this.weatherForecastDataSource = weatherForecastDataSource;
    }

    private void loadWeatherForecast(double latitude,
                                     double longitude) {
        WeatherForecastView view = getView();
        subscribe(
                weatherForecastDataSource.getWeatherForecast(latitude, longitude)
                        .retryWhen(new RxRetryWithDelay())
                        .map(response -> response)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::showWeatherForecast,
                                new RxErrorAction(getView().getContext())
                        )
        );
    }

    private void setCityName(String name) {
        getView().setCityName(name);
    }

    private String getCityNameByCoordinates(double latitude, double longitude) {
        try {
            Geocoder geocoder = new Geocoder(getView().getContext(), Locale.US);
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses.size() > 0) {
                if (addresses.get(0).getLocality().length() != 0) {
                    return addresses.get(0).getLocality();
                } else {
                    return getView().getContext().getString(R.string.unknown_city);
                }
            } else {
                return getView().getContext().getString(R.string.unknown_city);
            }

        } catch (Exception e) {
            return "Unknown city";
        }
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            setCityName(getCityNameByCoordinates(location.getLatitude(), location.getLongitude()));
            loadWeatherForecast(location.getLatitude(), location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            if (provider.equals(LocationManager.GPS_PROVIDER)) {
                if (InternetConnectivityUtil.isConnected(getView().getContext())) {
                    getView().setLoadingLayoutVisibility(Constants.VISIBLE);
                    getView().setConnectionIsNeededLayoutVisibility(Constants.INVISIBLE);
                }
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            getView().setLoadingLayoutVisibility(Constants.INVISIBLE);
            getView().setWeatherTextLayoutVisibility(Constants.INVISIBLE);
            getView().setConnectionIsNeededLayoutVisibility(Constants.VISIBLE);
        }

    };

    public void getWeatherForecast(LocationManager locationManager) {
        if (ActivityCompat.checkSelfPermission(getView().getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getView().getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getView().askLocationPermissions();
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000 * 10, 60 * 5, locationListener);
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 1000 * 10, 60 * 5, locationListener);
        }
    }

}

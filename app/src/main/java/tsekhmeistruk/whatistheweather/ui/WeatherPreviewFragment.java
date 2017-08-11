package tsekhmeistruk.whatistheweather.ui;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tsekhmeistruk.whatistheweather.AppWhatIsTheWeather;
import tsekhmeistruk.whatistheweather.Constants;
import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.activities.FullWeatherForecastActivity;
import tsekhmeistruk.whatistheweather.activities.MainApplicationActivity;
import tsekhmeistruk.whatistheweather.di.component.AppComponent;
import tsekhmeistruk.whatistheweather.di.component.DaggerPresentersComponent;
import tsekhmeistruk.whatistheweather.di.module.PresentersModule;
import tsekhmeistruk.whatistheweather.models.entities.WeatherForecast;
import tsekhmeistruk.whatistheweather.presenters.WeatherForecastPresenter;
import tsekhmeistruk.whatistheweather.utils.InternetConnectivityUtil;
import tsekhmeistruk.whatistheweather.utils.LocationUtil;
import tsekhmeistruk.whatistheweather.views.WeatherForecastView;
import tsekhmeistruk.whatistheweather.widgets.adapters.WeatherListAdapter;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Roman Tsekhmeistruk on 27.03.2017.
 */

public class WeatherPreviewFragment extends Fragment implements WeatherForecastView {

    @BindView(R.id.weather_overviews_container)
    ListView weatherOverviewList;
    @BindView(R.id.current_city_name_fragment)
    TextView cityNameFragmentTextView;
    @BindView(R.id.internet_and_gps_is_needed_text_view)
    TextView connectionIsNeededTextView;

    @BindView(R.id.weather_in_city)
    LinearLayout weatherInCityLinearLayout;
    @BindView(R.id.loading)
    LinearLayout loadingLinearLayout;

    @BindView(R.id.loading_progress_bar)
    ProgressBar loadingProgressBar;

    @Inject
    WeatherForecastPresenter weatherForecastPresenter;

    private WeatherListAdapter weatherListAdapter;
    private LocationManager locationManager;
    private NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();

    public static WeatherPreviewFragment newInstance() {
        return new WeatherPreviewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        weatherForecastPresenter.setView(this);

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_preview, container, false);
        ButterKnife.bind(this, view);

        setConnectionIsNeededLayoutVisibility(Constants.INVISIBLE);
        setLoadingLayoutVisibility(Constants.INVISIBLE);
        setWeatherTextLayoutVisibility(Constants.INVISIBLE);

        Bundle extras = getArguments();
        if (extras != null) {
            if (InternetConnectivityUtil.isConnected(getContext())) {
                setLoadingLayoutVisibility(Constants.VISIBLE);
                weatherForecastPresenter.getWeatherForecast(extras.getString("name"),
                        extras.getDouble("latitude"),
                        extras.getDouble("longitude"));
            } else {
                showToast(getString(R.string.internet_is_needed));
            }
        } else {
            if (InternetConnectivityUtil.isConnected(getContext())) {
                weatherForecastPresenter.getWeatherForecast(locationManager);
            } else {
                showToast(getString(R.string.internet_is_needed));
                setConnectionIsNeededLayoutVisibility(Constants.VISIBLE);
            }

            if (!(LocationUtil.isEnabled(getContext()))) {
                showToast(getString(R.string.gps_is_needed));
                setConnectionIsNeededLayoutVisibility(Constants.VISIBLE);
                new AlertDialog.Builder(getActivity())
                        .setMessage(R.string.enable_gps_question)
                        .setNegativeButton("No", (arg0, arg1) -> {
                        })
                        .setPositiveButton("Yes", (arg0, arg1)
                                -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                        .create().show();
            }

            if ((InternetConnectivityUtil.isConnected(getContext()))
                    && (LocationUtil.isEnabled(getContext()))) {
                setLoadingLayoutVisibility(Constants.VISIBLE);
            }
        }

        weatherOverviewList.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity(), FullWeatherForecastActivity.class);
            intent.putExtra(Constants.FULL_WEATHER_INFORMATION,
                    weatherListAdapter.getItem(position));
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void showWeatherForecast(WeatherForecast weatherForecast) {
        setLoadingLayoutVisibility(Constants.INVISIBLE);
        setConnectionIsNeededLayoutVisibility(Constants.INVISIBLE);
        setWeatherTextLayoutVisibility(Constants.VISIBLE);

        weatherListAdapter
                = new WeatherListAdapter(getContext(), weatherForecast.getMainWeatherInfoList());
        weatherOverviewList.setAdapter(weatherListAdapter);
    }

    @Override
    public void setCityName(String cityName) {
        cityNameFragmentTextView.setText(cityName);
    }

    @Override
    public void askLocationPermissions() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    public void setLoadingLayoutVisibility(int visibility) {
        if (visibility == Constants.VISIBLE) {
            loadingLinearLayout.setVisibility(View.VISIBLE);
            loadingProgressBar.setVisibility(View.VISIBLE);
        } else {
            loadingLinearLayout.setVisibility(View.INVISIBLE);
            loadingProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setWeatherTextLayoutVisibility(int visibility) {
        if (visibility == Constants.VISIBLE) {
            weatherInCityLinearLayout.setVisibility(View.VISIBLE);
        } else {
            weatherInCityLinearLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setConnectionIsNeededLayoutVisibility(int visibility) {
        if (visibility == Constants.VISIBLE) {
            connectionIsNeededTextView.setVisibility(View.VISIBLE);
        } else {
            connectionIsNeededTextView.setVisibility(View.INVISIBLE);
        }
    }

    public AppComponent getAppComponent() {
        return ((AppWhatIsTheWeather) getActivity().getApplication()).appComponent();
    }

    private void showToast(String toastText) {
        Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {

        public NetworkChangeReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast()) {
                final ConnectivityManager connectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                final android.net.NetworkInfo wifi = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                final android.net.NetworkInfo mobile = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if (wifi.isConnected() || mobile.isConnected()) {
                    if (LocationUtil.isEnabled(getContext())) {
                        setConnectionIsNeededLayoutVisibility(Constants.INVISIBLE);
                        setLoadingLayoutVisibility(Constants.VISIBLE);
                    }
                    weatherForecastPresenter.getWeatherForecast(locationManager);
                } else {
                    setConnectionIsNeededLayoutVisibility(Constants.VISIBLE);
                    setWeatherTextLayoutVisibility(Constants.INVISIBLE);
                }
            }
        }

    }

}

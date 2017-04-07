package tsekhmeistruk.whatistheweather.ui;

import android.Manifest;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tsekhmeistruk.whatistheweather.AppWhatIsTheWeather;
import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.di.component.AppComponent;
import tsekhmeistruk.whatistheweather.di.component.DaggerPresentersComponent;
import tsekhmeistruk.whatistheweather.di.module.PresentersModule;
import tsekhmeistruk.whatistheweather.models.entities.WeatherForecast;
import tsekhmeistruk.whatistheweather.presenters.WeatherForecastPresenter;
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

    @Inject
    WeatherForecastPresenter weatherForecastPresenter;

    private LocationManager locationManager;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_preview, container, false);
        ButterKnife.bind(this, view);

        weatherForecastPresenter.getWeatherForecast(locationManager);

        return view;
    }

    @Override
    public void showWeatherForecast(WeatherForecast weatherForecast) {
        WeatherListAdapter weatherListAdapter
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

    public AppComponent getAppComponent() {
        return ((AppWhatIsTheWeather) getActivity().getApplication()).appComponent();
    }

}

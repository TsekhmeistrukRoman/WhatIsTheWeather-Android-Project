package tsekhmeistruk.whatistheweather.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import tsekhmeistruk.whatistheweather.AppWhatIsTheWeather;
import tsekhmeistruk.whatistheweather.Constants;
import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.di.component.AppComponent;
import tsekhmeistruk.whatistheweather.di.component.DaggerPresentersComponent;
import tsekhmeistruk.whatistheweather.di.module.PresentersModule;
import tsekhmeistruk.whatistheweather.models.entities.MainWeatherInfo;
import tsekhmeistruk.whatistheweather.models.entities.WeatherForecast;
import tsekhmeistruk.whatistheweather.presenters.WeatherForecastPresenter;
import tsekhmeistruk.whatistheweather.views.WeatherForecastView;

/**
 * Created by Roman Tsekhmeistruk on 27.03.2017.
 */

public class WeatherPreviewFragment extends BaseFragment implements WeatherForecastView {

    @Inject
    WeatherForecastPresenter weatherForecastPresenter;

    public static WeatherPreviewFragment newInstance() {
        WeatherPreviewFragment fragment = new WeatherPreviewFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        weatherForecastPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_preview, container, false);

        return view;
    }

}

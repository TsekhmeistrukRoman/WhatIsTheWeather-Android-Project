package tsekhmeistruk.whatistheweather.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.StreamAssetPathFetcher;

import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.models.entities.MainWeatherInfo;

/**
 * Created by Roman Tsekhmeistruk on 27.05.2017.
 */

public class FullWeatherForecastFragment extends Fragment {

    @BindView(R.id.full_weather_background)
    ImageView backgroundImage;
    @BindView(R.id.average_temperature_text_view)
    TextView averageTemperatureTextView;
    @BindView(R.id.weather_description_text_view)
    TextView shortWeatherDescriptionTextView;
    @BindView(R.id.date_text_view)
    TextView dateTextView;
    @BindView(R.id.night_temperature)
    TextView nightTemperatureTextView;
    @BindView(R.id.morning_temperature)
    TextView morningTemperatureTextView;
    @BindView(R.id.day_temperature)
    TextView dayTemperatureTextView;
    @BindView(R.id.evening_temperature)
    TextView eveningTemperatureTextView;
    @BindView(R.id.wind_speed_text_view)
    TextView windSpeedTextView;
    @BindView(R.id.humidity_text_view)
    TextView humidityTextView;
    @BindView(R.id.clouds_text_view)
    TextView cloudsTextView;

    public static FullWeatherForecastFragment newInstance() {
        return new FullWeatherForecastFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_weather_forecast, container, false);
        ButterKnife.bind(this, view);

        MainWeatherInfo weatherInfo
                = (MainWeatherInfo) getArguments().getSerializable("full_weather_information");

        Glide.with(getContext())
                .load(getContext().getResources().getIdentifier(
                        new StringBuilder(weatherInfo
                                .getWeather().get(0).getIcon()).reverse().toString() + "back",
                        "drawable",
                        getContext().getPackageName()))
                .centerCrop().fitCenter().into(backgroundImage);

        averageTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getDayTemperature().intValue()
                        + getContext().getString(R.string.celsius)));
        shortWeatherDescriptionTextView.setText(
                String.valueOf(weatherInfo.getWeather().get(0).getDescription()));

        Date forecastDate = new Date((long) weatherInfo.getTime() * 1000);
        dateTextView.setText(String.valueOf(forecastDate.toString().substring(4, 10)));

        nightTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getNightTemperature().intValue()
                        + getContext().getString(R.string.celsius)));
        morningTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getMorningTemperature().intValue()
                        + getContext().getString(R.string.celsius)));
        dayTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getDayTemperature().intValue()
                        + getContext().getString(R.string.celsius)));
        eveningTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getEveningTemperature().intValue()
                        + getContext().getString(R.string.celsius)));

        windSpeedTextView.setText(String.valueOf(weatherInfo.getSpeed()));

        if (weatherInfo.getHumidity() != 0) {
            humidityTextView.setText(String.valueOf(
                    weatherInfo.getHumidity()));
        } else {
            humidityTextView.setText(String.valueOf(
                    new Random().nextInt(15) + 48));
        }

        cloudsTextView.setText(String.valueOf(weatherInfo.getClouds()));

        return view;
    }

}

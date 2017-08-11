package tsekhmeistruk.whatistheweather.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import tsekhmeistruk.whatistheweather.Constants;
import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.models.entities.MainWeatherInfo;

/**
 * Created by Roman Tsekhmeistruk on 11.08.2017.
 */

public class FullWeatherForecastActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_weather_forecast);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        MainWeatherInfo weatherInfo
                = (MainWeatherInfo) intent.getExtras().getSerializable(Constants.FULL_WEATHER_INFORMATION);

        Glide.with(this)
                .load(getResources().getIdentifier(
                        new StringBuilder(weatherInfo
                                .getWeather().get(0).getIcon()).reverse().toString() + "back",
                        "drawable",
                        getPackageName()))
                .centerCrop().fitCenter().into(backgroundImage);

        averageTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getDayTemperature().intValue()
                        + getString(R.string.celsius)));
        shortWeatherDescriptionTextView.setText(
                String.valueOf(weatherInfo.getWeather().get(0).getDescription()));

        Date forecastDate = new Date((long) weatherInfo.getTime() * 1000);
        dateTextView.setText(String.valueOf(forecastDate.toString().substring(4, 10)));

        nightTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getNightTemperature().intValue()
                        + getString(R.string.celsius)));
        morningTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getMorningTemperature().intValue()
                        + getString(R.string.celsius)));
        dayTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getDayTemperature().intValue()
                        + getString(R.string.celsius)));
        eveningTemperatureTextView.setText(String.valueOf(
                weatherInfo.getTemperature().getEveningTemperature().intValue()
                        + getString(R.string.celsius)));

        windSpeedTextView.setText(String.valueOf(weatherInfo.getSpeed()));

        if (weatherInfo.getHumidity() != 0) {
            humidityTextView.setText(String.valueOf(
                    weatherInfo.getHumidity()));
        } else {
            humidityTextView.setText(String.valueOf(
                    new Random().nextInt(15) + 48));
        }

        cloudsTextView.setText(String.valueOf(weatherInfo.getClouds()));
    }

}

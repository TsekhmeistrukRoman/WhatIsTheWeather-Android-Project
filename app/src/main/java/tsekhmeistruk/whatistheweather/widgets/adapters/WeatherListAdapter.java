package tsekhmeistruk.whatistheweather.widgets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.List;
import java.util.Random;

import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.models.entities.MainWeatherInfo;

/**
 * Created by Roman Tsekhmeistruk on 05.04.2017.
 */

public class WeatherListAdapter extends BaseAdapter {

    private List<MainWeatherInfo> weatherInfoList;
    private Context applicationContext;

    public WeatherListAdapter(Context applicationContext, List<MainWeatherInfo> weatherInfoList) {
        this.weatherInfoList = weatherInfoList;
        this.applicationContext = applicationContext;
    }

    @Override
    public int getCount() {
        return weatherInfoList.size();
    }

    @Override
    public MainWeatherInfo getItem(int position) {
        return weatherInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_weather_overview, parent, false);
        }

        TextView day = (TextView) convertView.findViewById(R.id.day_text_view);
        TextView date = (TextView) convertView.findViewById(R.id.date_text_view);

        TextView description
                = (TextView) convertView.findViewById(R.id.short_weather_description_text_view);
        ImageView icon = (ImageView) convertView.findViewById(R.id.weather_icon_image_view);

        TextView temperature = (TextView) convertView.findViewById(R.id.main_temperature_text_view);

        TextView windSpeed = (TextView) convertView.findViewById(R.id.wind_speed_text_view);
        TextView humidity = (TextView) convertView.findViewById(R.id.humidity_text_view);

        Date forecastDate = new Date((long) weatherInfoList.get(position).getTime() * 1000);
        day.setText(String.valueOf(forecastDate.toString().substring(0, 3)));
        date.setText(String.valueOf(forecastDate.toString().substring(4, 10)));

        description.setText(String.valueOf(
                weatherInfoList.get(position).getWeather().get(0).getDescription()));

        Glide.with(applicationContext)
                .load(applicationContext.getResources().getIdentifier(
                        new StringBuilder(weatherInfoList.get(position)
                                .getWeather().get(0).getIcon()).reverse().toString(),
                        "drawable",
                        applicationContext.getPackageName()))
                .centerCrop().fitCenter().into(icon);

        temperature.setText(String.valueOf(
                weatherInfoList.get(position).getTemperature().getDayTemperature().intValue()));

        windSpeed.setText(String.valueOf(
                weatherInfoList.get(position).getSpeed()));

        if (weatherInfoList.get(position).getHumidity() != 0) {
            humidity.setText(String.valueOf(
                    weatherInfoList.get(position).getHumidity()));
        } else {
            humidity.setText(String.valueOf(
                    new Random().nextInt(15) + 48));
        }

        return convertView;
    }

}

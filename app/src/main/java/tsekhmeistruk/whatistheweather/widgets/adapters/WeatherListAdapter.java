package tsekhmeistruk.whatistheweather.widgets.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import tsekhmeistruk.whatistheweather.R;
import tsekhmeistruk.whatistheweather.models.entities.MainWeatherInfo;

/**
 * Created by Roman Tsekhmeistruk on 05.04.2017.
 */

public class WeatherListAdapter extends BaseAdapter {

    private List<MainWeatherInfo> weatherInfoList;

    public WeatherListAdapter(List<MainWeatherInfo> weatherInfoList) {
        this.weatherInfoList = weatherInfoList;
    }

    @Override
    public int getCount() {
        return weatherInfoList.size();
    }

    @Override
    public Object getItem(int position) {
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

        return convertView;
    }

}

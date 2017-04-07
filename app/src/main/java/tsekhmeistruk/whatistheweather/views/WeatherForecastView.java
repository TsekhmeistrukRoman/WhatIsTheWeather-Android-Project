package tsekhmeistruk.whatistheweather.views;

import tsekhmeistruk.whatistheweather.models.entities.WeatherForecast;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public interface WeatherForecastView extends BaseView {

    void showWeatherForecast(WeatherForecast weatherForecast);

    void setCityName(String cityName);

    void askLocationPermissions();

    void setLoadingLayoutVisibility(int visibility);

    void setWeatherTextLayoutVisibility(int visibility);

    void setConnectionIsNeededLayoutVisibility(int visibility);

}

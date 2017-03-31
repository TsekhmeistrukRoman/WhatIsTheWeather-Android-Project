package tsekhmeistruk.whatistheweather.models.remote;

import rx.Observable;
import tsekhmeistruk.whatistheweather.api.OpenWeatherApiSet;
import tsekhmeistruk.whatistheweather.models.entities.WeatherForecast;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public class WeatherForecastDataSource implements IWeatherForecastDataSource {

    private OpenWeatherApiSet openWeatherApiSet;

    public WeatherForecastDataSource(OpenWeatherApiSet openWeatherApiSet) {
        this.openWeatherApiSet = openWeatherApiSet;
    }

    @Override
    public Observable<WeatherForecast> getWeatherForecast(double latitude,
                                                          double longitude,
                                                          int daysNumber,
                                                          String mode,
                                                          String appId,
                                                          String units) {
        return openWeatherApiSet.getWeatherForecast(latitude, longitude,
                daysNumber, mode,
                appId, units);
    }

}

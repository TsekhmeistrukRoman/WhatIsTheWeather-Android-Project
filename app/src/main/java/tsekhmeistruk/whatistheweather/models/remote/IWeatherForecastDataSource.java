package tsekhmeistruk.whatistheweather.models.remote;

import rx.Observable;
import tsekhmeistruk.whatistheweather.models.entities.WeatherForecast;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public interface IWeatherForecastDataSource {

    Observable<WeatherForecast> getWeatherForecast(double latitude,
                                                   double longitude);

}

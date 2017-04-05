package tsekhmeistruk.whatistheweather.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import tsekhmeistruk.whatistheweather.Constants;
import tsekhmeistruk.whatistheweather.models.entities.WeatherForecast;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public interface OpenWeatherApiSet {

    @GET("data/2.5/forecast/daily?cnt=14&mode=json&appid=" + Constants.OPEN_WEATHER_API_KEY + "&units=metric")
    Observable<WeatherForecast> getWeatherForecast(@Query("lat") double latitude,
                                                   @Query("lon") double longitude);

}

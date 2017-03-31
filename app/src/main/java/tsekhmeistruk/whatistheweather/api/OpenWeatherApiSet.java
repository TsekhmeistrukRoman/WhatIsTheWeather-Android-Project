package tsekhmeistruk.whatistheweather.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import tsekhmeistruk.whatistheweather.models.entities.WeatherForecast;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public interface OpenWeatherApiSet {

    @GET("data/2.5/forecast/daily")
    Observable<WeatherForecast> getWeatherForecast(@Query("lat") double latitude,
                                                   @Query("lon") double longitude,
                                                   @Query("cnt") int daysNumber,
                                                   @Query("mode") String mode,
                                                   @Query("appid") String appId,
                                                   @Query("units") String units);

}

package tsekhmeistruk.whatistheweather.di.component;

import retrofit2.Retrofit;
import tsekhmeistruk.whatistheweather.models.remote.IWeatherForecastDataSource;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

public interface ApiComponent {

    Retrofit retrofit();

    IWeatherForecastDataSource weatherForecastDataSource();

}

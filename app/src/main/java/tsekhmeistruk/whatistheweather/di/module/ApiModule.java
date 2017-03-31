package tsekhmeistruk.whatistheweather.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tsekhmeistruk.whatistheweather.Constants;
import tsekhmeistruk.whatistheweather.api.OpenWeatherApiSet;
import tsekhmeistruk.whatistheweather.models.remote.IWeatherForecastDataSource;
import tsekhmeistruk.whatistheweather.models.remote.WeatherForecastDataSource;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.OPEN_WEATHER_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    IWeatherForecastDataSource provideWeatherForecastDataSource(Retrofit retrofit) {
        return new WeatherForecastDataSource(retrofit.create(OpenWeatherApiSet.class));
    }

}

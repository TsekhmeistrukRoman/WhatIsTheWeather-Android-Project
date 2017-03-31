package tsekhmeistruk.whatistheweather.di.module;

import dagger.Module;
import dagger.Provides;
import tsekhmeistruk.whatistheweather.di.scopes.Scope;
import tsekhmeistruk.whatistheweather.di.scopes.Scopes;
import tsekhmeistruk.whatistheweather.models.remote.IWeatherForecastDataSource;
import tsekhmeistruk.whatistheweather.presenters.WeatherForecastPresenter;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

@Module
public class PresentersModule {

    @Provides
    @Scope(Scopes.VIEW)
    public WeatherForecastPresenter provideAudioPlayerPresenter(IWeatherForecastDataSource weatherForecastDataSource) {
        return new WeatherForecastPresenter(weatherForecastDataSource);
    }

}

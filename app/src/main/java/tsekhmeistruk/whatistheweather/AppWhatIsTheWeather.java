package tsekhmeistruk.whatistheweather;

import android.support.multidex.MultiDexApplication;

import tsekhmeistruk.whatistheweather.di.component.AppComponent;
import tsekhmeistruk.whatistheweather.di.component.DaggerAppComponent;
import tsekhmeistruk.whatistheweather.di.module.ApiModule;
import tsekhmeistruk.whatistheweather.di.module.AppModule;

/**
 * Created by Roman Tsekhmeistruk on 27.03.2017.
 */

public class AppWhatIsTheWeather extends MultiDexApplication {

    private AppComponent appComponent;

    public AppWhatIsTheWeather() {
        super();

        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }

}

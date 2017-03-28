package tsekhmeistruk.whatistheweather.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;
import tsekhmeistruk.whatistheweather.di.module.ApiModule;
import tsekhmeistruk.whatistheweather.di.module.AppModule;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class
})
public interface AppComponent extends ApiComponent {

    Context context();

    SharedPreferences preferences();

    Executor executor();

}

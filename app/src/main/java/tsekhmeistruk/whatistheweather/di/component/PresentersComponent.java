package tsekhmeistruk.whatistheweather.di.component;

import dagger.Component;
import tsekhmeistruk.whatistheweather.di.module.PresentersModule;
import tsekhmeistruk.whatistheweather.di.scopes.Scope;
import tsekhmeistruk.whatistheweather.di.scopes.Scopes;
import tsekhmeistruk.whatistheweather.ui.WeatherPreviewFragment;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

@Scope(Scopes.VIEW)
@Component(
        modules = {PresentersModule.class},
        dependencies = {AppComponent.class}
)
public interface PresentersComponent {

    void inject(WeatherPreviewFragment view);

}

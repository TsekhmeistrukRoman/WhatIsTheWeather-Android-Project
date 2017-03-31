package tsekhmeistruk.whatistheweather.presenters;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tsekhmeistruk.whatistheweather.models.remote.IWeatherForecastDataSource;
import tsekhmeistruk.whatistheweather.utils.rx.RxErrorAction;
import tsekhmeistruk.whatistheweather.utils.rx.RxRetryWithDelay;
import tsekhmeistruk.whatistheweather.views.WeatherForecastView;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public class WeatherForecastPresenter extends BasePresenter<WeatherForecastView> {

    private final IWeatherForecastDataSource weatherForecastDataSource;

    public WeatherForecastPresenter(IWeatherForecastDataSource weatherForecastDataSource) {
        this.weatherForecastDataSource = weatherForecastDataSource;
    }

    public void loadWeatherForecast(double latitude,
                                    double longitude,
                                    int daysNumber,
                                    String mode,
                                    String appId,
                                    String units) {
        WeatherForecastView view = getView();
        subscribe(
                weatherForecastDataSource.getWeatherForecast(latitude, longitude,
                        daysNumber, mode,
                        appId, units)
                        .retryWhen(new RxRetryWithDelay())
                        .map(response -> response)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::showWeatherForecast,
                                new RxErrorAction(getView().getContext())
                        )
        );
    }

}

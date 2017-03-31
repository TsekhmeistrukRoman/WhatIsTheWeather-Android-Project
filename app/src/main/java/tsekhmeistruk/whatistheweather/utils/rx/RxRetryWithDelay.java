package tsekhmeistruk.whatistheweather.utils.rx;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Roman Tsekhmeistruk on 27.02.2017.
 */

public class RxRetryWithDelay implements
        Func1<Observable<? extends Throwable>, Observable<?>> {

    private int maxRetries = 3;
    private long retryDelayMillis = 500;
    private int retryCount;

    public RxRetryWithDelay() {
    }

    public RxRetryWithDelay(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public RxRetryWithDelay(long retryDelayMillis) {
        this.retryDelayMillis = retryDelayMillis;
    }

    public RxRetryWithDelay(int maxRetries, long retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts
                .flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (++retryCount < maxRetries) {
                            // When this Observable calls onNext, the original
                            // Observable will be retried (i.e. re-subscribed).
                            return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
                        }

                        // Max retries hit. Just pass the error along.
                        return Observable.error(throwable);
                    }
                });
    }

}
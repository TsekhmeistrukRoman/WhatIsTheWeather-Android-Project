package tsekhmeistruk.whatistheweather.presenters;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import tsekhmeistruk.whatistheweather.views.BaseView;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

public class BasePresenter<T extends BaseView> {

    private T mView;

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public T getView() {
        return mView;
    }

    public void setView(T view) {
        mView = view;
    }

    public void destroy() {
        mCompositeSubscription.clear();
    }

    protected Subscription subscribe(Subscription subscription) {
        mCompositeSubscription.add(subscription);
        return subscription;
    }

}

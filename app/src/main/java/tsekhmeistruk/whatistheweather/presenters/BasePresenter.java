package tsekhmeistruk.whatistheweather.presenters;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import tsekhmeistruk.whatistheweather.views.BaseView;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

public class BasePresenter<T extends BaseView> {

    private T view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void destroy() {
        compositeSubscription.clear();
    }

    protected Subscription subscribe(Subscription subscription) {
        compositeSubscription.add(subscription);
        return subscription;
    }

}

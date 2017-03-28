package tsekhmeistruk.whatistheweather.utils;

import android.content.Context;
import android.widget.Toast;

import rx.functions.Action1;

/**
 * Created by Roman Tsekhmeistruk on 27.02.2017.
 */

public class RxErrorAction implements Action1<Throwable> {

    private final Context mContext;

    public RxErrorAction(Context context) {
        mContext = context;
    }

    @Override
    public void call(Throwable throwable) {
        throwable.printStackTrace();

        Toast.makeText(mContext, "Request execution has failed", Toast.LENGTH_SHORT).show();
    }

}
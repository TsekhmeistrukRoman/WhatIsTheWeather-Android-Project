package tsekhmeistruk.whatistheweather.utils.rx;

import android.content.Context;
import android.widget.Toast;

import rx.functions.Action1;
import tsekhmeistruk.whatistheweather.R;

/**
 * Created by Roman Tsekhmeistruk on 27.02.2017.
 */

public class RxErrorAction implements Action1<Throwable> {

    private final Context context;

    public RxErrorAction(Context context) {
        this.context = context;
    }

    @Override
    public void call(Throwable throwable) {
        throwable.printStackTrace();

        Toast.makeText(context, context.getString(R.string.request_execution_has_failed),
                Toast.LENGTH_SHORT).show();
    }

}
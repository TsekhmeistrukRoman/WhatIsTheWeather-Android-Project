package tsekhmeistruk.whatistheweather.widgets.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import tsekhmeistruk.whatistheweather.utils.TypefaceCache;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

public class FontTextView extends TextView {

    public FontTextView(Context context) {
        this(context, null);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) {
            setTypeface(TypefaceCache.getTypeface(context, attrs));
        }
    }

}

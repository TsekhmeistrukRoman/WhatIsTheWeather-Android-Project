package tsekhmeistruk.whatistheweather.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import java.util.WeakHashMap;

import tsekhmeistruk.whatistheweather.R;

/**
 * Created by Roman Tsekhmeistruk on 06.04.2017.
 */

public class TypefaceCache {

    private static final String DEFAULT = "Montserrat-Regular.ttf";

    private static WeakHashMap<String, Typeface> cache;

    private static Typeface getTypeface(Context context, String typefaceName) {
        if (cache == null) {
            cache = new WeakHashMap<>(5);
        }

        Typeface tf = cache.get(typefaceName);
        if (tf == null) {
            tf = Typeface.createFromAsset(context.getAssets(),
                    String.format("fonts/%s", typefaceName));
            cache.put(typefaceName, tf);
        }

        return tf;
    }

    public static Typeface getTypeface(Context context, AttributeSet attrs) {
        String fontName = DEFAULT;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Localized);
        if (a.hasValue(R.styleable.Localized_font)) {
            fontName = a.getString(R.styleable.Localized_font);
        }
        a.recycle();

        return getTypeface(context, fontName);
    }

}

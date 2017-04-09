package tsekhmeistruk.whatistheweather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public class InternetConnectivityUtil {

    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo info = InternetConnectivityUtil.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

}

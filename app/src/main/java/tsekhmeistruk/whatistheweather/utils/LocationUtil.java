package tsekhmeistruk.whatistheweather.utils;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by Roman Tsekhmeistruk on 31.03.2017.
 */

public class LocationUtil {

    public static boolean isEnabled(Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        return ((locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                && (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)));
    }

}

package tsekhmeistruk.whatistheweather.di.scopes;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 * Constants variables for modules and providers.
 */

public class Scopes {

    /** Lifecycle scope annotation constants. */
    public static final String ACTIVITY  = "activity";
    public static final String SERVICE   = "service";
    public static final String FRAGMENT  = "fragment";
    public static final String VIEW      = "view";

    private Scopes() {
        throw new AssertionError("Unable to instantiate");
    }

}

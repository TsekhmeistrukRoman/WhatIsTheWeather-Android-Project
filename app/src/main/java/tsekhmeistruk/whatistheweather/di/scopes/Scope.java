package tsekhmeistruk.whatistheweather.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 * Scope annotation for different scopes.
 */

@javax.inject.Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    String value() default "";
}

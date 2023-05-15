package io.github.aquerr.steamwebapiclient.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark a field in request class to be used as request query param.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SteamRequestQueryParam
{
    /**
     * The name of the query param that should be used while performing the request.
     *
     * Empty string will result in field name being used as query param name.
     */
    String value() default  "";
}

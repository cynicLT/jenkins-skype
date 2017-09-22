package com.alna.software.ci.utils;

import com.alna.software.ci.exception.JenkinsApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * Jenkins validatorius su klaidų kodais
 */
public final class JenkinsValidate extends Validate {

    public static <T extends CharSequence> T notEmptyWithCode(T chars, String code) {
        if (StringUtils.isNotEmpty(chars)) {
            return chars;
        } else {
            throw new JenkinsApplicationException(code);
        }
    }

    public static <T> T notNullWithCode(T object, String code, Object... values) {
        if (object != null) {
            return object;
        } else {
            throw new JenkinsApplicationException(code, values);
        }
    }

    public static <T> boolean isAssignable(Object object, Class<T> clazz) {
        notNull(object, "Object to check is mandatory");
        notNull(clazz, "Destination class is mandatory");

        return clazz.isAssignableFrom(object.getClass());
    }

}

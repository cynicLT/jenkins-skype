package com.alna.software.ci.utils;

import java.util.List;

/**
 * Cast klase
 */
public final class JenkinsCast {
    private JenkinsCast() {
    }

    /**
     * Vertimas į List
     *
     * @param object objektas
     * @param <T>    List tipas
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> cast(Object object) {
        return List.class.cast(object);
    }

    /**
     * Verimas į pasirinkto tipo objektą
     *
     * @param object objektas
     * @param clazz  tipas
     * @param <T>    tipas
     * @return pasirinktas tipas
     */
    public static <T> T cast(Object object, Class<T> clazz) {
        JenkinsValidate.notNull(object, "Object to cast is mandatory");
        JenkinsValidate.notNull(clazz, "Destination class is mandatory");

        JenkinsValidate.isAssignableFrom(clazz, object.getClass(), "Unable to cast [%s] to [%s]", object.getClass().getSimpleName(), clazz.getSimpleName());

        return clazz.cast(object);
    }

}

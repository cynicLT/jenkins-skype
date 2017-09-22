package com.alna.software.ci.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.concurrent.atomic.AtomicReference;

public class JenkinsOptional<T> {
    /**
     * Objektas
     */
    private AtomicReference<T> object;

    /**
     * Konstruktorius
     *
     * @param object objektas
     */
    private JenkinsOptional(T object) {
        this.object = new AtomicReference<>(object);
    }


    /**
     * Inicijavimas tipo
     *
     * @param object objektas
     * @param <T>    tipas
     * @return tipas
     */
    public static <T> JenkinsOptional<T> of(T object) {
        return new JenkinsOptional<>(object);
    }

    /**
     * Inicijavimas tipo
     *
     * @param <T> tipas
     * @return tipas
     */
    public static <T> JenkinsOptional<T> empty() {
        return new JenkinsOptional<>(null);
    }

    /**
     * Ar tusčias
     *
     * @return taip/ne
     */
    public boolean isEmpty() {
        return object.get() == null;
    }

    /**
     * Ar egzistuoja
     *
     * @return taip/ne
     */
    public boolean exists() {
        return !isEmpty();
    }

    /**
     * Gauti netusčią objektą
     *
     * @return objektas
     */
    public T get() {
        JenkinsValidate.notNull(object.get());

        return object.get();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("object", object)
                .toString();
    }
}

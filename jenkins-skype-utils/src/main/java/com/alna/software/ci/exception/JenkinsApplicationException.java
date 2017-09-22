package com.alna.software.ci.exception;

import com.alna.software.ci.utils.JenkinsValidate;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Aplikacijos klaidos
 */
public class JenkinsApplicationException extends IllegalArgumentException {
    /**
     * Pranešymo kodas
     */
    private String code;

    /**
     * Pranešymo parametrai
     */
    private Object[] values;

    public JenkinsApplicationException(String code, Object... values) {
        super(code);
        this.code = Validate.notEmpty(code, "Error code must be provided");

        if (ArrayUtils.isNotEmpty(values)) {
            this.values = ObjectUtils.cloneIfPossible(values);
        }
    }

    public JenkinsApplicationException withCause(Throwable cause) {
        initCause(JenkinsValidate.notNull(cause, "Cause must be provided"));

        return this;
    }

    public String getCode() {
        return code;
    }

    public Object[] getValues() {
        return ObjectUtils.cloneIfPossible(values);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("code", code)
                .append("values", values)
                .toString();
    }
}

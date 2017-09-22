package com.alna.software.ci.utils;

import com.alna.software.ci.exception.JenkinsApplicationException;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * AVP pranešymai
 */
public final class JenkinsMessage {
    private JenkinsMessage() {
    }


    /**
     * Gauti klaidos pranešymą pagal klaidą
     *
     * @param throwable klaida
     * @return klaidos pranešymas
     */
    public static String getMessageBy(Throwable throwable, MessageSourceAccessor messageSourceAccessor) {
        JenkinsValidate.notNull(throwable, "Exception must be provided");
        JenkinsValidate.notNull(messageSourceAccessor, "MessageSourceAccessor must be provided");

        Class<?> exceptionClazz = throwable.getClass();

        String message;

        if (JenkinsApplicationException.class.isAssignableFrom(exceptionClazz)) {
            message = getApplicationMessage(JenkinsCast.cast(throwable, JenkinsApplicationException.class), messageSourceAccessor);
        } else {
            message = getUnknownMessage(throwable, messageSourceAccessor);
        }

        return message;
    }

    /**
     * Gauti nežinomos klaidos tekstą
     *
     * @param throwable klaida
     * @return klaidos tekstas
     */
    private static String getUnknownMessage(Throwable throwable, MessageSourceAccessor messageSourceAccessor) {
        return messageSourceAccessor.getMessage("error.unknown", new Object[]{throwable.getMessage()});
    }

    /**
     * Gauti aplikacijos klaidos tekstą
     *
     * @param evapApplicationException aplikacijos klaida
     * @return klaidos tekstas
     */
    private static String getApplicationMessage(JenkinsApplicationException evapApplicationException, MessageSourceAccessor messageSourceAccessor) {
        return messageSourceAccessor.getMessage(evapApplicationException.getCode(), evapApplicationException.getValues());
    }
}

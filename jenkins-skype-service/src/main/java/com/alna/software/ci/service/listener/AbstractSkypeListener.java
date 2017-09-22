package com.alna.software.ci.service.listener;

import com.alna.software.ci.exception.JenkinsApplicationException;
import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

abstract class AbstractSkypeListener implements ChatMessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSkypeListener.class);

    @Override
    public final void chatMessageReceived(ChatMessage chatMessage) throws SkypeException {
        LOGGER.info("chatMessageReceived({})", chatMessage);
        try {
            if (isValidMessage(chatMessage)) {

                receivedMessage(chatMessage);
            }
        } catch (SkypeException e) {
            throw new JenkinsApplicationException("error.skype.send.message").withCause(e);
        }
    }

    private boolean isValidMessage(ChatMessage chatMessage) throws SkypeException {
        boolean result = false;

        if (ChatMessage.Status.RECEIVED.equals(chatMessage.getStatus())) {
            if (ChatMessage.Type.SAID.equals(chatMessage.getType())) {
                Date data = chatMessage.getTime();

                if (DateUtils.truncatedCompareTo(DateUtils.addMinutes(data, 10), new Date(), Calendar.MINUTE) >= 0) {
                    result = true;
                }
            }
        }

        return result;
    }

    @Override
    public final void chatMessageSent(ChatMessage chatMessage) throws SkypeException {

    }

    protected abstract void receivedMessage(ChatMessage chatMessage) throws SkypeException;
}

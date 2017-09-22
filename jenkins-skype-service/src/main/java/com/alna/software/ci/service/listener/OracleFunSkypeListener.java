package com.alna.software.ci.service.listener;

import com.skype.ChatMessage;
import com.skype.SkypeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Oracle fun commands
 */
@Service
public class OracleFunSkypeListener extends AbstractSkypeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleFunSkypeListener.class);

    private static final List<String> MESSAGES = Collections.singletonList(
            "Ir vel 'dombazistai' prisidirbo |-("
    );

    @Override
    protected void receivedMessage(ChatMessage chatMessage) throws SkypeException {
        LOGGER.info("executeCommand({})", chatMessage);

        if (StringUtils.containsIgnoreCase(chatMessage.getContent(), "ORA-")) {
            chatMessage.getChat().send(MESSAGES.get(ThreadLocalRandom.current().nextInt(MESSAGES.size())));
        }
    }
}

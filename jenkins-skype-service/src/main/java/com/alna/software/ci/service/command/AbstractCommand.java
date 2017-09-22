package com.alna.software.ci.service.command;

import com.alna.software.ci.exception.JenkinsApplicationException;
import com.alna.software.ci.service.SkypeService;
import com.skype.ChatMessage;
import com.skype.SkypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommand.class);

    @Autowired
    private SkypeService skypeService;

    protected abstract void executeCommand(ChatMessage chatMessage) throws SkypeException;

    @Override
    public void execute(ChatMessage chatMessage) {
        LOGGER.info("execute({})", chatMessage);

        skypeService.checkSkype();

        try {
            executeCommand(chatMessage);
        } catch (SkypeException e) {
            throw new JenkinsApplicationException("error.skype.send.message").withCause(e);
        }
    }
}

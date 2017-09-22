package com.alna.software.ci.service.command.impl;

import com.alna.software.ci.service.command.AbstractCommand;
import com.skype.ChatMessage;
import com.skype.SkypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VersionCommand extends AbstractCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionCommand.class);

    @Override
    protected void executeCommand(ChatMessage chatMessage) throws SkypeException {
        LOGGER.info("executeCommand({})", chatMessage);

        chatMessage.
                getSender().
                send(getClass().getPackage().getImplementationVersion());

    }

    @Override
    public String getCommandName() {
        return "#version";
    }
}

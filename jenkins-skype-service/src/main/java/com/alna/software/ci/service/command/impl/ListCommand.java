package com.alna.software.ci.service.command.impl;

import com.alna.software.ci.exception.JenkinsApplicationException;
import com.alna.software.ci.service.command.AbstractCommand;
import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.Skype;
import com.skype.SkypeException;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;

/**
 * List chats command
 */
@Service
public class ListCommand extends AbstractCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListCommand.class);

    @Override
    protected void executeCommand(final ChatMessage chatMessage) throws SkypeException {
        LOGGER.info("executeCommand({})", chatMessage);

        CollectionUtils.forAllDo(Arrays.asList(Skype.getAllChats()), new Closure<Chat>() {
            @Override
            public void execute(Chat chat) {
                try {
                    chatMessage.getSender().send(String.format(Locale.getDefault(), "[%s] - [%s]", chat.getId(), chat.getWindowTitle()));
                } catch (SkypeException e) {
                    throw new JenkinsApplicationException("error.skype.send.message").withCause(e);
                }
            }
        });
    }

    @Override
    public String getCommandName() {
        return "#list";
    }
}

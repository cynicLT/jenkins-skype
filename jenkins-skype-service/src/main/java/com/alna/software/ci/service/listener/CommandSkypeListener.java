package com.alna.software.ci.service.listener;

import com.alna.software.ci.service.command.Command;
import com.skype.ChatMessage;
import com.skype.SkypeException;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommandSkypeListener extends AbstractSkypeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandSkypeListener.class);

    private static final Map<String, Command> ALLOWED_COMMANDS = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        LOGGER.info("init()");

        MapUtils.populateMap(ALLOWED_COMMANDS, applicationContext.getBeansOfType(Command.class).values(), new Transformer<Command, String>() {
            @Override
            public String transform(Command command) {
                return command.getCommandName();
            }
        });
    }

    @Override
    protected void receivedMessage(ChatMessage chatMessage) throws SkypeException {
        LOGGER.info("receivedMessage({})", chatMessage);

        String command = chatMessage.getContent();

        if (ALLOWED_COMMANDS.containsKey(command)) {
            ALLOWED_COMMANDS.get(command).execute(chatMessage);
        }
    }
}

package com.alna.software.ci.service;

import com.alna.software.ci.exception.JenkinsApplicationException;
import com.alna.software.ci.service.listener.CommandSkypeListener;
import com.alna.software.ci.service.listener.OracleFunSkypeListener;
import com.alna.software.ci.service.param.SkypeMessageParam;
import com.alna.software.ci.utils.JenkinsOptional;
import com.skype.Chat;
import com.skype.Skype;
import com.skype.SkypeException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class SkypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkypeService.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommandSkypeListener commandSkypeListener;

    /**
     * Send message to group by ID
     *
     * @param param skype message
     */
    public void sendMessate(SkypeMessageParam param) {
        LOGGER.info("sendMessate({})", param);

        checkSkype();

        Chat chat = getChatBy(param.getGroupId());

        try {
            chat.send(messageService.createMessage(param.getJob()));
        } catch (SkypeException e) {
            throw new JenkinsApplicationException("error.skype.send.message", param).withCause(e);
        }
    }

    /**
     * Init
     */
    @PostConstruct
    public void init() {
        LOGGER.info("init()");

        checkSkype();

        try {
            Skype.addChatMessageListener(new OracleFunSkypeListener());
            Skype.addChatMessageListener(commandSkypeListener);
        } catch (SkypeException e) {
            throw new JenkinsApplicationException("error.skype.add.listener").withCause(e);
        }
    }


    /**
     * Check Skype
     */
    public void checkSkype() {
        try {
            if (!Skype.isRunning()) {
                throw new JenkinsApplicationException("error.skype.not.running");
            }
        } catch (SkypeException e) {
            throw new JenkinsApplicationException("error.skype.unable.check.running").withCause(e);
        }
    }

    private Chat getChatBy(final String id) {
        try {
            JenkinsOptional<Chat> optionalChat = JenkinsOptional.of(
                    CollectionUtils.find(
                            Arrays.asList(Skype.getAllChats()),
                            new Predicate<Chat>() {
                                @Override
                                public boolean evaluate(Chat chat) {
                                    return StringUtils.equals(chat.getId(), id);
                                }
                            }
                    )
            );

            if (optionalChat.exists()) {
                return optionalChat.get();
            } else {
                throw new JenkinsApplicationException("error.skype.chat.id.bad", id);
            }
        } catch (SkypeException e) {
            throw new JenkinsApplicationException("error.skype.find.chat", id).withCause(e);
        }
    }

}

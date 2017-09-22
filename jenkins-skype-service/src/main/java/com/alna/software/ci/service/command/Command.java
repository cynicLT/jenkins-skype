package com.alna.software.ci.service.command;

import com.skype.ChatMessage;

public interface Command {
    /**
     * Execute command
     *
     * @param chatMessage chat message
     */
    void execute(ChatMessage chatMessage);

    /**
     * Get command name
     *
     * @return command name
     */
    String getCommandName();
}

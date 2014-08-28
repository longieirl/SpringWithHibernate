package com.opserver.simpleapp.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {

    public static final Logger LOGGER = LoggerFactory.getLogger(HelpCommand.class);

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(String[] options) {
        LOGGER.debug(">>HelpCommand(options=" + options + ")");

        System.out.println("usage: option [1-6] [-output filename] [-id uuid]");

        // Print options to user
        for (CommandEnum commandEnum : CommandEnum.values()) {

            System.out.println(commandEnum.getCommandId() + ") " + commandEnum.getDescription());
        }
    }

}

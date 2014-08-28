package com.opserver.simpleapp.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuitServerCommand implements Command {

    public static final Logger LOGGER = LoggerFactory.getLogger(QuitServerCommand.class);

    public QuitServerCommand() {
        super();
    }

    @Override
    public void execute(String[] options) {
        LOGGER.debug(">>CountServerCommand(options=" + options + ")");

        System.out.println("Thank you...");

        System.exit(0);
    }

}

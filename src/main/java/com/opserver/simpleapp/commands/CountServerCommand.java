package com.opserver.simpleapp.commands;

import com.opserver.simpleapp.services.OpServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountServerCommand implements Command {

    public static final Logger LOGGER = LoggerFactory.getLogger(CountServerCommand.class);

    @Autowired
    OpServerService opServerService;

    public CountServerCommand() {
        super();
    }

    @Override
    public void execute(String[] options) {
        LOGGER.debug(">>CountServerCommand(options=" + options + ")");

        System.out.println("Option [COUNT] Selected \n");
        System.out.println("Found: " + opServerService.findAll().size() + " servers");
    }
}

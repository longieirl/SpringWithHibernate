package com.opserver.simpleapp.commands;

import com.opserver.simpleapp.utils.Consts;
import com.opserver.simpleapp.services.OpServerService;
import com.opserver.simpleapp.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddServerCommand implements Command {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddServerCommand.class);

    @Autowired
    OpServerService opServerService;

    public AddServerCommand() {
        super();
    }

    @Override
    public void execute(String[] options) {
        LOGGER.debug(">>AddServerCommand(options=" + options + ")");

        if (Utils.isCommandLineValid(options)) {
            System.out.println("Option [ADD] Selected\n");
            try {
                opServerService.loadXMLFile(options[2]);

                System.out.println("Entity has been added");

            } catch (Throwable ex) {
                LOGGER.error("AddServerCommand(), ex=" + ex);
                System.out.println(String.format(Consts.EXCEPTION_ERROR, ""));
            }
        }
    }

}

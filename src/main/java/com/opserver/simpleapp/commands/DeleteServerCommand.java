package com.opserver.simpleapp.commands;

import com.opserver.simpleapp.services.OpServerService;
import com.opserver.simpleapp.utils.Consts;
import com.opserver.simpleapp.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteServerCommand implements Command {

    public static final Logger LOGGER = LoggerFactory.getLogger(DeleteServerCommand.class);

    @Autowired
    OpServerService opServerService;

    public DeleteServerCommand() {
        super();
    }

    @Override
    public void execute(String[] options) {
        LOGGER.debug(">>DeleteServerCommand(options=" + options + ")");

        if (Utils.isCommandLineValid(options)) {
            System.out.println("Option [DELETE] Selected \n");

            final String entityId = options[2];
            opServerService.delete(entityId);

        } else {
            System.out.println(Consts.FORMATTING_ERROR);
        }
    }

}

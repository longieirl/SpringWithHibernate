package com.opserver.simpleapp.commands;

import com.opserver.simpleapp.services.OpServerService;
import com.opserver.simpleapp.utils.Consts;
import com.opserver.simpleapp.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditServerCommand implements Command {

    public static final Logger LOGGER = LoggerFactory.getLogger(EditServerCommand.class);

    @Autowired
    OpServerService opServerService;

    public EditServerCommand() {
        super();
    }

    @Override
    public void execute(String[] options) {
        LOGGER.debug(">>EditServerCommand(options=" + options + ")");

        if (Utils.isCommandLineValid(options)) {
            System.out.println("Option [EDIT] Selected \n");

            final String entityId = options[2];
            final String entityName = options[3];

            try {
                opServerService.update(entityId, entityName);
                System.out.println(Consts.ENTITY_UPDATED);
            } catch (Throwable ex) {
                LOGGER.error("EditServerCommand(), ex=" + ex);
                System.out.println(String.format(Consts.EXCEPTION_ERROR, entityId));
            }

        }
    }

}

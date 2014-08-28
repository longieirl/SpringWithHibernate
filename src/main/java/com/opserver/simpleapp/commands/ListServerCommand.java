package com.opserver.simpleapp.commands;

import com.opserver.simpleapp.utils.Consts;
import com.opserver.simpleapp.model.ServerEntity;
import com.opserver.simpleapp.services.OpServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListServerCommand implements Command {

    public static final Logger LOGGER = LoggerFactory.getLogger(ListServerCommand.class);

    @Autowired
    OpServerService opServerService;

    public ListServerCommand() {
        super();
    }

    @Override
    public void execute(String[] options) {
        LOGGER.debug(">>ListServerCommand(options=" + options + ")");


        try {
            System.out.println("Option [LIST] Selected \n");

            List<ServerEntity> entityList = opServerService.findAll();

            if (entityList.isEmpty()) {
                System.out.println("No servers have been added");
            } else {

                for (ServerEntity entity : entityList) {
                    System.out.println("ID: " + entity.getServerId() + ", Name: " + entity.getName() + "\n");
                }
            }

        } catch (Exception ex) {
            System.out.println(String.format(Consts.EXCEPTION_ERROR, ""));
        }
    }

}

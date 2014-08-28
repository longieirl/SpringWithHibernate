package com.opserver.simpleapp.services.impl;

import com.opserver.simpleapp.commands.*;
import com.opserver.simpleapp.services.CommandLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommandLineServiceImpl implements CommandLineService {

    /**
     * Command instantiations are managed by Spring DI
     * Dev-note: possible code bloat here when adding new commands
     */
    @Autowired
    private ListServerCommand listServerCommand;
    @Autowired
    private HelpCommand helpCommand;
    @Autowired
    private CountServerCommand countServerCommand;
    @Autowired
    private AddServerCommand addServerCommand;
    @Autowired
    private EditServerCommand editServerCommand;
    @Autowired
    private QuitServerCommand quitServerCommand;
    @Autowired
    private DeleteServerCommand deleteServerCommand;

    /**
     * Encapsulates command and forward the request to be processed  by the individual command
     *
     * @param options
     * @param commandEnum
     */
    @Override
    public void execute(String[] options, CommandEnum commandEnum) {

        getCommand(commandEnum).execute(options);
    }

    private Command getCommand(CommandEnum commandEnum) {

        Command command = null;

        switch (commandEnum) {
            case LIST:
                command = listServerCommand;
            break;
            case HELP:
                command = helpCommand;
            break;
            case COUNT:
                command = countServerCommand;
                break;
            case ADD:
                command = addServerCommand;
                break;
            case EDIT:
                command = editServerCommand;
                break;
            case QUIT:
                command = quitServerCommand;
                break;
            case DELETE:
                command = deleteServerCommand;
                break;
        }

        return command;
    }

}

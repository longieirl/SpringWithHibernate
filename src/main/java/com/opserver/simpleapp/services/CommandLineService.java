package com.opserver.simpleapp.services;

import com.opserver.simpleapp.commands.CommandEnum;

public interface CommandLineService {

    public void execute(String[] options, CommandEnum commandEnum);

}
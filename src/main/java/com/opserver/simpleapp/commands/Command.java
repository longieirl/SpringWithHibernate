package com.opserver.simpleapp.commands;

/**
 * Using interface because there is no default implementations required
 */
public interface Command {

    public void execute(String[] options);

}


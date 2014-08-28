package com.opserver.simpleapp.commands;

/**
 *
 * Den-note: look at create null enumm to prevent NPE's
 *
 */
public enum CommandEnum {

    QUIT(0, "Quit\n"),
    HELP(1, "Help\n"),
    COUNT(2, "Count\n"),
    ADD(3, "Add\n"),
    EDIT(4, "Edit\n"),
    DELETE(5, "Delete\n"),
    LIST(6, "List\n");

    private int commandId;
    private String description;

    private CommandEnum(int commandId, String description) {
        this.commandId = commandId;
        this.description = description;
    }

    public int getCommandId() {
        return commandId;
    };

    public String getDescription() {
        return description;
    };

    public static CommandEnum fromValue(int value) {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (commandEnum.commandId == value) {
                return commandEnum;
            }
        }

        return null;
    }

}

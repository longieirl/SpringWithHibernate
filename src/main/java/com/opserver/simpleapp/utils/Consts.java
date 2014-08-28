package com.opserver.simpleapp.utils;

import java.util.Arrays;
import java.util.List;

public final class Consts {

    public static final String FORMATTING_ERROR = "Formatting of command is not correct";
    public static final String EXCEPTION_ERROR = "EXCEPTION: Error handling entity %s";
    public static final String ENTITY_UPDATED = "Entity has been updated";
    public static final String ENTITY_REMOVED = "Entity %s has been removed";
    public static final String OPTION_ID = "-id";
    public static final String OPTION_FILE = "-output";
    public static final List<String> SUPPORTED_OPTIONS = Arrays.asList(OPTION_ID, OPTION_FILE);

    private Consts() {
        throw new AssertionError();
    }

}

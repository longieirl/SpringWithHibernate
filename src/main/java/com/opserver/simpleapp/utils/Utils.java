package com.opserver.simpleapp.utils;

public final class Utils {

    private Utils() {
        throw new AssertionError();
    }

    /**
     * Options should not be empty and contain -id or -output
     * @param options
     * @return
     */
    public static boolean isCommandLineValid(String[] options) {

        if (options != null && options.length >= 2 && Consts.SUPPORTED_OPTIONS.contains(options[1])) {
            return true;
        } else {
            System.out.println(Consts.FORMATTING_ERROR);
            return false;
        }
    }

}

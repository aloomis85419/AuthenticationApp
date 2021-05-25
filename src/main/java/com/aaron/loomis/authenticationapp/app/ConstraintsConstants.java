package com.aaron.loomis.authenticationapp.app;

import java.util.regex.Pattern;

public class ConstraintsConstants {

    public static final String VALID_USERNAME_REGEX_STRING = "([0-9a-zA-Z]|[_\\.])+";
    public static final String VALID_PASSWORD_REGEX_STRING = "([0-9a-zA-Z]|\\*\\.!@\\$%\\^&\\(\\\\)\\{}\\[]:;<>,\\.\\?/~_\\+-=\\)+";
    public static final Pattern VALID_USERNAME_REGEX = Pattern.compile(VALID_USERNAME_REGEX_STRING);
    public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile(VALID_PASSWORD_REGEX_STRING);

}

package com.controller;

/**
 * Stores parameter names for various REST services
 */
public enum RequestParameterNames {
    BADGE_NUMBER("badge_number"),
    DEPARTMENT_NAME("department_name");

    public final String parameterName;

    private RequestParameterNames(String parameterName) {
        this.parameterName = parameterName;
    }
}

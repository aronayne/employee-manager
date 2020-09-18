package com.service;

/**
 * Store error codes for CountryCodeService
 */
public enum CountryCodeServiceStatus {

    ERROR_NA("NA");

    public final String errorCode;

    private CountryCodeServiceStatus(String errorCode) {
        this.errorCode = errorCode;
    }

}

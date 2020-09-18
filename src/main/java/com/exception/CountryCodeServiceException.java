package com.exception;

/**
 * Handle exceptions thrown by CountryCodeService when accessing country name for a given code.
 */
public class CountryCodeServiceException extends RuntimeException {

    public CountryCodeServiceException() {
        super();
    }

    public CountryCodeServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CountryCodeServiceException(final String message) {
        super(message);
    }

    public CountryCodeServiceException(final Throwable cause) {
        super(cause);
    }

}

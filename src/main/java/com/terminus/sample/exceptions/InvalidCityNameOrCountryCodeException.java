package com.terminus.sample.exceptions;

public class InvalidCityNameOrCountryCodeException extends RuntimeException {

    public InvalidCityNameOrCountryCodeException(String message) {
        super(message);
    }

}

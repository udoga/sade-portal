package com.sadesoft.portal.base.error;

public class InvalidVatRateError extends ClientError{
    public InvalidVatRateError (short vatRate) {
        super("vatRate should be between 0-100, but was " + vatRate + ".");
    }
}

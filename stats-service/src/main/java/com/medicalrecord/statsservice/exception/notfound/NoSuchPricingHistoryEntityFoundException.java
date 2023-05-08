package com.medicalrecord.statsservice.exception.notfound;


import com.medicalrecord.statsservice.exception.NoSuchEntityFoundException;

public class NoSuchPricingHistoryEntityFoundException extends NoSuchEntityFoundException {

    public NoSuchPricingHistoryEntityFoundException(String message) {
        super(message);
    }

    public NoSuchPricingHistoryEntityFoundException(String field, String value) {
        super("Pricing History", field, value);
    }

}

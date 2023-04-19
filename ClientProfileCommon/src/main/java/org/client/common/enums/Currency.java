package org.client.common.enums;


public enum Currency {

    RUB("RUB"),
    USD("USD"),
    EUR("EUR");
    private final String currency;


    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}

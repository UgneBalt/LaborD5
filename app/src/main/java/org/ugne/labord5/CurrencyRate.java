package org.ugne.labord5;

public class CurrencyRate {

    public String currencyName;
    public String code;
    public double rate;

    public String toString() {
        return code+" - "+rate;
    }
}
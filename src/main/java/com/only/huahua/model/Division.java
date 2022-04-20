package com.only.huahua.model;

public class Division {
    public double numerator;
    public double denominator;

    public void setNumerator(double numerator) {
        this.numerator = numerator;
    }

    public void setDenominator(double denominator) {
        this.denominator = denominator;
    }

    public double quotient() {
        return numerator / denominator;
    }
}

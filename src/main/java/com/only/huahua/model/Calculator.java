package com.only.huahua.model;

/**
 * @description: Calculator
 * @author: leiming5
 * @date: 2022/2/25 13:57
 */
public class Calculator {

    private int first, second;

    public Calculator() {
    }


    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int addition() {

        return (first + second);

    }

    public int minus() {

        return (first - second);

    }

    public int multiply() {

        return (first * second);

    }

    public float divide() {

        return (first / second);

    }
}

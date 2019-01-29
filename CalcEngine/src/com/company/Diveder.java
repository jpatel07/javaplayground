package com.company;

public class Diveder extends CalculateBase {
    public Diveder() {

    }

    public Diveder(double leftVal, double rightVal) {
        super(leftVal, rightVal);
    }

    @Override
    public void calculate() {
        double value = getLeftVal() / getRightVal();
        setResult(value);
    }
}

package com.qtickl.javafx_1;

public class Rule {
    private int numericalRule;
    private byte[] binaryRule;

    public Rule(int rule) {
        setNumericalRule(rule);
    }

    public Rule(byte[] rule) {
        setBinaryRule(rule);
    }

    //converts integer i to a byte array
    private byte[] toBinaryArr(int i) {
        byte[] result = {0, 0, 0, 0, 0, 0, 0, 0};
        int k = i;
        for (int p = 7; p >= 0; p--) {
            if (k % Math.pow(2, p) == k) {
                result[p] = 0;
            } else {
                result[p] = 1;
                k -= Math.pow(2, p);
            }
        }
        return result;
    }

    //converts byte array a to an integer
    private int toInt(byte[] a) {
        int result = 0;
        int l = a.length - 1;
        for (int p = 0; p <= l; p++) {
            if (a[l - p] == 1) {
                result += Math.pow(2, p);
            }
        }
        return result;
    }

    //toString method
    public String toString() {
        String result = "";
        for (byte b : binaryRule) {
            result += b; //{1,1,0,0,1,0,0,0}
        }
        return result;
    }

    //getters
    public int getNumericalRule() {
        return numericalRule;
    }

    public byte[] getBinaryRule() {
        return binaryRule;
    }

    //setters
    public void setNumericalRule(int n) {
        this.numericalRule = n;
        this.binaryRule = toBinaryArr(n);
    }

    public void setBinaryRule(byte[] b) {
        if (b.length != 8) {
            throw new Error("Must be an array of length 8");
        }
        this.binaryRule = b;
        this.numericalRule = toInt(b);
    }

    /**
     * @param neighbors byte[] of size 3
     * @return the byte that results from neighbors being passed through this rule
     */
    public byte getNext(byte[] neighbors) {

        if (neighbors.length != 3) {
            throw new Error("Must be an array of length 3");
        }
        int set = toInt(neighbors);
        return binaryRule[7 - set];
    }

}
package com.notenboomtest.mcdc_test_app;

/**
 * contains the class Testcase
 * Testcase is used to store the sequence and the expected result of the sequence
 * Created by edwin on 28-8-16.
 */
public class Testcase {
    private final String seq;
    private final String res;

    public Testcase(String sequence, String result){
        seq = sequence;
        res = result;
    }

    public String getSeq(){
        return this.seq;
    }

    public String getRes() { return this.res; }
}

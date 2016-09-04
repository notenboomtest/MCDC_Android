package com.notenboomtest.mcdc_test_app;

/**
 * Contains the class Condition
 * class contains the name of the variable or condition,
 * its preceding operator, if available
 * its trailing operator, if available
 * This information is used to derive testcases from the decision containing this condition
 * Created by edwin on 28-8-16.
 */
public class Condition {
    private Character name;
    private Character precop;
    private Character postcop;

    public Condition(){
    }

    public Character getName(){
        return this.name;
    }

    public void setName(Character name){
        this.name = name;
    }

    public Character getPrecop(){
        return this.precop;
    }

    public void setPrecop(Character precop){
        this.precop = precop;
    }

    public Character getPostcop(){
        return this.postcop;
    }

    public void setPostcop(Character postcop){
        this.postcop = postcop;
    }
}

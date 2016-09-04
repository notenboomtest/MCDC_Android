package com.notenboomtest.mcdc_test_app;

import com.notenboomtest.mcdc_test_app.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the class decision
 * The class Decision contains all the logic to derive test cases from a given Decision
 * The decision is analyzed and the different conditions are stored as Condition-objects
 * The testcases are stored as Testcase-objects
 * The test cases are made available to view directly and as a large string for email purposes
 * Created by edwin on 28-8-16.
 */
public class Decision {
    private List<Condition> conditions;
    private List<Testcase> testcases;

    public Decision() {
        this.conditions = new ArrayList<>();
        this.testcases = new ArrayList<>();
    }

    public List<Testcase> getTestcases(){
        return this.testcases;
    }
    public String getSerializedTestcases() { return this.serialized(); }

    private void extract(String raw){
        char[] elem = raw.toCharArray();
        int size = raw.length();
        for (int i = 0; i < size; i++){
            Condition temp = new Condition();
            temp.setName(elem[i]);
            if (i == 0)
                temp.setPrecop('-');
            else
                temp.setPrecop(elem[i-1]);
            if ((i+1) == size)
                temp.setPostcop('-');
            else
                temp.setPostcop(elem[i+1]);
            this.conditions.add(temp);
            i += 1;
        }
    }

    private void derivation(){
        List<Character> testcase = new ArrayList<>();
        List<Testcase> temp = new ArrayList<>();
        int size = this.conditions.size();
        for (int i = 0; i< size; i++){
            for (int x = 0; x < (size - (size - i)); x++){
                if (this.conditions.get(x).getPostcop() == '&')
                    testcase.add('1');
                else
                    testcase.add('0');
            }
            testcase.add('1');
            for (int x = i+1; x < size; x++){
                if (this.conditions.get(x).getPrecop() == '&')
                    testcase.add('1');
                else
                    testcase.add('0');
            }
            this.testcases.add(new Testcase(testcase.toString(), "TRUE"));
            testcase.set(i, '0');
            temp.add(new Testcase(testcase.toString(), "FALSE"));
            testcase.clear();
        }
        this.testcases.addAll(temp);
    }

    private String serialized() {
        String result = "";
        for (int i = 0; i < this.testcases.size(); i++){
            result += this.testcases.get(i).getSeq();
            result += " ";
            result += this.testcases.get(i).getRes();
            result += "\n\r";
        }
        return result;
    }

    private void reduce(){
        List<String> li = new ArrayList<>();
        List<Testcase> temp = new ArrayList<>();
        for (int i = 0; i < this.testcases.size(); i++){
            if  (!li.contains(this.testcases.get(i).getSeq())) {
                li.add(this.testcases.get(i).getSeq());
                temp.add(this.testcases.get(i));
            }
        }
        this.testcases.clear();
        this.testcases = temp;
    }

    public void solve(String decision){
        this.extract(decision);
        this.derivation();
        this.reduce();
    }

}


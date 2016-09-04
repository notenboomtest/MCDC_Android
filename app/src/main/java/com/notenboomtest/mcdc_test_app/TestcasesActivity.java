package com.notenboomtest.mcdc_test_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestcasesActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.notenboomtest.mcdc_test_app.MESSAGE";
    final Decision decision = new Decision();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcases);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //TextView textView = new TextView(this);

//        String result = "Been here!";
//        Decision decision = new Decision();
        decision.solve(message);

        TextView header = (TextView) findViewById(R.id.sequenceHeader);
        header.setText(message);

        //sequenceHeader.text = message;
        int size = decision.getTestcases().size();

        final ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            res.add(decision.getTestcases().get(i).getRes());
        }

        final ListView resview = (ListView) findViewById(R.id.valueLine);
        final TestcaseArrayAdapter resAdapter = new TestcaseArrayAdapter(this,
                android.R.layout.simple_list_item_1, res);
        resview.setAdapter(resAdapter);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < size; ++i) {
            list.add(decision.getTestcases().get(i).getSeq());
        }

        final ListView listview = (ListView) findViewById(R.id.listview);
        final TestcaseArrayAdapter adapter = new TestcaseArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

    }

    public void newDecision(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createMail(View view) {

        Intent email = new Intent(Intent.ACTION_SEND);
        TextView textView = (TextView) findViewById(R.id.sequenceHeader);
        String message = textView.getText().toString();
        email.putExtra(Intent.EXTRA_EMAIL, "");
        email.putExtra(Intent.EXTRA_SUBJECT, message);
        email.putExtra(Intent.EXTRA_TEXT, decision.getSerializedResults());

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    private class TestcaseArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public TestcaseArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }
    }
}
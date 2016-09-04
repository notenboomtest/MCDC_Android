package com.notenboomtest.mcdc_test_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.notenboomtest.mcdc_test_app.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createTestcases(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String sequence = editText.getText().toString();

        if (Pattern.matches("^[A-Za-z]{1}((\\||&)[A-Za-z])*$", sequence)){
            Intent intent = new Intent(this, TestcasesActivity.class);
            String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);}
        else {
            Context context = getApplicationContext();
            CharSequence text = "The sequence doesn't met the form 'A&B|C'. Please change";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
}

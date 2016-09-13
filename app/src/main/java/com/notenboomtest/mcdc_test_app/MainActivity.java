package com.notenboomtest.mcdc_test_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.notenboomtest.mcdc_test_app.MESSAGE";

    private View.OnClickListener mAndListener = new View.OnClickListener() {
        public void onClick(View v) {
            EditText editText = (EditText) findViewById(R.id.edit_message);
            editText.append("&");
        }
    };

    private View.OnClickListener mOrListener = new View.OnClickListener() {
        public void onClick(View v) {
            EditText editText = (EditText) findViewById(R.id.edit_message);
            editText.append("|");
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button andButton = (Button)findViewById(R.id.andButton);
        andButton.setOnClickListener(mAndListener);

        Button orButton = (Button)findViewById(R.id.orButton);
        orButton.setOnClickListener(mOrListener);
    }

    public void createTestcases(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String sequence = editText.getText().toString();

        if (!Pattern.matches("^[A-Za-z]{1}((\\||&)[A-Za-z])*$", sequence)) {
            Context context = getApplicationContext();
            CharSequence text = "The decision has to start with a letter and can be followed by one or more combinations of a | or & + letter. Please change";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else if (doubles(sequence)){
            Context context = getApplicationContext();
            CharSequence text = "The decision contains duplicate letters";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }
        else {
            Intent intent = new Intent(this, TestcasesActivity.class);
            String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }



    }
    private boolean doubles(String sequence){
        String[] temp = sequence.split("(\\||&)");
        System.out.println(temp.length);
        Set<String> list = new HashSet<>();
        for (int i=0; i < temp.length;i++){
            list.add(temp[i]);
        }
        System.out.println(list.size());
        return !(list.size() == temp.length);

    }
}

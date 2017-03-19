package com.github.kebbbnnn.nextbuttondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.kebbbnnn.nextbutton.NextButton;
import com.github.kebbbnnn.nextbutton.listeners.OnStringArrayListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NextButton nextButton = (NextButton) findViewById(R.id.nextButton);
        nextButton.setOnStringArrayListener(new OnStringArrayListener() {
            @Override
            public void onStrings(String... s) {
                for (int i = 0; i < s.length; i++) {
                    Log.e(MainActivity.class.getName(), "index[" + i + "]" + ", string = " + s[i]);
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(MainActivity.class.getName(), "Clicked!");
            }
        });
    }
}

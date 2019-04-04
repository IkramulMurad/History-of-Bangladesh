package com.sinbadsoftware.historyofbangladesh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("OnCreate", "MainActivity created!");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setAllButtonClickListener();
    }

    private void setAllButtonClickListener() {
        Log.i("Method", "setAllButtonClickListener hit!");

        LinearLayout linearLayout = findViewById(R.id.buttonsContainer);
        for (int i = 0; i < linearLayout.getChildCount(); ++i) {
            Button button = (Button) linearLayout.getChildAt(i);
            button.setOnClickListener(onClickListener("generation", String.valueOf(i) + ".txt"));
        }
    }

    private View.OnClickListener onClickListener(final String extraKey, final String extraValue) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ExtraInfo", "ExtraKey: " + extraKey + ", ExtraValue: " + extraValue);

                Intent intent = new Intent(MainActivity.this, SubmenuActivity.class);
                intent.putExtra(extraKey, extraValue);
                startActivity(intent);
            }
        };
    }
}

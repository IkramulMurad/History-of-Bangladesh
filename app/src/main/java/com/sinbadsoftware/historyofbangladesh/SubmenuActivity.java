package com.sinbadsoftware.historyofbangladesh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubmenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("OnCreate", "SubmenuActivity created!");
        super.onCreate(savedInstanceState);

        //get text file name from previous activity
        String submenuFile = getIntent().getStringExtra("generation");
        String submenuFileUrl = "assets/txt/" + submenuFile;
        BufferedReader bufferedReader = getBufferedReader(submenuFileUrl);


        String line;
        int buttonNumber = 0;
        int textFileNumber = Integer.valueOf(submenuFile.split("[.]")[0]);
        LinearLayout linearLayout = setLinearLayoutOnView();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                Log.i("submenu", line);

                String htmlFileName = String.valueOf(textFileNumber * 10 + buttonNumber) + ".html";
                Button button = setButtonOnView(buttonNumber++, line, htmlFileName);
                linearLayout.addView(button);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LinearLayout setLinearLayoutOnView() {
        Log.i("Method", "setLinearLayoutOnView hit!");

        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        return linearLayout;
    }

    private BufferedReader getBufferedReader(String fileUrl) {
        Log.i("Method", "getBufferedReader hit!");
        Log.i("Parameters", "fileUrl: " + fileUrl);

        InputStreamReader inputStreamReader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(fileUrl));
        return new BufferedReader(inputStreamReader);
    }

    private Button setButtonOnView(int buttonNumber, String buttonText, String onClickListenerExtraValue) {
        Log.i("Method", "setButtonOnView hit!");
        Log.i("Parameters", "buttonNumber: " + buttonNumber + ", buttonText: " + buttonText);

        Button button = new Button(this);
        button.setOnClickListener(onClickListener(buttonNumber, "htmlView", onClickListenerExtraValue));
        button.setText(buttonText);
        button.setId(buttonNumber);

        return button;
    }

    private View.OnClickListener onClickListener(final int buttonId, final String extraKey, final String extraValue) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Button Pressed", String.valueOf(buttonId) + "th button pressed!");
                Log.i("ExtraInfo", "ExtraKey: " + extraKey + ", ExtraValue: " + extraValue);

                Intent intent = new Intent(SubmenuActivity.this, ContentActivity.class);
                intent.putExtra(extraKey, extraValue);
                startActivity(intent);
            }
        };
    }
}

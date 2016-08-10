package com.example.rishugoel.otpgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import java.util.*;
import java.text.*;
import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                TextView result= (TextView) findViewById(R.id.textView2);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                                String currentDateandTime = sdf.format(new Date());
                                result.setText(md5(currentDateandTime));
                            }
                        });
                        Thread.sleep(60000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    public static final String md5(final String toEncrypt) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncrypt.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase().substring(0,5);
        } catch (Exception exc) {
            return ""; // Impossibru!
        }
    }
}

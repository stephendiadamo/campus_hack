package com.stephendiadamo.lockscreen;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        TextView startFakeLock = (TextView) findViewById(R.id.settings_start_fake_lock);
        TextView stopFakeLock = (TextView) findViewById(R.id.settings_stop_fake_lock);
        TextView fakeDataInput = (TextView) findViewById(R.id.settings_fake_data_input);
        final Intent fakeLock = new Intent(this, LockScreenService.class);

        startFakeLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(fakeLock);
                Toast.makeText(view.getContext(), "Lockout started", Toast.LENGTH_LONG).show();
            }
        });

        stopFakeLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(fakeLock);
                Toast.makeText(view.getContext(), "Lockout stopped", Toast.LENGTH_LONG).show();
            }
        });

        fakeDataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.fake_data_input);
            }
        });
    }
}

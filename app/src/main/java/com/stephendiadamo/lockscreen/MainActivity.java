package com.stephendiadamo.lockscreen;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stephendiadamo.lockscreen.data_objects.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        TextView startFakeLock = (TextView) findViewById(R.id.settings_start_fake_lock);
        TextView stopFakeLock = (TextView) findViewById(R.id.settings_stop_fake_lock);
        TextView fakeDataInput = (TextView) findViewById(R.id.settings_fake_data_input);
        final Intent fakeLock = new Intent(this, LockScreenService.class);
        final Intent fakeDataInputIntent = new Intent(this, FakeDataInput.class);

        FakeDataGenerator f = new FakeDataGenerator();
        ArrayList<Person> people = new ArrayList<>();
        Person p = new Person("1", "2", "3", "4", "5", "6", "7");
        people.add(p);
        f.generatePeople(people);

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
                startActivity(fakeDataInputIntent);
            }
        });
    }
}

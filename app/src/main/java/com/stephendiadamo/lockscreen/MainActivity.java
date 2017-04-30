package com.stephendiadamo.lockscreen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        sharedPref = this.getSharedPreferences("lockout", Context.MODE_PRIVATE);

        TextView startFakeLock = (TextView) findViewById(R.id.settings_start_fake_lock);
        TextView stopFakeLock = (TextView) findViewById(R.id.settings_stop_fake_lock);
        TextView fakeDataInput = (TextView) findViewById(R.id.settings_fake_data_input);
        TextView setFakePassword = (TextView) findViewById(R.id.customize_fake);
        TextView setRealPassword = (TextView) findViewById(R.id.customize_real);

        final Intent fakeLock = new Intent(this, LockScreenService.class);
        final Intent fakeDataInputIntent = new Intent(this, FakeDataInput.class);

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

        setFakePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                passwordDialog("Fake Password", "tsa_password");
            }
        });

        setRealPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordDialog("Real Password", "real_password");
            }
        });
    }

    public void passwordDialog(String header, final String passwordType) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(header);
        alertDialog.setMessage("Enter " + header);

        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String password = input.getText().toString();
                final SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(passwordType, password);
                editor.commit();
                Toast.makeText(input.getContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }


}

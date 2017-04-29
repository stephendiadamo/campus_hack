package com.stephendiadamo.lockscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by antonio on 29.04.17.
 */

public class FakeDataInput extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fake_data_input);

        TextView saveButton = (TextView) findViewById(R.id.save_button);
        final EditText email = (EditText) findViewById(R.id.fake_data_email);
        final EditText name = (EditText) findViewById(R.id.fake_data_first_name);
        final EditText phone = (EditText) findViewById(R.id.fake_data_number);


        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("EMAIL", email.getText().toString());
                editor.putString("Name", name.getText().toString());
                editor.putString("Phone", phone.getText().toString());

                editor.commit();

                Toast.makeText(v.getContext(), "Saved", Toast.LENGTH_LONG).show();

            }
        });

    }

}

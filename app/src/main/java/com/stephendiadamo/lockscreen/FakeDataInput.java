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

import com.stephendiadamo.lockscreen.data_objects.Person;

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
        final EditText first = (EditText) findViewById(R.id.fake_data_first_name);
        final EditText phone = (EditText) findViewById(R.id.fake_data_number);
        final EditText last = (EditText) findViewById(R.id.fake_data_last_name);
        final EditText company = (EditText) findViewById(R.id.fake_data_company_name);
        final EditText birthDate = (EditText) findViewById(R.id.fake_data_birth_date);
        final EditText address = (EditText) findViewById(R.id.fake_data_address);


        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Person person = new Person(first.getText().toString(), last.getText().toString(), company.getText().toString(), email.getText().toString(), phone.getText().toString(), birthDate.getText().toString(), address.getText().toString());

                PersonOperations personOperations = new PersonOperations(v.getContext());
                personOperations.addPerson(person);

                first.setText("");
                email.setText("");
                phone.setText("");
                last.setText("");
                company.setText("");
                birthDate.setText("");
                address.setText("");


                Toast.makeText(v.getContext(), "Saved", Toast.LENGTH_LONG).show();

            }
        });

    }

}

package com.stephendiadamo.lockscreen;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.stephendiadamo.lockscreen.data_objects.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by stephendiadamo on 2017-04-27.
 */

public class LockScreenService extends Service {
    private LinearLayout linearLayout;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;

    private final String tsaPassword = "1234";
    private final String normalPassword = "1111";
    private Integer quickEspaceCounter = 0;

    BroadcastReceiver screenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF) && linearLayout == null) {
                init();
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(screenReceiver, intentFilter);
        windowManager = ((WindowManager) getSystemService(WINDOW_SERVICE));
        layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD,
                PixelFormat.TRANSLUCENT);
    }

    private void init() {
        linearLayout = new LinearLayout(this);
        final Context context = this;
        windowManager.addView(linearLayout, layoutParams);
        ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.lock_screen, linearLayout);

        final EditText passwordField = (EditText) linearLayout.findViewById(R.id.password_field);

        TextView one = (TextView) linearLayout.findViewById(R.id.number_one);
        TextView two = (TextView) linearLayout.findViewById(R.id.number_two);
        TextView three = (TextView) linearLayout.findViewById(R.id.number_three);
        TextView four = (TextView) linearLayout.findViewById(R.id.number_four);
        TextView five = (TextView) linearLayout.findViewById(R.id.number_five);
        TextView six = (TextView) linearLayout.findViewById(R.id.number_six);
        TextView seven = (TextView) linearLayout.findViewById(R.id.number_seven);
        TextView eight = (TextView) linearLayout.findViewById(R.id.number_eight);
        TextView nine = (TextView) linearLayout.findViewById(R.id.number_nine);
        TextView zero = (TextView) linearLayout.findViewById(R.id.number_zero);

        TextView remove = (TextView) linearLayout.findViewById(R.id.remove);
        TextView ok = (TextView) linearLayout.findViewById(R.id.ok);

        ArrayList<TextView> numbers = new ArrayList<>();

        numbers.add(zero);
        numbers.add(one);
        numbers.add(two);
        numbers.add(three);
        numbers.add(four);
        numbers.add(five);
        numbers.add(six);
        numbers.add(seven);
        numbers.add(eight);
        numbers.add(nine);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = passwordField.getText().toString();
                if (password.length() > 0) {
                    passwordField.setText(password.substring(0, password.length() - 1));
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            addNumberToField(numbers.get(i), String.valueOf(i), passwordField);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = passwordField.getText().toString();
                switch (password) {
                    case tsaPassword:
                        windowManager.removeView(linearLayout);
                        linearLayout = new LinearLayout(context);
                        windowManager.addView(linearLayout, layoutParams);
                        ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
                                .inflate(R.layout.tsa_main_screen, linearLayout);
                        setIconActions();

                        linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                quickEspaceCounter++;
                                if (quickEspaceCounter == 10) {
                                    windowManager.removeView(linearLayout);
                                }
                            }
                        });

                        break;
                    case normalPassword:
                        windowManager.removeView(linearLayout);
                        linearLayout = null;
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void addNumberToField(TextView number, final String text, final EditText passwordField) {
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordField.append(text);
            }
        });
    }


    private void setIconActions() {

        ImageView contacts = (ImageView) linearLayout.findViewById(R.id.contacts_icon);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowManager.removeView(linearLayout);
                linearLayout = new LinearLayout(view.getContext());
                windowManager.addView(linearLayout, layoutParams);
                ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
                        .inflate(R.layout.contacts, linearLayout);

                PersonOperations peopleOps = new PersonOperations(linearLayout.getContext());
                ArrayList<Person> people = peopleOps.getAllPeople();

                ContactsAdapter contactsAdapter = new ContactsAdapter(linearLayout.getContext(), people);
                ListView list = (ListView) linearLayout.findViewById(R.id.contacts_listview);
                list.setAdapter(contactsAdapter);
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        //unregisterReceiver(screenReceiver);
        super.onDestroy();
    }
}

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
import android.widget.TextView;
import android.widget.Toast;

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
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN //draw on status bar
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION,// hiding the home screen button
                PixelFormat.TRANSLUCENT);
    }

    private void init() {
        linearLayout = new LinearLayout(this);
        final Context context = this;
        windowManager.addView(linearLayout, layoutParams);
        ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.lock_screen, linearLayout);
        final EditText passwordField = (EditText) linearLayout.findViewById(R.id.password_field);
        final TextView resultMessage = (TextView) linearLayout.findViewById(R.id.result_message);

        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 4) {
                    String input = editable.toString();
                    switch (input) {
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
                            resultMessage.setText("Wrong Password");
                            editable.clear();
                            break;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
        });
    }

    private void setIconActions() {
        final ImageView emailIcon = (ImageView) linearLayout.findViewById(R.id.email_icon);
//        final ImageView phoneIcon = (ImageView) linearLayout.findViewById(R.id.phone_icon);
//        final ImageView contactsIcon = (ImageView) linearLayout.findViewById(R.id.contacts_icon);
//        final ImageView calendarIcon = (ImageView) linearLayout.findViewById(R.id.calendar_icon);

        emailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(linearLayout.getContext(), "Email", Toast.LENGTH_LONG).show();
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

package com.example.myapplication.Activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.SessionClass.SessionClass;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SnapScreen extends AppCompatActivity {

    SessionClass sessionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snap_screen);

        sessionClass = SessionClass.getInstance(this);


        if (!haveNetwork()) {
            showDialog();
        }
        else
        {
            if (sessionClass.isLoggedIn()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(SnapScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    }
                }, 2000);
            } else {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SnapScreen.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
        }




    }


    public boolean haveNetwork() {
        boolean have_WIFI=false;
        boolean have_Internet=false;
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI")) {
                if (info.isConnected())
                    have_WIFI=true;
            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (info.isConnected())
                    have_Internet=true;

            }
        }

        return have_Internet || have_WIFI;
    }

    void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewLayout = inflater.inflate(R.layout.no_internet_alart_dialog , null);
        dialog.setView(viewLayout);
        Button ok = (Button) viewLayout.findViewById(R.id.logok);
        final AlertDialog  alertDialog=dialog.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });
        alertDialog.show();
    }
}

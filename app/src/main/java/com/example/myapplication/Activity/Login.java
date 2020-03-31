package com.example.myapplication.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DialogBox.LoginFaildDialog;
import com.example.myapplication.Model.SessionData;
import com.example.myapplication.R;
import com.example.myapplication.SessionClass.SessionClass;
import com.example.myapplication.loginModel.LoginResponse;
import com.example.myapplication.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText login_email, login_password;
    TextView sign_in;
    Button bottomSinUp;
    private String TAG = "LoginActivity ";
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        bottomSinUp = findViewById(R.id.bottomSinUp);

        sign_in = findViewById(R.id.sign_in);

        progressBar = findViewById(R.id.progress_bar);


        bottomSinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();

                if (email.equals("")) {
                    login_email.setError("Enter Your Email");
                } else if (password.equals("")) {
                    login_password.setError("Enter Your password");
                } else {

                    progressBar.setVisibility( View.VISIBLE );
                    bottomSinUp.setVisibility( View.GONE );

                    doLogin(email, password);
//                    if (email.equals( "habib") && password.equals( "1234" ) ){
//                        Intent intent = new Intent( Login.this, MainActivity.class );
//                        startActivity( intent );
//                        finish();
//                    }else {
//                        Toast.makeText( Login.this, "your email or password is incorrect. please try again", Toast.LENGTH_SHORT ).show();
//                    }


                }
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NumberAuthentication.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void doLogin(String email, String password) {
        Call<LoginResponse> call = RetrofitClient.getInstance().getApiInterface().userLogin(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getStatus() == 1) {
                        Log.e(TAG, "onResponse: " + loginResponse.getToken().getOriginal().getAccessToken());
                        Log.e(TAG, "onResponse: " + loginResponse.getToken().getOriginal().getAccessToken().length());
                        Toast.makeText(Login.this, loginResponse.getUserId().toString(), Toast.LENGTH_SHORT).show();

                        int userID = loginResponse.getUserId();
                        String UserToken = loginResponse.getToken().getOriginal().getAccessToken();
                        String UserName = loginResponse.getUserInformation().getName();
                        String UserEmail = loginResponse.getUserInformation().getEmail();
                        String UserAddress = loginResponse.getUserInformation().getAddress();
                        String UserMobile = loginResponse.getUserInformation().getMobile();
                        String UserProfilePhoto = loginResponse.getUserInformation().getUserProfilePhoto().toString();

                        SessionData sessionData = new SessionData();

                        sessionData.setId( userID );
                        sessionData.setToken( UserToken );
                        sessionData.setName( UserName );
                        sessionData.setEmail( UserEmail );
                        sessionData.setAddress( UserAddress );
                        sessionData.setMobile( UserMobile );


                        if(!UserProfilePhoto.equals( null ))
                        {
                            sessionData.setProfile_image( UserProfilePhoto );
                        }else {

                        }
                        //SessionData.(getApplicationContext()).userLogin(sessionData);

                        SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);


                        progressBar.setVisibility( View.GONE );
                        bottomSinUp.setVisibility( View.VISIBLE );

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        progressBar.setVisibility( View.GONE );
                        bottomSinUp.setVisibility( View.VISIBLE );
                        showDialog();
                    }
                } else {
                    progressBar.setVisibility( View.GONE );
                    bottomSinUp.setVisibility( View.VISIBLE );
                    Log.d(TAG, "onResponse: " + response.code());
                }
            }

             public void loginfailed() {
                 LoginFaildDialog loginFaildDialog=new LoginFaildDialog();
                 loginFaildDialog.show(getSupportFragmentManager(), "LoginFailedAlartDialog");

             }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewLayout = inflater.inflate(R.layout.login_failed_alart_dialog , null);
        dialog.setView(viewLayout);
        Button ok = (Button) viewLayout.findViewById(R.id.logok);
        final AlertDialog  alertDialog=dialog.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

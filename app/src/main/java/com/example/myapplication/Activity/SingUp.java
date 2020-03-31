package com.example.myapplication.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Model.SessionData;
import com.example.myapplication.R;
import com.example.myapplication.RegisterModel.RegisterResponse;
import com.example.myapplication.SessionClass.SessionClass;
import com.example.myapplication.loginModel.LoginResponse;
import com.example.myapplication.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingUp extends AppCompatActivity {

    EditText userName,userEmail,Password,confirm_password;
    CheckBox check_box;
    Button sign_up;
    String mobile;
    private String TAG = "RegisterActivity ";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sing_up);

        userName = findViewById( R.id.userName );
        userEmail = findViewById( R.id.userEmail );
        Password = findViewById( R.id.password );
        confirm_password = findViewById( R.id.confirm_password );

        check_box = findViewById( R.id.check_box );

        sign_up = findViewById( R.id.sign_up );

        progressBar = findViewById( R.id.progressBar );

        mobile = getIntent().getStringExtra("number");

        sign_up.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String password = Password.getText().toString();
                String ConfirmPassword = confirm_password.getText().toString();

                sign_up.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                if (name.equals( "" ) ){
                    userName.setError( "Enter Your Name" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else if (email.equals( "" ) ){
                    userEmail.setError( "Enter Your Email" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else if (password.equals( "" )){
                    Password.setError( "Enter Your password" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else if (password.length() <= 9){
                    Password.setError( "Enter 9 character password" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
                else if (ConfirmPassword.equals( "" )){
                    confirm_password.setError( "Enter Your password" );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else if (!password.equals(ConfirmPassword)){
                    Toast.makeText( SingUp.this, "Password not match!", Toast.LENGTH_SHORT ).show();
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }if(!check_box.isChecked())//checked then
                {
                    check_box.setError( "Please Confirm Our terms and condition." );
                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }else {

                   /* Intent intent = new Intent( SingUp.this, MainActivity.class );
                    startActivity( intent );
                    finish(); */
                   doRegister(name,email,mobile,password);
                }


            }
        } );






    }

    private void doRegister(String name,String email,String mobile, String password) {

        Call<RegisterResponse> call = RetrofitClient.getInstance().getApiInterface().userRegister(name,email,mobile, password);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && registerResponse.getStatus() == 1) {
                        Log.e(TAG, "onResponse: " + registerResponse.getToken().getOriginal().getAccessToken());
                        Log.e(TAG, "onResponse: " + registerResponse.getToken().getOriginal().getAccessToken().length());

                        sign_up.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                        int userID = registerResponse.getUserId();
                        String UserToken = registerResponse.getToken().getOriginal().getAccessToken();
                        String UserName = registerResponse.getUserInformation().getName();
                        String UserEmail = registerResponse.getUserInformation().getEmail();
                        String UserAddress = registerResponse.getUserInformation().getAddress();
                        String UserMobile = registerResponse.getUserInformation().getMobile();

                        SessionData sessionData = new SessionData();

                        sessionData.setId( userID );
                        sessionData.setToken( UserToken );
                        sessionData.setName( UserName );
                        sessionData.setEmail( UserEmail );
                        sessionData.setAddress( UserAddress );
                        sessionData.setMobile( UserMobile );
                        //SessionData.(getApplicationContext()).userLogin(sessionData);

                        SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);

                        Toast.makeText(SingUp.this, registerResponse.getUserId().toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SingUp.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
//                        Toast.makeText(SingUp.this, registerResponse.getStatus(), Toast.LENGTH_LONG).show();

                        Toast.makeText( SingUp.this, "Another Email or Number", Toast.LENGTH_SHORT ).show();



                        sign_up.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);


                    }
                }
                else {
                    Log.d(TAG, "onResponse: " + response.code());

                    sign_up.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                showDialog();
//                Toast.makeText(SingUp.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                sign_up.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewLayout = inflater.inflate(R.layout.sign_up_alart_dialog , null);
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

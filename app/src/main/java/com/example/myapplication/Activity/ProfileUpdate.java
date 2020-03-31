package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.AdPostRecylerViewResponce.AdRecylerViewResponse;
import com.example.myapplication.AdPostResponce.AdPostResponce;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.ProfileFragment;
import com.example.myapplication.Model.SessionData;
import com.example.myapplication.ProfileModel.ProfileResponse;
import com.example.myapplication.ProfileUpdateModel.ProfileUpdateResponce;
import com.example.myapplication.R;
import com.example.myapplication.SessionClass.SessionClass;
import com.example.myapplication.loginModel.LoginResponse;
import com.example.myapplication.networking.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileUpdate extends AppCompatActivity {

    LinearLayout buttons;
    ProgressBar progress_bar;

    EditText user_name,email,address;
    ImageButton back;
    Button bottonUpdate;
    TextView Token;

    ImageView profile_image;

    private Bitmap bitmap;

    String encodedImage;

    String imageString;

    String TAG="ProfileUpdate";

    String token,Name,Email,Address,ProfileImage,Mobile;
    int id;

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyeemail";
    private static final String KEY_ADDRESS = "keyeaddress";
    private static final String KEY_MOBILE = "keyemobile";
    private static final String KEY_PROFILE_IMAGE = "keyeprofileimage";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.profile_update );

        user_name = findViewById( R.id.user_name );
        email = findViewById( R.id.email );
        bottonUpdate = findViewById( R.id.bottonUpdate );
        address = findViewById( R.id.address );

        back = findViewById( R.id.back );

        profile_image =  findViewById( R.id.profile_image );

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
        Name = sharedPreferences.getString(KEY_NAME, "");
        Email = sharedPreferences.getString(KEY_EMAIL, "");
        Address = sharedPreferences.getString(KEY_ADDRESS, "");
        Mobile = sharedPreferences.getString( KEY_MOBILE,"" );
        id = sharedPreferences.getInt( KEY_ID,0);

        user_name.setText(Name);
        email.setText(Email);
        address.setText(Address);

        buttons = findViewById( R.id.buttons );

        progress_bar = findViewById( R.id.progress_bar );
        progress_bar.setVisibility( View.GONE );





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bottonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                buttons.setVisibility( View.GONE );
                progress_bar.setVisibility( View.VISIBLE );

                Name = user_name.getText().toString();
                Email = email.getText().toString();
                Address = address.getText().toString();


                Call<ProfileUpdateResponce> call = RetrofitClient.getInstance(token).getApiInterface().profileUpdateData(Name,Email,Address,imageString);
                call.enqueue(new Callback<ProfileUpdateResponce>() {
                    @Override
                    public void onResponse(Call<ProfileUpdateResponce> call, Response<ProfileUpdateResponce> response) {
                        if (response.isSuccessful()) {

                            ProfileUpdateResponce profileUpdateResponce = response.body();
                            if (profileUpdateResponce != null && profileUpdateResponce.getStatus() == 1) {


                                Log.e(TAG, "Name: " + Name);
                                Log.e(TAG, "Email: " + Email);
                                Log.e(TAG, "Address: " + Address);
                                Toast.makeText(ProfileUpdate.this, "Profile Update", Toast.LENGTH_SHORT).show();

                                ProfileImage = profileUpdateResponce.getUser().getUserProfilePhoto().toString();

                                SessionData sessionData = new SessionData();

                                sessionData.setName( Name );
                                sessionData.setEmail( Email );
                                sessionData.setAddress( Address );
                                sessionData.setToken( token );
                                sessionData.setMobile( Mobile );
                                sessionData.setId( id );
                                sessionData.setProfile_image( ProfileImage );
                                //SessionData.(getApplicationContext()).userLogin(sessionData);

                                SessionClass.getInstance(getApplicationContext()).userLogin(sessionData);
                                Intent intent = new Intent( ProfileUpdate.this,MainActivity.class );
                                startActivity( intent );

                                finish();

                                buttons.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );


                            } else {
                                Toast.makeText(ProfileUpdate.this, "Error", Toast.LENGTH_SHORT).show();

                                buttons.setVisibility( View.VISIBLE );
                                progress_bar.setVisibility( View.GONE );
                            }
                        } else {
                            Toast.makeText(ProfileUpdate.this, "", Toast.LENGTH_SHORT).show();

                            buttons.setVisibility( View.VISIBLE );
                            progress_bar.setVisibility( View.GONE );
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileUpdateResponce> call, Throwable t) {
                        buttons.setVisibility( View.VISIBLE );
                        progress_bar.setVisibility( View.GONE );
                    }
                });


            }
        });


        profile_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        } );

    }


    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction( Intent.ACTION_GET_CONTENT);
        startActivityForResult( Intent.createChooser(intent, "Select Picture"), 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profile_image.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            imageString = getStringImage(bitmap);

            Log.e( TAG, "Image_sTRING: "+ imageString );

        }
    }

    public String getStringImage(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }
}

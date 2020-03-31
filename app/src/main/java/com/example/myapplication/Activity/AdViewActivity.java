package com.example.myapplication.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.AdPostResponce.AdPostResponce;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.R;
import com.example.myapplication.UserInterestModel.UserInterestResponce;
import com.example.myapplication.networking.RetrofitClient;

public class AdViewActivity extends AppCompatActivity {

    String TAG = "AdViewActivity";

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_MOBILE = "keyemobile";

    ImageView featured_image;
    TextView address;

    TextView id,division,post_title,city,apartment_no,rent_date,tenant;
    TextView description,space_size,renters,utility,category,floor;
    LinearLayout extra_information;
    TextView bedroom,bathroom,balconi,kitchen,dining_room,drawing_room,garage;
    TextView closing_time,opening_time,nearest_famous_place_one,nearest_famous_place_two;

    Button button_interest;
    ProgressBar progress_bar;

    String PostTitle,Division,City,Address,ApartmentNo,RentDate,Tenant,Description,SpaceSize,Renters,Utility,Category,Floor,Bedroom,Bathroom,Balconi,Kitchen,DiningRoom,DrawingRoom,Garage,ClosingTime,OpeningTime,NearestFamousPlaceOne,NearestFamousPlaceTwo,FeaturedImage;
    String User_name,User_id,User_mobile,Token,ad_id;
    int UserId;

    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ad_view );


        featured_image = findViewById( R.id.featured_image );
        address = findViewById( R.id.address );


        id = findViewById( R.id.id );
        division = findViewById( R.id.division );
        post_title = findViewById( R.id.post_title );
        city = findViewById( R.id.city );

        apartment_no = findViewById( R.id.apartment_no );
        rent_date = findViewById( R.id.rent_date );
        tenant = findViewById( R.id.tenant );

        description = findViewById( R.id.description );
        space_size = findViewById( R.id.space_size );
        renters = findViewById( R.id.renters );
        utility = findViewById( R.id.utility );
        category = findViewById( R.id.category );
        floor = findViewById( R.id.floor );

        extra_information = findViewById( R.id.extra_information );
        bedroom = findViewById( R.id.bedroom );
        bathroom = findViewById( R.id.bathroom );
        balconi = findViewById( R.id.balconi );
        kitchen = findViewById( R.id.kitchen );
        dining_room = findViewById( R.id.dining_room );
        drawing_room = findViewById( R.id.drawing_room );
        garage = findViewById( R.id.garage );

        closing_time = findViewById( R.id.closing_time );
        opening_time = findViewById( R.id.opening_time );
        nearest_famous_place_one = findViewById( R.id.nearest_famous_place_one );
        nearest_famous_place_two = findViewById( R.id.nearest_famous_place_two );



        button_interest = findViewById( R.id.button_interest );
        progress_bar = findViewById( R.id.progress_bar );


         PostTitle = getIntent().getStringExtra("post_title");
         Division = getIntent().getStringExtra("division");
         City = getIntent().getStringExtra("city");

        Address = getIntent().getStringExtra("address");

        Id = getIntent().getIntExtra("ad_id", 0);
        ApartmentNo = getIntent().getStringExtra("apartment_no");
        RentDate = getIntent().getStringExtra("rent_date");
        Tenant = getIntent().getStringExtra("tenant");

        Description = getIntent().getStringExtra("description");
        SpaceSize = getIntent().getStringExtra("space_size");
        Renters = getIntent().getStringExtra("renters");
        Utility = getIntent().getStringExtra("utility");
        Category = getIntent().getStringExtra("category");
        Floor = getIntent().getStringExtra("floor");

        Bedroom = getIntent().getStringExtra("bedroom");
        Bathroom = getIntent().getStringExtra("bathroom");
        Balconi = getIntent().getStringExtra("balconi");
        Kitchen = getIntent().getStringExtra("kitchen");
        DiningRoom = getIntent().getStringExtra("dining_room");
        DrawingRoom = getIntent().getStringExtra("drawing_room");
        Garage = getIntent().getStringExtra("garage");

        ClosingTime = getIntent().getStringExtra("closing_time");
        OpeningTime = getIntent().getStringExtra("opening_time");
        NearestFamousPlaceOne = getIntent().getStringExtra("nearest_famous_place_one");
        NearestFamousPlaceTwo = getIntent().getStringExtra("nearest_famous_place_two");

        FeaturedImage = "https://propertyrental.againwish.com/"+getIntent().getStringExtra("featured_image");

        division.setText( Division );
        city.setText( City );

        address.setText( Address );

        post_title.setText( PostTitle );
        id.setText(Integer.toString(Id));
        apartment_no.setText(ApartmentNo );
        rent_date.setText(RentDate);
        tenant.setText( Tenant );

        description.setText( Description );
        space_size.setText( SpaceSize+"ft2" );
        renters.setText( Renters+"tk" );
        utility.setText( Utility+"tk" );
        category.setText( Category);
        floor.setText(Floor);


        if(Category.equals( "Apartment" ) || Category.equals( "Flat" ))
        {
            extra_information.setVisibility( View.VISIBLE );
        }else {
            extra_information.setVisibility( View.GONE );
        }


        bedroom.setText( Bedroom);
        bathroom.setText( Bathroom);
        balconi.setText( Balconi);

        if (Kitchen.equals( "1" ))
        {
            kitchen.setText( "Available");
        }else {
            kitchen.setText( "Unavailable");
        }

        if (DiningRoom.equals( "1" )){
            dining_room.setText( "Available" );
        }else {
            dining_room.setText( "Unavailable" );
        }

        if (DrawingRoom.equals( "1" )){
            drawing_room.setText( "Available" );
        }else {
            drawing_room.setText( "Unavailable" );
        }

        if (Garage.equals( "1" )){
            garage.setText( "Available" );
        }else {
            garage.setText( "Unavailable" );
        }

        opening_time.setText( OpeningTime );
        closing_time.setText( ClosingTime );
        nearest_famous_place_one.setText( NearestFamousPlaceOne );
        nearest_famous_place_two.setText( NearestFamousPlaceTwo );


        ad_id = String.valueOf(Id);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.image_load_bg).error(R.drawable.image_load_bg);
        Glide.with(this).load(FeaturedImage).apply(requestOptions).into(featured_image);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Token = sharedPreferences.getString(KEY_TOKEN, "");
        UserId = sharedPreferences.getInt(KEY_ID, 0);
        User_name = sharedPreferences.getString(KEY_NAME, "");
        User_mobile = sharedPreferences.getString(KEY_MOBILE, "");

        User_id = String.valueOf( UserId );

        button_interest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress_bar.setVisibility( View.VISIBLE );
                button_interest.setVisibility( View.GONE );

                Call <UserInterestResponce> call = RetrofitClient.getInstance(Token).getApiInterface().userInterest(ad_id,PostTitle,Division,City,Address,Category,Renters,RentDate,User_id,User_name,User_mobile);
                call.enqueue(new Callback <UserInterestResponce>() {
                    @Override
                    public void onResponse(Call<UserInterestResponce> call, Response <UserInterestResponce> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.code());
                            UserInterestResponce userInterestResponce = response.body();
                            if (userInterestResponce != null && userInterestResponce.getStatus() == 1) {

                                progress_bar.setVisibility( View.GONE );
                                button_interest.setVisibility( View.VISIBLE );
                                showDialog();

                            } else {
                                Toast.makeText(AdViewActivity.this,userInterestResponce.getStatus(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "onResponse: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserInterestResponce> call, Throwable t) {
                        Toast.makeText(AdViewActivity.this,"Data Featching Faild", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }



    void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View viewLayout = inflater.inflate(R.layout.interest_dialog , null);
        dialog.setView(viewLayout);
        Button ok = (Button) viewLayout.findViewById(R.id.logok);
        final AlertDialog  alertDialog=dialog.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

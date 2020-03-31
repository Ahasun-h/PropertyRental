package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MyInterestViewModel.MyInterestViewResponce;
import com.example.myapplication.R;
import com.example.myapplication.networking.RetrofitClient;

public class MyInterestAdView extends AppCompatActivity {


    String TAG = "AdViewActivity";

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";

    ImageView featured_image;
    TextView address;

    TextView id,division,post_title,city,apartment_no,rent_date,tenant;
    TextView description,space_size,renters,utility,category,floor;
    LinearLayout extra_information;
    TextView bedroom,bathroom,balconi,kitchen,dining_room,drawing_room,garage;
    TextView closing_time,opening_time,nearest_famous_place_one,nearest_famous_place_two;

    Button button_interest;
    ProgressBar progress_bar,progress_bar_main;
    LinearLayout all_content;

    String PostTitle,Division,City,Address,ApartmentNo,RentDate,Tenant,Description,SpaceSize,Renters,Utility,Category,Floor,Bedroom,Bathroom,Balconi,Kitchen,DiningRoom,DrawingRoom,Garage,ClosingTime,OpeningTime,NearestFamousPlaceOne,NearestFamousPlaceTwo,FeaturedImage;
    String User_name,User_id,User_mobile,Token,ad_id,PostId;

    int Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_my_interest_ad_view );

        featured_image = findViewById( R.id.featured_image );
        address = findViewById( R.id.address );

        all_content = findViewById( R.id.all_content );
        all_content.setVisibility( View.GONE );

        progress_bar_main = findViewById( R.id.progress_bar_main );
        progress_bar_main.setVisibility( View.VISIBLE );

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


        PostId = getIntent().getStringExtra("ad_id");
        Log.e( TAG, "onCreate_ad_id: "+PostId );

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Token = sharedPreferences.getString(KEY_TOKEN, "");

        fetchData();
    }

    private void fetchData() {

        Call<MyInterestViewResponce> call = RetrofitClient.getInstance(Token).getApiInterface().myInterestViewResponce(PostId);
        call.enqueue( new Callback <MyInterestViewResponce>() {
            @Override
            public void onResponse(Call <MyInterestViewResponce> call, Response <MyInterestViewResponce> response) {
                if (response.isSuccessful()){
                    MyInterestViewResponce myInterestViewResponce = response.body();

                    Log.e( TAG, "onResponseMain: "+myInterestViewResponce.toString());
                    PostTitle = myInterestViewResponce.getPostTitle();
                    Log.e( TAG, "myInterestViewResponce_PostTitle: "+myInterestViewResponce.getPostTitle());
                    Division = myInterestViewResponce.getDivision();
                    Log.e( TAG, "myInterestViewResponce: "+myInterestViewResponce.getDivision());
                    City = myInterestViewResponce.getCity();
                    Address = myInterestViewResponce.getAddress();
                    ApartmentNo = myInterestViewResponce.getApartmentNo();
                    RentDate = myInterestViewResponce.getRentDate();
                    Tenant = myInterestViewResponce.getTenant();
                    Description = myInterestViewResponce.getDescription();
                    SpaceSize = myInterestViewResponce.getSpaceSize();
                    Renters = myInterestViewResponce.getRenters();
                    Utility = myInterestViewResponce.getUtility();
                    Category = myInterestViewResponce.getCategory();

                    Log.e( TAG, "Category: "+myInterestViewResponce.getCategory());

                    Floor = myInterestViewResponce.getFloor();
                    Bedroom = myInterestViewResponce.getBedroom();
                    Bathroom = myInterestViewResponce.getBathroom();
                    Balconi = myInterestViewResponce.getBalconi();
                    Kitchen = myInterestViewResponce.getKitchen();
                    DiningRoom = myInterestViewResponce.getDiningRoom();
                    DrawingRoom = myInterestViewResponce.getDrawingRoom();
                    Garage = myInterestViewResponce.getGarage();
                    ClosingTime = myInterestViewResponce.getClosingTime();
                    OpeningTime = myInterestViewResponce.getOpeningTime();
                    NearestFamousPlaceOne = myInterestViewResponce.getNearestFamousPlaceOne();
                    NearestFamousPlaceTwo = myInterestViewResponce.getNearestFamousPlaceTwo();
                    FeaturedImage =  "https://propertyrental.againwish.com/"+myInterestViewResponce.getFeaturedImage();

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


//                    if(myInterestViewResponce.getCategory().equals( "Apartment" ) || myInterestViewResponce.getCategory().equals( "Flat" ))
//                    {
//                        extra_information.setVisibility( View.VISIBLE );
//                    }else {
//                        extra_information.setVisibility( View.GONE );
//                    }


                    bedroom.setText( Bedroom);
                    bathroom.setText( Bathroom);
                    balconi.setText( Balconi);

//                    if (Kitchen.equals( "1" ))
//                    {
//                        kitchen.setText( "Available");
//                    }else {
//                        kitchen.setText( "Unavailable");
//                    }
//
//                    if (DiningRoom.equals( "1" )){
//                        dining_room.setText( "Available" );
//                    }else {
//                        dining_room.setText( "Unavailable" );
//                    }
//
//                    if (DrawingRoom.equals( "1" )){
//                        drawing_room.setText( "Available" );
//                    }else {
//                        drawing_room.setText( "Unavailable" );
//                    }
//
//                    if (Garage.equals( "1" )){
//                        garage.setText( "Available" );
//                    }else {
//                        garage.setText( "Unavailable" );
//                    }

                    opening_time.setText( OpeningTime );
                    closing_time.setText( ClosingTime );
                    nearest_famous_place_one.setText( NearestFamousPlaceOne );
                    nearest_famous_place_two.setText( NearestFamousPlaceTwo );


                    progress_bar_main.setVisibility( View.GONE );
                    all_content.setVisibility( View.VISIBLE );

                }else {
                    Toast.makeText( MyInterestAdView.this, "Fail", Toast.LENGTH_SHORT ).show();

                }
            }

            @Override
            public void onFailure(Call <MyInterestViewResponce> call, Throwable t) {
                Toast.makeText( MyInterestAdView.this, "onFailure", Toast.LENGTH_SHORT ).show();
                progress_bar_main.setVisibility( View.GONE );
                Log.e( TAG, "onFailure: " );
            }
        } );


    }


}

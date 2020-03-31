package com.example.myapplication.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Activity.ProfileUpdate;
import com.example.myapplication.Adapter.MyPostRecylerViewAdapter;
import com.example.myapplication.MyPostRecylerViewModel.MyPost;
import com.example.myapplication.MyPostRecylerViewModel.MyPostRecylerViewResponce;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    RecyclerView my_post_recyler_view;

    FrameLayout fragmentContiner;

    private MyPostRecylerViewResponce myPostRecylerViewResponce;
    private List<MyPost> myPostList = new ArrayList<>();

    String TAG="ProfileFragment ";
    //    String TAG,auth="Bearer "+"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9oYWJpYjAxOS4wMDB3ZWJob3N0YXBwLmNvbVwvYXBpXC9hdXRoXC91c2VyX2xvZ2luIiwiaWF0IjoxNTgwNjYyODczLCJleHAiOjE1ODA2NjY0NzMsIm5iZiI6MTU4MDY2Mjg3MywianRpIjoiQ0VqQTFmZDdVc2VrWFQ5RiIsInN1YiI6MywicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.lxhgKxjrN9K-AyXNhEqov0hkqxGSw3goZhy_ERLcOBU";
    String name,email,mobile,address,profileImage,mProfilteImage;
    private Context context;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_ID = "keyid";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyeemail";
    private static final String KEY_ADDRESS = "keyeaddress";
    private static final String KEY_MOBILE = "keyemobile";
    private static final String KEY_PROFILE_IMAGE = "keyeprofileimage";
    String token,id;
    int Id;


    TextView userName,userEmail,userAddress,userNumber;
    CircleImageView profile_image;
    Button update_profile;

    TabLayout tabLayout;
    FrameLayout viewPager;



    MyPostRecylerViewAdapter myPostRecylerViewAdapter;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
        Id = sharedPreferences.getInt(KEY_ID,0);
        name = sharedPreferences.getString( KEY_NAME,"" );
        email = sharedPreferences.getString( KEY_EMAIL,"" );
        address = sharedPreferences.getString( KEY_ADDRESS,"" );
        mobile = sharedPreferences.getString( KEY_MOBILE,"" );
        profileImage = sharedPreferences.getString( KEY_PROFILE_IMAGE,"" );

        Log.e(TAG, "onAttach: id"+Id );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);



        userName =(TextView)view.findViewById(R.id.userName);
        userEmail =(TextView)view.findViewById(R.id.userEmail);
        userAddress =(TextView)view.findViewById(R.id.userAddress);
        userNumber = (TextView)view.findViewById(R.id.userNumber);
        profile_image =(CircleImageView) view.findViewById(R.id.profile_image);

        update_profile = (Button) view.findViewById(R.id.update_profile);

        fragmentContiner = (FrameLayout) view.findViewById(R.id.fragmentContiner);

        userName.setText( name );
        userEmail.setText( email );
        userAddress.setText( address );
        userNumber.setText( mobile );



        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        viewPager = (FrameLayout)view.findViewById(R.id.viewPager);
//        viewPager.setBackgroundResource(R.drawable.frma_layout_bg);


        mProfilteImage = "https://propertyrental.againwish.com/"+profileImage;



//        tabLayout.addTab(tabLayout.newTab().setText("My Add,s"));
//        tabLayout.addTab(tabLayout.newTab().setText("Rent History"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//
//        final ProfileTabViewAdapter adapter = new ProfileTabViewAdapter(getActivity(),getFragmentManager(), tabLayout.getTabCount());
//        //Adding Fragment;
//        adapter.AddFragment( new MyPostRecylerView(),"My Add,s" );
//        adapter.AddFragment( new MyRentHistory(),"Rent History" );
//
//
//        //set Addapter
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager( viewPager );
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.profile_user_icon).error(R.drawable.profile_user_icon);
        Glide.with(this).load(mProfilteImage).apply(requestOptions).into(profile_image);


        ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, new MyPostRecylerView()).commit();

        //          Test Code  For  Tab Lay Out
        TabLayout.Tab myPost = tabLayout.newTab();
        myPost.setText("My Post");
        tabLayout.addTab(myPost);

        TabLayout.Tab profileUpdate = tabLayout.newTab();
        profileUpdate.setText("Interested");
        tabLayout.addTab(profileUpdate);

        TabLayout.Tab history = tabLayout.newTab();
        history.setText("History");
        tabLayout.addTab(history);



// perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new MyPostRecylerView();
                        break;
                    case 1:
                        fragment = new MyInterest();
                        break;

                    case 2:
                        fragment = new MyRentHistory();
                        break;
                }
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.viewPager, fragment);
                ft.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

//          Test Code  For  Tab Lay Out





        update_profile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( getContext(), ProfileUpdate.class );
                intent.putExtra("token", token);
                startActivity( intent );

            }
        } );

//        ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContiner, new ProfileFragment()).commit();

        return view;
    }


}

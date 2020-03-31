package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myapplication.Fragment.AddPostFragment;
import com.example.myapplication.Fragment.CategoryFragment;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.NotificationFragment;
import com.example.myapplication.Fragment.ProfileFragment;
import com.example.myapplication.R;
import com.example.myapplication.SessionClass.SessionClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout fragmentContiner;

    private HomeFragment homeFragment;
    private AddPostFragment addPostFragment;
    private CategoryFragment categoryFragment;
    private NotificationFragment notificationFragment;
    private ProfileFragment profileFragment;

    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fragmentContiner = findViewById(R.id.fragmentContiner);

        homeFragment = new HomeFragment();
        addPostFragment = new AddPostFragment();
        categoryFragment = new CategoryFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();

        ((AppCompatActivity) MainActivity.this).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContiner, new HomeFragment()).commit();




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        InitialFragment(homeFragment);
                        return true;

                    case R.id.category:
                        InitialFragment(categoryFragment);
                        return true;

                    case R.id.add:
                        InitialFragment(addPostFragment);
                        return true;

                    case R.id.inbox:
                        InitialFragment( notificationFragment );
                        return true;

                    case R.id.profile:
                        InitialFragment(profileFragment);
                        return true;
                }
             return false;

            }
        });
    }



   public void  InitialFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContiner,fragment);
        fragmentTransaction.commit();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        action = menu;

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_logout:

                /*new LoginRegisterClass(Profile.this).removeEmail();
                Intent intent = new Intent( Profile.this,loginactivity.class );
                startActivity( intent );
                finish();*/

                SessionClass.getInstance(getApplicationContext()).logout();
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}

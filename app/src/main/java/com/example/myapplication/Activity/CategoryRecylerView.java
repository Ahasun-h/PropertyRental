package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.AdPostRecylerViewResponce.ActivePost;
import com.example.myapplication.Adapter.CategoryRecylerViewAdapter;
import com.example.myapplication.CategoryRecyclerViewModel.CategoryActivePost;
import com.example.myapplication.CategoryRecyclerViewModel.CategoryRecyclerViewResponse;
import com.example.myapplication.R;
import com.example.myapplication.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRecylerView extends AppCompatActivity {


    String category,token;
    RecyclerView ad_recyler_view;
    String TAG="CategoryRecylerView";

    EditText aa_search;

    LinearLayout loding,no_result,search;

    CategoryRecylerViewAdapter categoryRecylerViewAdapter;
    RecyclerView myPostRecylerView;
    CategoryRecyclerViewResponse categoryRecyclerViewResponse;

    private List<CategoryActivePost> myPostList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.category_recyler_view);

        category = getIntent().getStringExtra("category");
        Log.e( TAG,"CategoryRecyclerViewResponseCategory : "+category);
        token = getIntent().getStringExtra("token");

        aa_search = findViewById( R.id.aa_search );

        aa_search.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        } );

        loding = findViewById( R.id.loding );
        loding.setVisibility( View.VISIBLE );

        no_result = findViewById( R.id.no_result );
        no_result.setVisibility( View.GONE );

        search = findViewById( R.id.search );
        search.setVisibility( View.GONE );



        ad_recyler_view = findViewById( R.id.ad_recyler_view );

        categoryRecylerViewAdapter = new CategoryRecylerViewAdapter(CategoryRecylerView.this, myPostList );
        ad_recyler_view.setLayoutManager(new LinearLayoutManager(this));
        ad_recyler_view.setAdapter(categoryRecylerViewAdapter);



        fetchCategoryAd();




    }

    private void filter(String text) {
        ArrayList<CategoryActivePost> newList = new ArrayList <>(  );
        for (CategoryActivePost itemList : myPostList)
        {
            if (itemList.getAddress().toLowerCase().contains(text.toLowerCase())) {
                newList.add(itemList);
            }
        }
        categoryRecylerViewAdapter.updateList(newList);
    }


    private void fetchCategoryAd() {

        Log.e( TAG,"CategoryRecyclerViewResponseToken"+token);
        loding.setVisibility( View.VISIBLE );
        search.setVisibility( View.GONE );
        no_result.setVisibility( View.GONE );

        Call<CategoryRecyclerViewResponse> call = RetrofitClient.getInstance(token).getApiInterface().categoryPostData(category);
        call.enqueue( new Callback <CategoryRecyclerViewResponse>() {
            @Override
            public void onResponse(Call <CategoryRecyclerViewResponse> call, Response <CategoryRecyclerViewResponse> response) {
                categoryRecyclerViewResponse = response.body();
                if (response.isSuccessful()){
                    myPostList = categoryRecyclerViewResponse.getCategoryActivePosts();

                    if (categoryRecyclerViewResponse.getCategoryActivePosts().size() != 0){
                        categoryRecylerViewAdapter.updateList( myPostList );
                        loding.setVisibility( View.GONE );
                        search.setVisibility( View.VISIBLE );
                        Log.e( TAG,"CategoryRecyclerViewResponse"+myPostList.size() );
                        Toast.makeText(CategoryRecylerView.this, "Ok", Toast.LENGTH_SHORT ).show();
                    }
                    else {
                        no_result.setVisibility( View.VISIBLE );
                        loding.setVisibility( View.GONE );
                        Log.e( TAG,"no_result : Its Work" );
                    }
                }
            }
            @Override
            public void onFailure(Call <CategoryRecyclerViewResponse> call, Throwable t) {
                Toast.makeText( CategoryRecylerView.this, "Fail", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}

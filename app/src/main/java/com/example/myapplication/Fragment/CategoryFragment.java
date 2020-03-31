package com.example.myapplication.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Activity.CategoryRecylerView;
import com.example.myapplication.Adapter.CategoryRecylerViewAdapter;
import com.example.myapplication.CategoryRecyclerViewModel.CategoryActivePost;
import com.example.myapplication.CategoryRecyclerViewModel.CategoryRecyclerViewResponse;
import com.example.myapplication.R;
import com.example.myapplication.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {




    RecyclerView ad_recyler_view;

    EditText aa_search;

    LinearLayout loding,no_result,search,catagory_buttons;

    CategoryRecylerViewAdapter categoryRecylerViewAdapter;
    RecyclerView myPostRecylerView;
    CategoryRecyclerViewResponse categoryRecyclerViewResponse;

    private List <CategoryActivePost> myPostList =new ArrayList <>();



    LinearLayout flat,apartment,store,office,garage,warehouse;
    Spinner loaction;

    ArrayAdapter<String> arrayAdapter;


    public CategoryFragment() {
        // Required empty public constructor
    }

    String TAG="AddPostFragment ";
    private Context context;
    private Context mCobtext;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";
    String token,category;
    // String TAG="AddPostFragment ";




    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
        Log.e(TAG, "onAttach: "+token );

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_category, container, false);

        flat = (LinearLayout)view.findViewById(R.id.flat);
        apartment = (LinearLayout)view.findViewById(R.id.apartment);
        store = (LinearLayout)view.findViewById(R.id.store);
        office = (LinearLayout)view.findViewById(R.id.office);
        garage = (LinearLayout)view.findViewById(R.id.garage);
        warehouse = (LinearLayout)view.findViewById(R.id.warehouse);






        aa_search = view.findViewById( R.id.aa_search );

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

        catagory_buttons = view.findViewById( R.id.catagory_buttons );
        catagory_buttons.setVisibility( View.VISIBLE );

        loding = view.findViewById( R.id.loding );
        loding.setVisibility( View.GONE );

        no_result = view.findViewById( R.id.no_result );
        no_result.setVisibility( View.GONE );

        search = view.findViewById( R.id.search );
        search.setVisibility( View.GONE );



        ad_recyler_view = view.findViewById( R.id.ad_recyler_view );

        categoryRecylerViewAdapter = new CategoryRecylerViewAdapter(getContext(), myPostList );
        ad_recyler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        ad_recyler_view.setAdapter(categoryRecylerViewAdapter);















        flat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( getContext(), CategoryRecylerView.class );
//                intent.putExtra("category","Flat");
//                intent.putExtra("token",token);
//                startActivity( intent );

                catagory_buttons.setVisibility( View.GONE );
                loding.setVisibility( View.VISIBLE );


                category = "Flat";
                fetchCategoryAd();


            }
        } );

        apartment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( getContext(), CategoryRecylerView.class );
//                intent.putExtra("category","Apartment");
//                intent.putExtra("token",token);
//                startActivity( intent );
                catagory_buttons.setVisibility( View.GONE );
                loding.setVisibility( View.VISIBLE );

                category = "Apartment";
                fetchCategoryAd();

            }
        } );

        store.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( getContext(), CategoryRecylerView.class );
//                intent.putExtra("category","Store");
//                intent.putExtra("token",token);
//                startActivity( intent );
                catagory_buttons.setVisibility( View.GONE );
                loding.setVisibility( View.VISIBLE );

                category = "Store";
                fetchCategoryAd();
            }
        } );

        office.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( getContext(), CategoryRecylerView.class );
//                intent.putExtra("category","Office");
//                intent.putExtra("token",token);
//                startActivity( intent );
                catagory_buttons.setVisibility( View.GONE );
                loding.setVisibility( View.VISIBLE );

                category = "Office";
                fetchCategoryAd();
            }
        } );

        garage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( getContext(), CategoryRecylerView.class );
//                intent.putExtra("category","Garage");
//                intent.putExtra("token",token);
//                startActivity( intent );
                catagory_buttons.setVisibility( View.GONE );
                loding.setVisibility( View.VISIBLE );

                category = "Garage";
                fetchCategoryAd();
            }
        } );

        warehouse.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( getContext(), CategoryRecylerView.class );
//                intent.putExtra("category","Warehouse");
//                intent.putExtra("token",token);
//                startActivity( intent );
                catagory_buttons.setVisibility( View.GONE );
                loding.setVisibility( View.VISIBLE );

                category = "Warehouse";
                fetchCategoryAd();
            }
        } );

        return view;
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

        Call <CategoryRecyclerViewResponse> call = RetrofitClient.getInstance(token).getApiInterface().categoryPostData(category);
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
                        Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT ).show();

                        category = null;

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
                Toast.makeText( getContext(), "Fail", Toast.LENGTH_SHORT ).show();
            }
        } );
    }







}

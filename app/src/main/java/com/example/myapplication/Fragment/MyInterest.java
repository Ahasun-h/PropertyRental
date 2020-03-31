package com.example.myapplication.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.Adapter.MyInterestAdapter;
import com.example.myapplication.Adapter.MyPostRecylerViewAdapter;
import com.example.myapplication.MyInterestModel.MyInterestResponce;
import com.example.myapplication.MyPostRecylerViewModel.MyPost;
import com.example.myapplication.MyPostRecylerViewModel.MyPostRecylerViewResponce;
import com.example.myapplication.R;
import com.example.myapplication.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInterest extends Fragment {

    String TAG="MyInterest ";
    private Context context;
    private Context mCobtext;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_ID = "keyid";
    String token;
    int id;

    RecyclerView myPostRecylerView;
    MyInterestAdapter myInterestAdapter;

    MyInterestResponce myInterestResponce;

    private List <com.example.myapplication.MyInterestModel.MyInterest> myPostList =new ArrayList <>();

    LinearLayout loding,no_result;

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(KEY_TOKEN, "");
        id = sharedPreferences.getInt(KEY_ID,0);
        Log.e(TAG, "MyPostID: "+token );

    }


    public MyInterest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_my_interest, container, false );

        loding = view.findViewById(R.id.loding);
        loding.setVisibility( View.VISIBLE );

        no_result = view.findViewById(R.id.no_result);
        no_result.setVisibility( View.GONE );

        myPostRecylerView = (RecyclerView)view.findViewById( R.id.my_post_recyler_view );

        myInterestAdapter = new MyInterestAdapter(getContext(),myPostList);
        myPostRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myPostRecylerView.setAdapter(myInterestAdapter);



        fetchMyAd();

        return view;
    }


    private void fetchMyAd() {


        Call <MyInterestResponce> call = RetrofitClient.getInstance(token).getApiInterface().myInterestResponce( id );
        call.enqueue( new Callback <MyInterestResponce>() {
            @Override
            public void onResponse(Call <MyInterestResponce> call, Response <MyInterestResponce> response) {
                myInterestResponce = response.body();
                if (response.isSuccessful()){
                    myPostList = myInterestResponce.getMyInterest();

                    Log.d( TAG,"myPostRecylerViewResponce"+  myPostList.toString());

                    if (myInterestResponce.getMyInterest().size() != 0){
                        myInterestAdapter.updateList( myPostList );

//                    Toast.makeText( getActivity(), "Ok", Toast.LENGTH_SHORT ).show();

                        loding.setVisibility( View.GONE );
                        myPostRecylerView.setVisibility( View.VISIBLE );
                    }else {
                        loding.setVisibility( View.GONE );
                        no_result.setVisibility( View.VISIBLE );

                    }


                }
            }

            @Override
            public void onFailure(Call <MyInterestResponce> call, Throwable t) {
                Toast.makeText( getContext(), "Fail", Toast.LENGTH_SHORT ).show();

            }
        } );

    }

}

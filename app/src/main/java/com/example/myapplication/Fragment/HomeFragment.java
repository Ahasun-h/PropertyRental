package com.example.myapplication.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.myapplication.AdPostRecylerViewResponce.ActivePost;
import com.example.myapplication.AdPostRecylerViewResponce.AdRecylerViewResponse;
import com.example.myapplication.Adapter.AdPostRecylerViewAdapter;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.R;
import com.example.myapplication.networking.ApiInterface;
import com.example.myapplication.networking.RetrofitClient;
import com.example.myapplication.utils.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView AdRecylerView;
    
    AdPostRecylerViewAdapter adPostRecylerViewAdapter;
    LinearLayout loding;
    ProgressBar progress_bar_pagination;
    EditText search;


    LinearLayoutManager linearLayoutManager;


    private ApiInterface apiInterface;


    private List<ActivePost> postList=new ArrayList<>();
    private AdRecylerViewResponse recylerViewResponse;


    //-----------Pagination Variable--------------

//    private static final int START_PAGE = 1;
//    private static int TOTAL_PAGES = 2;
//    private boolean isLoading = false;
//    private boolean isLastPage = false;
//    private int CURRENT_PAGE = START_PAGE;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES;
    private int currentPage = PAGE_START;

    //-----------End Pagination Variable--------------



    public HomeFragment() {
        // Required empty public constructor
    }


    String TAG="AddPostFragment ";
    private Context context;
    private Context mCobtext;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_ID = "keyid";
    String token;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, parent, false);


        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        progress_bar_pagination = view.findViewById(R.id.progress_bar_pagination);

        loding = view.findViewById(R.id.loding);
        loding.setVisibility( View.VISIBLE );

        AdRecylerView = view.findViewById(R.id.ad_recyler_view);

        adPostRecylerViewAdapter = new AdPostRecylerViewAdapter(getContext(),postList);
//        AdRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        AdRecylerView.setAdapter(adPostRecylerViewAdapter);

        AdRecylerView.setLayoutManager( linearLayoutManager );
        AdRecylerView.setItemAnimator(new DefaultItemAnimator());
        AdRecylerView.setAdapter(adPostRecylerViewAdapter);


//-----------Pagination --------------
        AdRecylerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress_bar_pagination.setVisibility( View.VISIBLE );
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


//-----------Pagination  End--------------


        search = view.findViewById( R.id.search );

        fetchAd();

         return view;
    }





    private void fetchAd() {

        Log.d(TAG, "loadFirstPage: ");

        String page = String.valueOf(currentPage);
//        String page = "?page="+pageAA;
        Log.e(TAG, "PageBBB: "+page);

        Call<AdRecylerViewResponse> call = RetrofitClient.getInstance(token).getApiInterface().recylerViewData(page);
            call.enqueue(new Callback<AdRecylerViewResponse>() {
                @Override
                public void onResponse(Call<AdRecylerViewResponse> call, Response<AdRecylerViewResponse> response) {
                    recylerViewResponse = response.body();
                    if (response.isSuccessful()){
                        postList=recylerViewResponse.getActivePosts();
                        TOTAL_PAGES = recylerViewResponse.getTotalPages();
                        Log.e(TAG, "onResponseBB: "+recylerViewResponse.getActivePosts().size()+" " );
//                        TOTAL_PAGES = recylerViewResponse.getTotalPages();
                        adPostRecylerViewAdapter.updateList(postList);

                        if (currentPage <= TOTAL_PAGES) adPostRecylerViewAdapter.addLoadingFooter();
                        else isLastPage = true;



                        Log.e(TAG, "onResponseAAA: "+postList.size()+" " );

                        loding.setVisibility( View.GONE );
                        AdRecylerView.setVisibility( View.VISIBLE );
                    }
                }

                @Override
                public void onFailure(Call<AdRecylerViewResponse> call, Throwable t) {

                }
            });
        }


    private List<ActivePost> fetchResults(Response<AdRecylerViewResponse> response) {
        AdRecylerViewResponse adRecylerViewResponse = response.body();
        return adRecylerViewResponse.getActivePosts();
    }

    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);



        String page = String.valueOf(currentPage);
//        String page = "?page="+pageAA;

        Call<AdRecylerViewResponse> call = RetrofitClient.getInstance(token).getApiInterface().recylerViewData(page);
        call.enqueue(new Callback<AdRecylerViewResponse>() {
            @Override
            public void onResponse(Call<AdRecylerViewResponse> call, Response<AdRecylerViewResponse> response) {
                adPostRecylerViewAdapter.removeLoadingFooter();
                isLoading = false;

                List<ActivePost> results = fetchResults(response);
                adPostRecylerViewAdapter.addAll(results);

                progress_bar_pagination.setVisibility( View.GONE );

                if (currentPage != TOTAL_PAGES) adPostRecylerViewAdapter.addLoadingFooter();
                else isLastPage = true;

                progress_bar_pagination.setVisibility( View.GONE );
            }

            @Override
            public void onFailure(Call<AdRecylerViewResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}

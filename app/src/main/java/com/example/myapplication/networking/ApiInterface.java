package com.example.myapplication.networking;


import com.example.myapplication.AdPostRecylerViewResponce.AdRecylerViewResponse;
import com.example.myapplication.AdPostResponce.AdPostResponce;
import com.example.myapplication.CategoryRecyclerViewModel.CategoryRecyclerViewResponse;
import com.example.myapplication.ChangeMyPostStatusModel.ChangeMyPostStatusResponce;
import com.example.myapplication.HistoryModel.HistoryResponce;
import com.example.myapplication.MyInterestModel.MyInterestResponce;
import com.example.myapplication.MyInterestViewModel.MyInterestViewResponce;
import com.example.myapplication.MyPostRecylerViewModel.MyPostRecylerViewResponce;
import com.example.myapplication.NotificationModel.NotificationResponce;
import com.example.myapplication.ProfileModel.ProfileResponse;
import com.example.myapplication.ProfileUpdateModel.ProfileUpdateResponce;
import com.example.myapplication.RegisterModel.RegisterResponse;
import com.example.myapplication.UserInterestModel.UserInterestResponce;
import com.example.myapplication.loginModel.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("auth/user_login")
    Call<LoginResponse> userLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/user_register")
    Call<RegisterResponse> userRegister(@Field("name") String name, @Field("email") String email , @Field("mobile") String mobile , @Field("password") String password);


//    @Headers({ "Content-Type: application/json;Accept: application/json"})
//    @POST("auth/user_profile")
//    Call<ProfileResponse> profileData(@Header("Authorization") String auth);



    @Headers({"Accept: application/json"})
    @POST("auth/user_profile")
    Call<ProfileResponse> profileData();

    @FormUrlEncoded
    @POST("post/save_ad")
    Call<AdPostResponce> adPostData(@Field("post_title") String post_title ,
                                    @Field("division") String division ,
                                    @Field("city") String city ,
                                    @Field("authority_types") String authority_types ,
                                    @Field("address") String address ,
                                    @Field("apartment_no") String apartment_no,
                                    @Field("rent_date") String rent_date,
                                    @Field("tenant") String tenant,
                                    @Field("description") String description,

                                    @Field("space_size") String space_size,
                                    @Field("renters") String renters,
                                    @Field("utility") String utility,

                                    @Field("category") String category,
                                    @Field("floor") String floor,

                                    @Field("bedroom") String bedroom,
                                    @Field("bathroom") String bathroom,
                                    @Field("balconi") String balconi,
                                    @Field("kitchen") String kitchen,
                                    @Field("dining_room") String dining_room,
                                    @Field("drawing_room") String drawing_room,
                                    @Field("garage") String garage,

                                    @Field("closing_time") String closing_time,
                                    @Field("opening_time") String opening_time,
                                    @Field("nearest_famous_place_one") String nearest_famous_place_one,
                                    @Field("nearest_famous_place_two") String nearest_famous_place_two,

                                    @Field("featured_image") String featured_image,
//                                    @Field("more_image") String more_image,
//                                    @Field("more_image_two") String more_image_two,
//                                    @Field("more_image_three") String more_image_three,
//                                    @Field("more_image_four") String more_image_four,
//                                    @Field("more_image_five") String more_image_five,
//                                    @Field("more_image_six") String more_image_six,


                                    @Field("user_id") String user_id,

                                    @Field("mobile") String mobile );


    //@FormUrlEncoded
    @GET("post/show_ad?page=")
    Call<AdRecylerViewResponse> recylerViewData(@Query("page") String page);

    @FormUrlEncoded
    @POST("auth/profile_update")
    Call<ProfileUpdateResponce> profileUpdateData(@Field("name") String name,
                                                  @Field("email") String email,
                                                  @Field("address") String address,
                                                  @Field("user_profile_photo") String user_profile_photo);

    @Headers({"Accept: application/json"})
    @GET("post/show_my_post/{id}")
    Call<MyPostRecylerViewResponce> myPostData(@Path("id") int id);


    @Headers({"Accept: application/json"})
    @GET("post/show_ad_category/{category}")
    Call<CategoryRecyclerViewResponse> categoryPostData(@Path("category") String category);


    @FormUrlEncoded
    @POST("interest/user_interest")
    Call<UserInterestResponce> userInterest(@Field("post_id") String post_id ,
                                            @Field("post_title") String post_title ,
                                            @Field("post_division") String post_division ,
                                            @Field("post_city") String post_city ,
                                            @Field("post_address") String post_address ,
                                            @Field("post_category") String post_category ,
                                            @Field("renters") String renters ,
                                            @Field("rent_date") String rent_date ,
                                            @Field("user_id") String user_id ,
                                            @Field("user_name") String user_name ,
                                            @Field("user_mobile") String user_mobile );




    @Headers({"Accept: application/json"})
    @GET("notification/show_notification")
    Call<NotificationResponce> notificationData();


    @Headers({"Accept: application/json"})
    @GET("interest/show_my_interest/{user_id}")
    Call<MyInterestResponce> myInterestResponce(@Path("user_id") int user_id);


    @Headers({"Accept: application/json"})
    @GET("post/show_my_post_view/{ad_id}")
    Call<MyInterestViewResponce> myInterestViewResponce(@Path("ad_id") String PostId);

    @Headers({"Accept: application/json"})
    @GET("history/show_history/{customer_id}")
    Call<HistoryResponce> historyResponce(@Path("customer_id") String id);


    @FormUrlEncoded
    @POST("post/change_my_add_status/{id}")
    Call<ChangeMyPostStatusResponce> myPostStatusChange(@Path("id") int id, @Field( "status" ) int status);



    Call<AdRecylerViewResponse> recylerViewData(Object theMovieDbApiToken, int currentPage);
}

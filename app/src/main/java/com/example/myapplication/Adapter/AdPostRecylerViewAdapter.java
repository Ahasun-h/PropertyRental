package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.Activity.AdViewActivity;
import com.example.myapplication.AdPostRecylerViewResponce.ActivePost;
import com.example.myapplication.R;

import java.util.List;

public class AdPostRecylerViewAdapter extends RecyclerView.Adapter<AdPostRecylerViewAdapter.MyViewHolder> {



    String TAG="AdPostRecylerViewAdapter";
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded = false;


    private Context mContext;
    private List<ActivePost> postList;
    private RecyclerView.ViewHolder holder;
    private int position;

    //String Tag = "AdPostRecylerViewAdapter";

    public AdPostRecylerViewAdapter(Context mContext, List<ActivePost> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    public List<ActivePost> getMovies() {
        return postList;
    }

    public void setActivePost(List<ActivePost> ActivePostResult) {
        this.postList = ActivePostResult;
    }




//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder viewHolder = null;
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//        switch (viewType) {
//            case ITEM:
//                viewHolder = getViewHolder(parent, inflater);
//                break;
//            case LOADING:
//                View v2 = inflater.inflate(R.layout.ad_post_recyler_view_item, parent, false);
//                viewHolder = new LoadingVH(v2);
//                break;
//        }
//        return viewHolder;
//    }


    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.ad_post_recyler_view_item, parent, false);
        viewHolder = new MyViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.Category.setText( postList.get( position ).getCategory() );
        holder.post_title.setText(postList.get(position).getPostTitle());
        holder.Address.setText(postList.get( position ).getDivision()+","+postList.get( position ).getCity()+","+postList.get( position ).getAddress());
        holder.price.setText( postList.get( position ).getRenters());
        holder.post_date.setText( postList.get( position ).getCreatedAt());
        // Picasso.get().load(postList.get(position).getMoreImage()).into(holder.iv);

        String Url = "https://propertyrental.againwish.com/";

        ActivePost product =postList.get(position);
        Glide.with(mContext)
                .load(Url+product.getFeaturedImage())
                .into(holder.iv);
    }


    @Override
    public int getItemCount() {
        Log.e( TAG, "getItemCount: "+ postList.size() );
        return postList == null ? 0 : postList.size()-1;
    }


    @Override
    public int getItemViewType(int position) {
        return (position == postList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    public void add(ActivePost r) {
        postList.add(r);
        notifyItemInserted(postList.size() - 1);
    }

    public void addAll(List<ActivePost> moveResults) {
        for (ActivePost result : moveResults) {
            add(result);
        }
    }

    public void remove(ActivePost r) {
        int position = postList.indexOf(r);
        if (position > -1) {
            postList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }


    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ActivePost());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = postList.size() - 1;
        ActivePost result = getItem(position);

        if (result != null) {
            postList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ActivePost getItem(int position) {
        return postList.get(position);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.ad_post_recyler_view_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder( view );
        viewHolder.recyler_view_layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( mContext, AdViewActivity.class );
                intent.putExtra( "ad_id",postList.get( viewHolder.getAdapterPosition()).getId());
                intent.putExtra( "post_title",postList.get( viewHolder.getAdapterPosition()).getPostTitle());
                intent.putExtra( "division",postList.get( viewHolder.getAdapterPosition()).getDivision());
                intent.putExtra( "city",postList.get( viewHolder.getAdapterPosition()).getCity());
                intent.putExtra( "authority_types",postList.get( viewHolder.getAdapterPosition()).getAuthorityTypes());
                intent.putExtra( "address",postList.get( viewHolder.getAdapterPosition()).getAddress());
                intent.putExtra( "apartment_no",postList.get( viewHolder.getAdapterPosition()).getApartmentNo());
                intent.putExtra( "rent_date",postList.get( viewHolder.getAdapterPosition()).getRentDate());
                intent.putExtra( "tenant",postList.get( viewHolder.getAdapterPosition()).getTenant());
                intent.putExtra( "description",postList.get( viewHolder.getAdapterPosition()).getDescription());
                intent.putExtra( "space_size",postList.get( viewHolder.getAdapterPosition()).getSpaceSize());
                intent.putExtra( "renters",postList.get( viewHolder.getAdapterPosition()).getRenters());
                intent.putExtra( "utility",postList.get( viewHolder.getAdapterPosition()).getUtility());
                intent.putExtra( "category",postList.get( viewHolder.getAdapterPosition()).getCategory());
                intent.putExtra( "floor",postList.get( viewHolder.getAdapterPosition()).getFloor());
                intent.putExtra( "bedroom",postList.get( viewHolder.getAdapterPosition()).getBedroom());
                intent.putExtra( "bathroom",postList.get( viewHolder.getAdapterPosition()).getBathroom());
                intent.putExtra( "balconi",postList.get( viewHolder.getAdapterPosition()).getBalconi());
                intent.putExtra( "kitchen",postList.get( viewHolder.getAdapterPosition()).getKitchen());
                intent.putExtra( "dining_room",postList.get( viewHolder.getAdapterPosition()).getDiningRoom());
                intent.putExtra( "drawing_room",postList.get( viewHolder.getAdapterPosition()).getDrawingRoom());
                intent.putExtra( "garage",postList.get( viewHolder.getAdapterPosition()).getGarage());
                intent.putExtra( "closing_time",postList.get( viewHolder.getAdapterPosition()).getClosingTime());
                intent.putExtra( "opening_time",postList.get( viewHolder.getAdapterPosition()).getOpeningTime());
                intent.putExtra( "nearest_famous_place_one",postList.get( viewHolder.getAdapterPosition()).getNearestFamousPlaceOne());
                intent.putExtra( "nearest_famous_place_two",postList.get( viewHolder.getAdapterPosition()).getNearestFamousPlaceTwo());
                intent.putExtra( "featured_image",postList.get( viewHolder.getAdapterPosition()).getFeaturedImage());
                intent.putExtra( "more_image",postList.get( viewHolder.getAdapterPosition()).getMoreImage());

                mContext.startActivity(intent);

               // Log.d( Tag,"RecylerViewID"+ postList.get( viewHolder.getAdapterPosition()).getId().toString());

            }
        } );






        return viewHolder;

    }


//    @Override
//    public int getItemCount() {
//        return postList.size();
//    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView post_title,Address, Category, price,post_date;
        LinearLayout recyler_view_layout;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super( itemView );

            recyler_view_layout = itemView.findViewById( R.id.recyler_view_layout );


            Address = itemView.findViewById( R.id.address );
            Category = itemView.findViewById( R.id.category );
            post_title = itemView.findViewById( R.id.post_title );
            price = itemView.findViewById( R.id.price );
            post_date = itemView.findViewById( R.id.post_date );

            iv = itemView.findViewById(R.id.iv);
        }
    }


    public void updateList(List<ActivePost> postList){
        this.postList=postList;
        notifyDataSetChanged();
    }


//    public void add(ActivePost activePost){
//        postList.add( activePost );
//        notifyItemInserted( postList.size()-1 );
//    }
//
//    public void addAll(List<ActivePost> activePosts){
//        for (ActivePost a:activePosts)
//        {
//            add( a );
//        }
//    }
//
//    //Add Empty Item
//
//    public void addBottomItem(){
//        add( new ActivePost() );
//    }

    public void removedLastEmptyItem(){
        int position = postList.size();
        ActivePost item = getItem(position);

        if (item!=null){
            postList.remove( position );
            notifyItemRemoved( position );
        }
    }

//    private ActivePost getItem(int position) {
//        return postList.get( position );
//    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}

package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.AdViewActivity;
import com.example.myapplication.CategoryRecyclerViewModel.CategoryActivePost;
import com.example.myapplication.R;

import java.util.List;

public class CategoryRecylerViewAdapter extends RecyclerView.Adapter<CategoryRecylerViewAdapter.MyViewHolder>{


    private Context mContext;
    private List<CategoryActivePost> postList;

    //String Tag = "AdPostRecylerViewAdapter";

    public CategoryRecylerViewAdapter(Context mContext, List<CategoryActivePost> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public CategoryRecylerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.catagory_ad_post_recyler_view_item,parent,false);
        final CategoryRecylerViewAdapter.MyViewHolder viewHolder = new CategoryRecylerViewAdapter.MyViewHolder( view );
        viewHolder.recyler_view_layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( mContext,AdViewActivity.class );
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

    @Override
    public void onBindViewHolder(CategoryRecylerViewAdapter.MyViewHolder holder, int position) {


        holder.post_title.setText( postList.get( position ).getPostTitle() );
//        holder.Address.setText(postList.get(position).getAddress());
        holder.Address.setText(postList.get( position ).getDivision()+","+postList.get( position ).getCity()+","+postList.get( position ).getAddress());
        holder.type.setText( postList.get( position ).getTenant());
        holder.post_date.setText( postList.get( position ).getCreatedAt());
        holder.price.setText( postList.get( position ).getRenters());
        // Picasso.get().load(postList.get(position).getMoreImage()).into(holder.iv);

        String Url = "https://propertyrental.againwish.com/";

        CategoryActivePost product = postList.get(position);
        Glide.with(mContext)
                .load(Url+product.getFeaturedImage())
                .into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView post_title,Address, type, price,post_date;
        LinearLayout recyler_view_layout;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super( itemView );

            recyler_view_layout = itemView.findViewById( R.id.recyler_view_layout );


            Address = itemView.findViewById( R.id.address );
            type = itemView.findViewById( R.id.type );
            post_title = itemView.findViewById( R.id.post_title );
            price = itemView.findViewById( R.id.price );
            post_date = itemView.findViewById( R.id.post_date );

            iv = itemView.findViewById(R.id.iv);
        }
    }


    public void updateList(List<CategoryActivePost> postList){
        this.postList=postList;
        notifyDataSetChanged();
    }

}

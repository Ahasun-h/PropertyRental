package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Activity.MyInterestAdView;
import com.example.myapplication.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyInterestAdapter extends RecyclerView.Adapter<MyInterestAdapter.MyViewHolder> {

    private Context mContext;
    private List <com.example.myapplication.MyInterestModel.MyInterest> postList;

    //String Tag = "AdPostRecylerViewAdapter";

    public MyInterestAdapter(Context mContext, List<com.example.myapplication.MyInterestModel.MyInterest> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public MyInterestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.my_interest_item_view,parent,false);
        final MyInterestAdapter.MyViewHolder viewHolder = new MyInterestAdapter.MyViewHolder( view );
        viewHolder.my_interest_view_content.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent= new Intent( mContext, MyInterestAdView.class );
//                intent.putExtra( "ad_id",postList.get( viewHolder.getAdapterPosition()).getPostId());
//                mContext.startActivity(intent);
            }
        } );
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyInterestAdapter.MyViewHolder holder, int position) {

        holder.post_title.setText( postList.get( position ).getPostTitle() );
        holder.category.setText(postList.get(position).getPostCategory());
        holder.rent_date.setText( postList.get( position ).getRentDate());
        holder.price.setText( postList.get( position ).getRenters());
        holder.created_date.setText(postList.get( position ).getCreatedAt());
        holder.address.setText(postList.get( position ).getPostAddress()+","+postList.get( position ).getPostDivision()+","+postList.get( position ).getPostCity() );



    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView post_title,category, rent_date, address,price,created_date;
        LinearLayout my_interest_view_content;

        public MyViewHolder(View itemView) {
            super( itemView );

            my_interest_view_content = itemView.findViewById( R.id.my_interest_view_content );


            category = itemView.findViewById( R.id.category );
            rent_date = itemView.findViewById( R.id.rent_date );
            address = itemView.findViewById( R.id.address );
            post_title = itemView.findViewById( R.id.post_title );
            price = itemView.findViewById( R.id.price );
            created_date = itemView.findViewById( R.id.created_date );

        }
    }


    public void updateList(List<com.example.myapplication.MyInterestModel.MyInterest> postList){
        this.postList=postList;
        notifyDataSetChanged();
    }

}

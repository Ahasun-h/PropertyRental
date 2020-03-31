package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Activity.MyInterestAdView;
import com.example.myapplication.HistoryModel.History;
import com.example.myapplication.MyInterestModel.MyInterest;
import com.example.myapplication.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private Context mContext;
    private List <History> postList;

    //String Tag = "AdPostRecylerViewAdapter";

    public HistoryAdapter(Context mContext, List<History> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.my_rent_history_item,parent,false);
        final HistoryAdapter.MyViewHolder viewHolder = new HistoryAdapter.MyViewHolder( view );
        viewHolder.my_history_view_content.setOnClickListener( new View.OnClickListener() {
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
    public void onBindViewHolder(HistoryAdapter.MyViewHolder holder, int position) {
        holder.deal_date.setText("Deal Date : "+ postList.get( position ).getDealDate() );
        holder.owner_name.setText("Owner Name : "+postList.get(position).getOwnerName());
        holder.amount.setText( "Price : "+postList.get( position ).getPriceAmount());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView deal_date,owner_name, amount;
        LinearLayout my_history_view_content;

        public MyViewHolder(View itemView) {
            super( itemView );

            my_history_view_content = itemView.findViewById( R.id.my_history_view_content );

            deal_date = itemView.findViewById( R.id.deal_date );
            owner_name = itemView.findViewById( R.id.owner_name );
            amount = itemView.findViewById( R.id.amount );
        }
    }


    public void updateList(List<History> postList){
        this.postList=postList;
        notifyDataSetChanged();
    }

}

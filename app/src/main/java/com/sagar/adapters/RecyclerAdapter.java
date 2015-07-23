package com.sagar.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sagar.materialdesigndemo.R;
import com.sagar.models.CardItemModel;

import java.util.List;

/**
 * Created by Sagar on 6/14/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<CardItemModel> cardItems;

    public RecyclerAdapter(List<CardItemModel> cardItems){
        this.cardItems = cardItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView)itemView.findViewById(R.id.card_title);
            this.content = (TextView)itemView.findViewById(R.id.card_content);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(cardItems.get(position).title);
        holder.content.setText(cardItems.get(position).content);
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }
}

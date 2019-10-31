package com.example.worldism;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable {

    ArrayList<Data> arrayList;
    ArrayList<Data> arrayFullList;
    Context context;
    ClickInterface clickInterface;

    public RecyclerAdapter(ArrayList<Data> arrayList, Context context, ClickInterface clickInterface) {
        this.arrayList = arrayList;
        this.context = context;
        this.clickInterface = clickInterface;
        arrayFullList = new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.cname.setText(arrayList.get(position).getCname());
        //Glide.with(context).load(arrayList.get(position).getCimage()).apply(RequestOptions.centerCropTransform()).into(holder.cimage);//
        holder.lnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }



    private Filter exampleFilter = new Filter() {
        @Override
        public FilterResults performFiltering(CharSequence constraint) {
            ArrayList <Data> filteredList = new ArrayList<>();

            if (constraint==null||constraint.length()==0)
            {
                filteredList.addAll(arrayFullList);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Data item: arrayFullList)
                {
                         if (item.getCname().toLowerCase().contains(filterPattern))
                         {
                             filteredList.add(item);
                         }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filteredList;

            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            arrayList.clear();
            arrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cname;
        //ImageView cimage;
        LinearLayout lnMain;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cname = itemView.findViewById(R.id.text);
           // cimage = itemView.findViewById(R.id.image);//
            lnMain = itemView.findViewById(R.id.ln_main);
        }

    }
}

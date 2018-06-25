package com.kodonho.android.firebase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{
    List<Memo> data = new ArrayList<>();
    public void setDataAndRefresh(List<Memo> data){
        this.data = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Memo memo = data.get(position);
        holder.textView.setText(memo.memo);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView textView;
        Memo memo;
        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textMemo);
        }
    }
}

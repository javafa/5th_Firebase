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

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{
    List<User> data = new ArrayList<>();
    public void setDataAndRefresh(List<User> data){
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
        User user = data.get(position);
        holder.id.setText(user.id);
        holder.name.setText(user.username);
        holder.email.setText(user.email);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView id,name,email;
        public Holder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textId);
            name = itemView.findViewById(R.id.textName);
            email = itemView.findViewById(R.id.textEmail);
        }
    }
}

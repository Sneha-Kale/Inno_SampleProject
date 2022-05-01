package com.example.inno_sampleproject.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inno_sampleproject.Model.Datum;
import com.example.inno_sampleproject.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterPageData extends RecyclerView.Adapter<AdapterPageData.MyViewHolder> {
    private List<Datum> data;
    private Context mContext;

    public AdapterPageData(Context Context, List<Datum> dataList) {
        this.mContext = Context;
        this.data = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_data, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        try{
            holder.textUserName.setText(""+data.get(position).getFirstName()+" "+data.get(position).getLastName());
            holder.textUserEmail.setText(""+data.get(position).getEmail());

            Glide.with(holder.imageView).load(data.get(position).getAvatar()).placeholder(R.drawable.placeholder).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

        }catch (NullPointerException e){
            Log.e("print_adapter",""+e);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void updateUserList(final List<Datum> userArrayList) {
        this.data.clear();
        this.data = userArrayList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textUserName, textUserEmail;
        CircleImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textUserName = itemView.findViewById(R.id.textUserName);
            textUserEmail = itemView.findViewById(R.id.textUserEmail);
            imageView = itemView.findViewById(R.id.image_profile);
        }
    }
}

package com.example.appchat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appchat.MessageActivity;
import com.example.appchat.Model.User;
import com.example.appchat.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private ArrayList<User> mUser;

    public UserAdapter(Context context, ArrayList<User> mUser) {
        this.context = context;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_users_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = mUser.get(position);
        holder.tvUser.setText(user.getUsername());
        if (user.getImageURL().equals("default")){
            holder.circularImageView.setImageResource(R.mipmap.ic_launcher);
        }else {
            Glide.with(context).load(user.getImageURL()).into(holder.circularImageView);
        }

        holder.circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircularImageView circularImageView;
        TextView tvUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circularImageView = itemView.findViewById(R.id.profile_image_fragment);
            tvUser = itemView.findViewById(R.id.username_fragment);
        }
    }


}

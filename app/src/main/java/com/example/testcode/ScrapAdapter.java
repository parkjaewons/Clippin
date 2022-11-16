package com.example.testcode;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScrapAdapter extends FirebaseRecyclerAdapter<Scrap,ScrapAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ScrapAdapter(@NonNull FirebaseRecyclerOptions<Scrap> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Scrap model) {
        holder.title.setText(model.gettitle());
        holder.maintext.setText(model.getText());
        holder.url.setText(model.getUrl());
        holder.keyword.setText(model.getKeyword());

        Glide.with(holder.img.getContext())
                .load(model.getImage_url())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_text_light_normal)
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),Scrap_info.class);
                intent.putExtra("title",model.gettitle());
                intent.putExtra("maintext",model.getText());
                intent.putExtra("url",model.getUrl());
                intent.putExtra("keyword",model.getKeyword());
                intent.putExtra("image",model.getImage_url());

                v.getContext().startActivities(new Intent[]{intent});
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scrap_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView title, maintext, url, keyword;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            title = (TextView) itemView.findViewById(R.id.titletext);
            maintext = (TextView) itemView.findViewById(R.id.maintext);
            url = (TextView) itemView.findViewById(R.id.url);
            keyword= (TextView) itemView.findViewById(R.id.keyword);
        }
    }
}

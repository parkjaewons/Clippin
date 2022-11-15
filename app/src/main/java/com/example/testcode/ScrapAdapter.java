package com.example.testcode;


import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scrap_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView title, maintext, url;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            title = (TextView) itemView.findViewById(R.id.titletext);
            maintext = (TextView) itemView.findViewById(R.id.maintext);
            url = (TextView) itemView.findViewById(R.id.url);

            maintext.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}

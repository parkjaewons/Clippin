package com.example.testcode;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

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
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull Scrap model) {
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

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.popup))
                        .setExpanded(true,1200)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText title = view.findViewById(R.id.txttitle);
                EditText maintext = view.findViewById(R.id.txttext);
                EditText keyword = view.findViewById(R.id.txtkeyword);
                EditText url = view.findViewById(R.id.txtURL);

                Button btnupdate = view.findViewById(R.id.btnupdate);

                title.setText(model.gettitle());
                maintext.setText(model.getText());
                keyword.setText(model.getKeyword());
                url.setText(model.getUrl());

                dialogPlus.show();

                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("title",title.getText().toString());
                        map.put("text",maintext.getText().toString());
                        map.put("keyword",keyword.getText().toString());
                        map.put("url",url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("news")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.title.getContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.title.getContext(), "수정 실패", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.title.getContext());
                builder.setTitle("삭제하시겠습니까?");
                builder.setMessage("스크랩 데이터가 전부 삭제됩니다.");

                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("news")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.title.getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

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

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            title = (TextView) itemView.findViewById(R.id.titletext);
            maintext = (TextView) itemView.findViewById(R.id.maintext);
            url = (TextView) itemView.findViewById(R.id.url);
            keyword= (TextView) itemView.findViewById(R.id.keyword);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }
}

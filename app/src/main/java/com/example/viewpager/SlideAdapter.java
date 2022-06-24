package com.example.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.Model.List;
import java.util.ArrayList;
public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.ViewHolder>{
    ArrayList<List> list;
    ViewPager2 viewPager2;
    Context context;
    String string = "https://cdn-icons-png.flaticon.com/128/3048/3048150.png";

    public SlideAdapter(ArrayList<List> list, ViewPager2 viewPager2,Context context) {
        this.list = list;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        if (position == list.siz.e() - 2){
//            viewPager2.post(runnable);
//        }
        Glide.with(context).load(list.get(position).image).into(holder.imageVocab);
        holder.trans.setText("[ "+list.get(position).trascription+" ]");
        holder.engWord.setText(list.get(position).wordEng);
        holder.uzWord.setText(list.get(position).wordUz);
        holder.ruWord.setText(list.get(position).wordRu);
        holder.playBtn.setOnClickListener(view -> {
            MediaPlayer player = new MediaPlayer();
            try {
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource("https://www.oxfordlearnersdictionaries.com/media/english/uk_pron/f/fat/fathe/father__gb_2.mp3");
                player.prepare();
                player.start();
            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(context, "Could not play audio", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageVocab,playBtn;
        TextView trans,engWord,uzWord,ruWord;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageVocab = itemView.findViewById(R.id.vocabImage);
            playBtn = itemView.findViewById(R.id.playBtn);
            trans = itemView.findViewById(R.id.transText);
            engWord = itemView.findViewById(R.id.engWordText);
            uzWord = itemView.findViewById(R.id.uzWordText);
            ruWord = itemView.findViewById(R.id.ruWordText);

        }

    }



//        private final Runnable runnable = new Runnable() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void run() {
//                list.addAll(list);
//                notifyDataSetChanged();
//            }};
}

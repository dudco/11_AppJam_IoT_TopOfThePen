package com.example.youngchae.topofthe_pen.fragment1;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.youngchae.topofthe_pen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youngchae on 2016-07-23.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    List<RecyclerItem> items = new ArrayList<>();

    public RecyclerAdapter(List<RecyclerItem> items) {
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecyclerItem item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.bookname.setText(item.getBookname());
        holder.time.setText(": "+item.getTime()+"분");
        holder.goal.setText("목표시간 : "+item.getGoal());
        holder.ing.setText("성취시간 : "+item.getIng());
        holder.progressBar.setMax(item.getGoal());
        holder.progressBar.setProgress(item.getIng());

        if(holder.progressBar.getProgress() == item.getGoal()){
            holder.linearLayout.setBackgroundResource(R.drawable.gray_box);
            holder.goalimage.setImageResource(R.drawable.progress_complated);
            holder.progressBar.setBackgroundResource(R.drawable.progress_complated);
            Drawable drawable = holder.itemView.getResources().getDrawable(R.drawable.progress_custom_complated);
            holder.progressBar.setProgressDrawable(drawable);
            holder.goal.setTextColor(Color.parseColor("#757575"));
            holder.ing.setTextColor(Color.parseColor("#757575"));
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, bookname,time, goal,ing;
        ProgressBar progressBar;
        ImageView goalimage;
        LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            bookname = (TextView) itemView.findViewById(R.id.bookname);
            time = (TextView) itemView.findViewById(R.id.time_item);
            goal = (TextView) itemView.findViewById(R.id.progress_text);
            ing = (TextView) itemView.findViewById(R.id.progress_text_complating);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            goalimage = (ImageView) itemView.findViewById(R.id.goal_image);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.recycler_container);
        }
    }
}

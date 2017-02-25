package util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;

import pojo.Task;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context context;
    private Task task;
    private final String uri = "http://mootask.com/taskcontroller/showtaskimagethumbnail?id=";
    public HomeAdapter(Context context)
    {
        this.context = context;
        if (task == null) {
            task = new Task();
        }
    }

    public void setData(Task task)
    {
        if (task != null) {
            this.task = task;
        }
    }
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.dataitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position) {
        holder.dataMessage.setText(Html.fromHtml(task.images.get(position).caption));
        holder.dataText.setText(task.title);
        Glide.with(context)
                .load(uri+task.images.get(position).entityId)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(task.images == null)
        {
            return 0;
        }
        return task.images.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView dataText,dataMessage;
        ImageView imageView,imageLogo;
        public MyViewHolder(View view)
        {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            imageLogo = (ImageView) view.findViewById(R.id.imageLogo);
            dataText = (TextView) view.findViewById(R.id.dataText);
            dataMessage = (TextView) view.findViewById(R.id.dataMessage);
        }
    }
}

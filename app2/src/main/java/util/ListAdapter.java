package util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import pojo.People;

/**
 * Created by Administrator on 2017/2/25.
 */

public class ListAdapter extends BaseAdapter {

    private List<People> peopleList;
    private Context context;

    public ListAdapter(Context context)
    {
        this.context = context;
        if (peopleList == null)
            peopleList = new ArrayList<>();
    }

    public void  setPeopleList(List<People> peopleList)
    {
        if (peopleList != null)
            this.peopleList = peopleList;
    }
    @Override
    public int getCount() {
        return peopleList.size();
    }

    @Override
    public Object getItem(int position) {
        return peopleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder;
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.listitem,null);
            holder = new Holder();
            holder.iv = (ImageView) view.findViewById(R.id.iv);
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }
        holder.iv.setBackgroundResource(peopleList.get(position).head);
        return view;
    }

    class Holder
    {
        ImageView iv;
    }
}

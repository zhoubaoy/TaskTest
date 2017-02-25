package util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.People;

/**
 * Created by Administrator on 2017/2/24.
 */

public  class  ExpandableAdapter extends BaseExpandableListAdapter{

    public String[] group;
    public Map<String,List<People>> child;
    public List<People> checked;
    private Context context;
    private boolean cklickgoup;
    public ExpandableAdapter(Context context) {

        this.context = context;
        checked = new ArrayList<>();
        if (group == null)
            group = new String[]{};
        if (child == null)
            child = new HashMap<String, List<People>>() ;
    }

    public void setGroups(String[] group)
    {
        if (group != null)
            this.group = group;
    }

    public void setChild(Map<String,List<People>> child)
    {
        if (child != null)
           this.child = child;
    }

    public void setCklickgoup(boolean cklickgoup)
    {
        this.cklickgoup = cklickgoup;
    }

    //获取与给定的组相关的数据，得到数组groups中元素的数据
    public Object getGroup(int groupPosition) {
        return group[groupPosition];
    }

    //获取与孩子在给定的组相关的数据,得到数组children中元素的数据
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(group[groupPosition]).get(childPosition);
    }

    //获取的群体数量，得到groups里元素的个数
    public int getGroupCount() {
        return group.length;
    }

    //取得指定组中的children个数，就是groups中每一个条目中的个数
    public int getChildrenCount(int groupPosition) {
        return child.get(group[groupPosition]).size();
    }

    //获取组在给定的位置编号，即groups中元素的ID
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取在给定的组的children的ID，也就是children中元素的ID
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //获取一个视图显示给定组，存放groups
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        View view = convertView;
        genericView textView;
        if (view == null)
        {
            view = View.inflate(context, R.layout.listitem_group, null);
            textView = new genericView();
            view.setTag(textView);
        }else
        {
            textView = (genericView)view.getTag();
        }
        textView.group = (TextView) view.findViewById(R.id.group);
        textView.group.setText(group[groupPosition].toString());
        return view;
    }

    //获取一个视图显示在给定的组 的儿童的数据，就是存放children
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        View view = convertView;
        childView childview;
        if (view == null)
        {
            view = View.inflate(context, R.layout.listitem_child, null);
            childview = new childView();
            view.setTag(childview);
        }else
        {
            childview = (childView)view.getTag();
        }
        childview.imageView = (ImageView) view.findViewById(R.id.iv);
        childview.name = (TextView) view.findViewById(R.id.name);
        childview.checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        childview.imageView.setBackgroundResource(child.get(group[groupPosition]).get(childPosition).head);
        childview.name.setText(child.get(group[groupPosition]).get(childPosition).name);
        childview.checkBox.setChecked(child.get(group[groupPosition]).get(childPosition).isChecked);
        childview.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = child.get(group[groupPosition]).get(childPosition).isChecked;
                String name = child.get(group[groupPosition]).get(childPosition).name;
                if (!isChecked)
                {
                    child.get(group[groupPosition]).get(childPosition).isChecked = true;
                    checked.add(child.get(group[groupPosition]).get(childPosition));

                }else {
                    child.get(group[groupPosition]).get(childPosition).isChecked = false;
                    for (int i =0;i<checked.size();i++)
                    {
                        if(checked.get(i).name.equals(name)){
                            checked.remove(i);
                        }
                    }

                }
                CheckListener.Checked(checked);
            }
        });
        return view;
    }

    //孩子在指定的位置是可选的，即：children中的元素是可点击的
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //表示孩子是否和组ID是跨基础数据的更改稳定
    public boolean hasStableIds() {
        return true;
    }


    class genericView
    {
        public TextView group;
    }

    class childView
    {
        public CheckBox checkBox;
        public ImageView imageView;
        public TextView name;
        public TextView other;
    }

    public interface CheckListener
    {
        void Checked(List<People> checked);
    }
    CheckListener CheckListener;
    public void getChecked(CheckListener CheckListener)
    {
        this.CheckListener = CheckListener;
    }
}

package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.People;
import util.ExpandableAdapter;
import util.HorizontalListView;
import util.ListAdapter;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView ev;
    private String[] group;
    private Map<String,List<People>> child;
    private ExpandableAdapter ExpandableAdapter;
    private List<People> peopleList;
    private HorizontalListView horizontalListView;
    private ListAdapter listAdapter;
    private LinearLayout ll;
    private EditText et_search;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select Contacts");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_menu_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
        initData();
        initView();
    }

    private void initData()
    {
        group = new String[]{"A","B","D","K","M","N"};
        child = new HashMap<>();
        List<People> listA = new ArrayList<>();
        People people1 = new People(R.mipmap.a,"Affgsgsg");
        listA.add(people1);
        child.put("A",listA);

        List<People> listB = new ArrayList<>();
        People people2 = new People(R.mipmap.b,"Bljujjj");
        listB.add(people2);
        child.put("B",listB);

        List<People> listD = new ArrayList<>();
        People people3 = new People(R.mipmap.d,"Dffgsgsg");
        People people4 = new People(R.mipmap.d2,"D255866");
        listD.add(people3);
        listD.add(people4);
        child.put("D",listD);

        List<People> listK = new ArrayList<>();
        People people5 = new People(R.mipmap.k,"Kffgsgsg");
        listK.add(people5);
        child.put("K",listK);

        List<People> listM = new ArrayList<>();
        People people6 = new People(R.mipmap.m,"Mffgsgsg");
        listM.add(people6);
        child.put("M",listM);

        List<People> listN = new ArrayList<>();
        People people7 = new People(R.mipmap.n,"Nffgsgsg");
        People people8 = new People(R.mipmap.n2,"Nffgsgsg1");
        People people9 = new People(R.mipmap.n3,"Nffgsgsg2");
        People people10 = new People(R.mipmap.n5,"Nffgsgsg3");
        listN.add(people7);
        listN.add(people8);
        listN.add(people9);
        listN.add(people10);
        child.put("N",listN);

    }

    private void initView()
    {
        textView = (TextView) findViewById(R.id.text_done);
        ll = (LinearLayout) findViewById(R.id.ll);
        et_search = (EditText) findViewById(R.id.et_search);
        horizontalListView = (HorizontalListView) findViewById(R.id.hlist);
        ev = (ExpandableListView)findViewById(R.id.expandableListView);
        ExpandableAdapter = new ExpandableAdapter(this);
        ExpandableAdapter.setGroups(group);
        ExpandableAdapter.setChild(child);
        ev.setAdapter(ExpandableAdapter);
        listAdapter = new ListAdapter(this);
        horizontalListView.setAdapter(listAdapter);

        ExpandableAdapter.getChecked(new ExpandableAdapter.CheckListener() {
            @Override
            public void Checked(List<People> checked) {
                listAdapter.setPeopleList(checked);
                listAdapter.notifyDataSetChanged();
                int alllength = ll.getWidth();
                int allHight = ll.getHeight();
                int listlength = alllength*2/3;
                int count = listlength/(allHight-10);
                if (count>=checked.size())
                {
                    LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    sp_params.width = alllength - (allHight-10)*checked.size();
                    et_search.setLayoutParams(sp_params);
                    LinearLayout.LayoutParams sp_params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    sp_params2.width = (allHight-10)*checked.size();
                    horizontalListView.setLayoutParams(sp_params2);
                }else
                {
                    LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    sp_params.width = alllength-listlength;
                    et_search.setLayoutParams(sp_params);
                    LinearLayout.LayoutParams sp_params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    sp_params2.width = listlength;
                    horizontalListView.setLayoutParams(sp_params2);
                }
                peopleList = checked;
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("people",(Serializable) peopleList);
                Intent mIntent = new Intent(MainActivity.this,HomeActivity.class);
                mIntent.putExtras(bundle);
                MainActivity.this.setResult(1,mIntent);
                MainActivity.this.finish();
            }
        });
    }
}

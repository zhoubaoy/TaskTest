package com.example.administrator.myapplication;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pojo.Task;
import util.HomeAdapter;

/**
 * Created by Administrator on 2017/2/24.
 */

public class DataFragment extends Fragment {
    private RecyclerView datas;
    private HomeAdapter homeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.datafragment, container, false);

        datas = (RecyclerView) view.findViewById(R.id.rlv_dataFragment);
        datas.setLayoutManager(new LinearLayoutManager(getActivity()));
        datas.setAdapter(homeAdapter);
        return view;
    }

    public void getData(Task task)
    {
        homeAdapter.setData(task);
        homeAdapter.notifyDataSetChanged();
    }

}

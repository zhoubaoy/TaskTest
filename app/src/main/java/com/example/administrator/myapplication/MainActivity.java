package com.example.administrator.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import pojo.Task;
import pojo.Tasks;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import util.StatusBarCompat;
import util.TextData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_home,tv_job,tv_me;
    private DataFragment dataFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarCompat.compat(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        initView();
        getData();
    }

    private void initView()
    {
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_job = (TextView) findViewById(R.id.tv_job);
        tv_me = (TextView) findViewById(R.id.tv_me);
        tv_home.setOnClickListener(this);
        tv_job.setOnClickListener(this);
        tv_me.setOnClickListener(this);
        tv_home.setTextColor(Color.parseColor("#1E90FF"));

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        dataFragment = new DataFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("task",list.get(0));
//        dataFragment.setArguments(bundle);
        transaction.replace(R.id.data_framelayout, dataFragment);
        transaction.commit();
    }

    public void getData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mootask.com/api/taskcontroller/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TextData text = retrofit.create(TextData.class);
        Call<List<Task>> obj = text.getData();
        obj.enqueue(new Callback<List<Task>>(){
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response){
                List<Task> list = response.body();
                dataFragment.getData(list.get(0));
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t){
                Log.d("task",t.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_home:
                tv_home.setTextColor(Color.parseColor("#1E90FF"));
                tv_job.setTextColor(Color.parseColor("#303030"));
                tv_me.setTextColor(Color.parseColor("#303030"));
                break;
            case R.id.tv_job:
                tv_home.setTextColor(Color.parseColor("#303030"));
                tv_job.setTextColor(Color.parseColor("#1E90FF"));
                tv_me.setTextColor(Color.parseColor("#303030"));
                break;
            case R.id.tv_me:
                tv_home.setTextColor(Color.parseColor("#303030"));
                tv_job.setTextColor(Color.parseColor("#303030"));
                tv_me.setTextColor(Color.parseColor("#1E90FF"));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}

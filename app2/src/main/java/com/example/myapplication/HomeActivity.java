package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pojo.People;

public class HomeActivity extends AppCompatActivity {

    private TextView tv;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tv = (TextView) findViewById(R.id.tv);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1) {
                String string = "";
                List<People> result=(ArrayList<People>)data.getSerializableExtra("people");
            if (result == null)
            {
                return;
            }
            for (People people : result)
            {
                string += people.name+" ";

            }
            tv.setText(string);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

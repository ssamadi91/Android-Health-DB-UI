package com.example.shamool.HeartRate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class UserDetailList extends AppCompatActivity implements Listener {

    RecyclerView recyclerView;
    HeartRateDbHelper dbHelper;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_list);

        dbHelper =  new HeartRateDbHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
        adapter = new ListAdapter(this, dbHelper.getAllUser());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void nameToChnge(String name) {
        dbHelper.deleteRow(name);

        adapter = new ListAdapter(this, dbHelper.getAllUser());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

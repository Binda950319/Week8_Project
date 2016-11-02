package com.wbh.week8_project.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wbh.week8_project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCollectActivity extends AppCompatActivity {
    @BindView(R.id.detail_toolBar)
    Toolbar detail_toolBar;
    @BindView(R.id.detail_recycler)
    RecyclerView detail_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initToolbar();

    }

    private void initToolbar() {
        detail_toolBar.setTitle("我的收藏");
        setSupportActionBar(detail_toolBar);
        ActionBar action = getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

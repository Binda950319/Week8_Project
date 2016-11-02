package com.wbh.week8_project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;

import com.wbh.week8_project.R;
import com.wbh.week8_project.adapter.DetailAdapter;
import com.wbh.week8_project.apiservice.GetDetailData;
import com.wbh.week8_project.javabean.DetailListBean;
import com.wbh.week8_project.mycallback.OnItemClickLinstener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {
    //    http://api-v2.mall.hichao.com/search/skus?query=连衣裙%20%20&sort=all&ga=%252Fsearch%252Fskus&flag=&cat=&asc=1
    @BindView(R.id.detail_toolBar)
    Toolbar detail_toolBar;
    @BindView(R.id.detail_recycler)
    RecyclerView detail_recycler;
    private String baseUrl = "http://api-v2.mall.hichao.com/";
    private Intent intent;
    private ArrayList<DetailListBean.DataBean.ItemsBean> itemsList;
    private DetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initToolbar();
        initRecycler();
    }


    private void initToolbar() {
        detail_toolBar.setTitle("列表详情");
        setSupportActionBar(detail_toolBar);
       ActionBar action = getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(true);
    }

    private void initRecycler() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        detail_recycler.setLayoutManager(manager);
        intent = getIntent();
        String word = intent.getStringExtra("word");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDetailData getDetailData = retrofit.create(GetDetailData.class);
        Call<DetailListBean> call = getDetailData.getDetailListData(word);
        call.enqueue(new Callback<DetailListBean>() {
            @Override
            public void onResponse(Call<DetailListBean> call, Response<DetailListBean> response) {
                if (response != null) {
                    itemsList = (ArrayList<DetailListBean.DataBean.ItemsBean>) response.body().getData().getItems();
//                Log.e("Bing", "******onResponse: ******" + itemsList.size());
                    adapter = new DetailAdapter(DetailActivity.this);
                    adapter.setItemsList(itemsList);
                    detail_recycler.setAdapter(adapter);
                    initClick();
                }
            }
            @Override
            public void onFailure(Call<DetailListBean> call, Throwable t) {
            }
        });
    }

    //点击事件
    private void initClick() {
        adapter.setOnItemClickLinstener(new OnItemClickLinstener() {
            @Override
            public void setOnMyItemClickLinstener(int position) {
                intent = new Intent(DetailActivity.this, Detail2Activity.class);
                String sourceId = itemsList.get(position).getComponent().getAction().getSourceId();
//                Log.e("Bing", "******sourceId: ******"+sourceId);
                intent.putExtra("sourceId", sourceId);
                DetailListBean.DataBean.ItemsBean.ComponentBean component = itemsList.get(position).getComponent();
                intent.putExtra("component", component);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

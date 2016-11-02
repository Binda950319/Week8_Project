package com.wbh.week8_project.myfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wbh.week8_project.R;
import com.wbh.week8_project.activity.DetailActivity;
import com.wbh.week8_project.adapter.HomeAdapter;
import com.wbh.week8_project.adapter.HomeIntroAdapter;
import com.wbh.week8_project.apiservice.GetHomeData;
import com.wbh.week8_project.javabean.HomeBean;
import com.wbh.week8_project.mycallback.OnItemClickLinstener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.rightRecycler)
    RecyclerView rightRecycler;
    @BindView(R.id.leftListView)
    ListView leftListView;
    //左边的列表
    private List<HomeBean.DataBean.ItemsBean> itemsList;
    //右边的数据列表
    private List<HomeBean.DataBean.ItemsBean.ComponentBean.ItemsBean2> items2List;
    //http://api-v2.mall.hichao.com/category/list?ga=%2Fcategory%2Flist
    //基本地址
    String baseUrl = "http://api-v2.mall.hichao.com/";
    private int prePosition;
    private HomeIntroAdapter rightAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initLeftRecycler();
        initLeftClick(itemsList);
        initRightRecycler();
        return view;
    }

    private void initLeftRecycler() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetHomeData getHomeData = retrofit.create(GetHomeData.class);
        Call<HomeBean> call = getHomeData.getHomeDatas();
        call.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                if (response != null) {
                    itemsList = response.body().getData().getItems();
//                Log.e("Bing", "******onResponse: ******" + itemsList.size());
                    HomeAdapter adapter = new HomeAdapter(itemsList, getActivity());
                    leftListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {
                Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initLeftClick(final List<HomeBean.DataBean.ItemsBean> itemsList) {
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftListView.getChildAt(position).setBackgroundResource(R.color.colorToolbar);
                leftListView.getChildAt(prePosition).setBackgroundResource(R.color.colorLeftDefault);
                prePosition = position;
                updata(position);
            }
        });
    }

    private void initRightRecycler() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        rightRecycler.setLayoutManager(manager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetHomeData getHomeData = retrofit.create(GetHomeData.class);
        Call<HomeBean> call = getHomeData.getHomeDatas();
        call.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                if (response != null) {
                    //右边的RecycleView更新数据
                    updata(0);
                }
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {
                Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initRightClick() {

        rightAdapter.setOnMyItemClickLinstener(new OnItemClickLinstener() {
            @Override
            public void setOnMyItemClickLinstener(int position) {
                Log.e("Bing", "******: ******" + position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                String word = items2List.get(position).getComponent().getWord();
                intent.putExtra("word", word);
                startActivity(intent);
            }
        });
    }

    //右边的RecycleView更新数据
    public void updata(int tag) {
        items2List = itemsList.get(tag).getComponent().getItems();
//        Log.e("Bing", "******右边: ******"+items2List.size());
        rightAdapter = new HomeIntroAdapter(getActivity());
        rightAdapter.setItems2List(items2List);
        rightRecycler.setAdapter(rightAdapter);
        initRightClick();
    }

}



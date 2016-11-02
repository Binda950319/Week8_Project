package com.wbh.week8_project.apiservice;

import com.wbh.week8_project.javabean.DetailListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/10/20.
 */
public interface GetDetailData {

    @GET(value = "search/skus")
    Call<DetailListBean> getDetailListData(@Query("query") String word);

}

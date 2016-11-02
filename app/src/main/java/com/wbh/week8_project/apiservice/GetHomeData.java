package com.wbh.week8_project.apiservice;

import com.wbh.week8_project.javabean.HomeBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface GetHomeData {
    @GET(value = "category/list?ga=%2Fcategory%2Flist")
    Call<HomeBean> getHomeDatas();
}

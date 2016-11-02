package com.wbh.week8_project.activity;
//android.support.v7.app包下的ActionBar

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.wbh.week8_project.R;
import com.wbh.week8_project.javabean.Component;
import com.wbh.week8_project.javabean.DetailListBean;
import com.wbh.week8_project.mysql.MyDBManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Detail2Activity extends AppCompatActivity {
    private String path = "http://m.hichao.com/lib/interface.php?m=goodsdetail&sid=";
    @BindView(R.id.detail2_webView)
    WebView detail2_webView;
    @BindView(R.id.detail2_toolBar)
    Toolbar detail2_toolBar;
    @BindView(R.id.detail2_save)
    Button detail2_save;
    @BindView(R.id.detail2_add)
    Button detail2_add;
    @BindView(R.id.detail2_buy)
    Button detail2_buy;
    private Intent intent;
    private String sourceId;
    private DetailListBean.DataBean.ItemsBean.ComponentBean component;
    private boolean isAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        ButterKnife.bind(this);
        intent = getIntent();
        sourceId = intent.getStringExtra("sourceId");
        component = (DetailListBean.DataBean.ItemsBean.ComponentBean) intent.getSerializableExtra("component");
        String description = component.getDescription();
//        Log.e("Bing", "******Description: ******" + description);
        initToolbar();
        initWebView();
    }

    private void initToolbar() {
        detail2_toolBar.setTitle("详情");
        setSupportActionBar(detail2_toolBar);
        ActionBar action = getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(true);
    }

    //处理WebView
    private void initWebView() {
        detail2_webView.loadUrl(path + sourceId);
        WebSettings settings = detail2_webView.getSettings();
        //支持js 语言
        settings.setJavaScriptEnabled(true);
        // 在当前WebView 内部展示加载的 Url
        detail2_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }

    @OnClick(R.id.detail2_save)
    public void save(View view) {
        String description = component.getDescription();
        String picUrl = component.getPicUrl();
        String price = component.getPrice();
        String origin_price = component.getOrigin_price();
        long id = Long.parseLong(component.getId());
        Component component = new Component(id, picUrl, price, origin_price, description);
//        Log.e("Bing", "******Des: ******" + description);

        if (isAdded) {
            isAdded = false;
            MyDBManager.getInstance(this).deleteData(component);
            detail2_save.setText("加入收藏");
            Toast.makeText(Detail2Activity.this, "取消收藏", Toast.LENGTH_SHORT).show();
        } else {
            isAdded = true;
            MyDBManager.getInstance(this).insertData(component);
            detail2_save.setText("取消收藏");
            Toast.makeText(Detail2Activity.this, "收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.detail2_add)
    public void add(View view) {

    }

    @OnClick(R.id.detail2_buy)
    public void buy(View view) {

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

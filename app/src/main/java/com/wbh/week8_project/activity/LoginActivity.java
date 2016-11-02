package com.wbh.week8_project.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wbh.week8_project.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity {


    private SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // TODO 数据库存储
        share = getSharedPreferences("user", MODE_PRIVATE);

        ShareSDK.initSDK(this);
    }

    public void qqClick(View view) {
        loginPlatfom(QQ.NAME);


    }

    public void weChatClick(View view) {
        loginPlatfom(Wechat.NAME);
    }

    public void weiBoClick(View view) {
        loginPlatfom(SinaWeibo.NAME);
    }

    public void tencentWeiboClick(View view) {
        loginPlatfom(TencentWeibo.NAME);
    }

    private void loginPlatfom(String name) {
        Platform platform = ShareSDK.getPlatform(this, name);
        platform.removeAccount();
        platform.showUser(null);

        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Looper.prepare();
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                String userName = platform.getDb().getUserName();
                String userIcon = platform.getDb().getUserIcon();
                String userId = platform.getDb().getUserId();
                Log.e("Bing", "******用户ID: ******" + userId);
                Log.e("Bing", "******用户名: ******" + userName);
                Log.e("Bing", "******用户密码: ******" + userIcon);
                Intent intent = new Intent();
                intent.putExtra("userName", userName);
                intent.putExtra("userIcon", userIcon);
                setResult(200, intent);
                finish();
                Looper.loop();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                Log.e("Bing", "******登录失败: ******" + throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(LoginActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
                Log.e("Bing", "******取消登录: ******");
            }
        });

    }
}

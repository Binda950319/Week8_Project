package com.wbh.week8_project.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wbh.week8_project.R;
import com.wbh.week8_project.myfragment.HomeFragment;
import com.wbh.week8_project.myfragment.SaveFragment;
import com.wbh.week8_project.myfragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolBar;
    //设置ToolBar的标题
    private CharSequence title;
    private CircleImageView headView_Image;
    private TextView login_User;
    private View view;
    private FragmentTransaction transaction;
    private Fragment preFragment;
    private HomeFragment homeFragment;
    private SaveFragment saveFragment;
    private ShopFragment shopFragment;
    private List<Fragment> listFragment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //初始化Toolbar, 并操作Toolbar
        initToolbar();
        initFragment();
        //NavigationView的点击事件
        initClick();

    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        saveFragment = new SaveFragment();
        shopFragment = new ShopFragment();
        listFragment.add(homeFragment);
        listFragment.add(saveFragment);
        listFragment.add(shopFragment);
        getSupportFragmentManager().beginTransaction().add(R.id.content_layout, listFragment.get(0)).commit();
        preFragment=homeFragment;
    }

    //初始化Toolbar, 并操作Toolbar
    private void initToolbar() {
        view = NavigationView.inflate(this, R.layout.headview_layout, null);
        view = navigationView.getHeaderView(0);
        headView_Image = (CircleImageView) view.findViewById(R.id.headView_Image);
        login_User = (TextView) view.findViewById(R.id.login_User);
        toolBar = (Toolbar) findViewById(R.id.toolBar);

        toolBar.setTitle("测试程序");
        setSupportActionBar(toolBar);
        ActionBar action = getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(true);
        //抽屉监听器
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        //为DrawerLayout添加监听器
        drawerLayout.addDrawerListener(toggle);
        //同步状态
        toggle.syncState();
    }

    //NavigationView的点击事件
    private void initClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(Gravity.LEFT);
                title = item.getTitle();
                toolBar.setTitle(title);
                transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.exit:
                        login_User.setText("未登录");
                        headView_Image.setImageResource(R.mipmap.default_usericon);
                        toolBar.setTitle("首页");
                        navigationView.setCheckedItem(R.id.first);
                        break;
                    case R.id.first:
                        if (homeFragment.isAdded()) {
                            transaction.show(homeFragment).hide(preFragment).commit();
                        } else{
                            transaction.add(R.id.content_layout, homeFragment).hide(preFragment).commit();
                        }
                        preFragment = homeFragment;
                        break;
                    case R.id.save:
                        toolBar.setTitle("我的收藏");
                        if (saveFragment.isAdded()) {
                            transaction.show(saveFragment).hide(preFragment).commit();
                        } else{
                            transaction.add(R.id.content_layout, saveFragment).hide(preFragment).commit();
                        }
                        preFragment = saveFragment;
                        break;
                    case R.id.shop:
                        if (shopFragment.isAdded()) {
                            transaction.show(shopFragment).hide(preFragment).commit();
                        } else{
                            transaction.add(R.id.content_layout, shopFragment).hide(preFragment).commit();
                        }
                        preFragment = shopFragment;
                        break;
                }
                return true;
            }
        });

        //
        headView_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 200) {
                if (data != null) {
                    login_User.setText(data.getStringExtra("userName"));
                    String userIcon = data.getStringExtra("userIcon");
                    Picasso.with(this).load(userIcon)
                            .config(Bitmap.Config.RGB_565)
                            .into(headView_Image);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //菜单添加图标有效
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

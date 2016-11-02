package com.wbh.week8_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wbh.week8_project.R;
import com.wbh.week8_project.javabean.HomeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/19.
 */
public class HomeAdapter extends BaseAdapter {
    private List<HomeBean.DataBean.ItemsBean> itemsList;
    private Context context;
    private LayoutInflater inflater;
    private int position;

    public HomeAdapter(List<HomeBean.DataBean.ItemsBean> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.left_item_layout, parent, false);
            holder = new ViewHolder();
            if (position == 0) {
                //默认选中第一条目
                convertView.setBackgroundResource(R.color.colorToolbar);
            }
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(itemsList.get(position).getComponent().getTitle());
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.intro_left_tv)
        TextView textView;
    }
}

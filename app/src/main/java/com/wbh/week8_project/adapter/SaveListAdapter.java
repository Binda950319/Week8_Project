package com.wbh.week8_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wbh.week8_project.R;
import com.wbh.week8_project.javabean.Component;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/19.
 */
public class SaveListAdapter extends BaseAdapter {
    private List<Component> componentList;
    private Context context;
    private LayoutInflater inflater;

    public SaveListAdapter(List<Component> componentList, Context context) {
        this.componentList = componentList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return componentList.size();
    }

    @Override
    public Object getItem(int position) {
        return componentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.save_item_layout, parent, false);
            holder = new ViewHolder();
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String picUrl = componentList.get(position).getPicUrl();
        Picasso.with(context).load(picUrl).placeholder(R.mipmap.game_default_image).into(holder.save_image);
        holder.save_desc.setText(componentList.get(position).getDescription());
        holder.save_price.setText(componentList.get(position).getPrice());
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.save_image)
        ImageView save_image;
        @BindView(R.id.save_desc)
        TextView save_desc;
        @BindView(R.id.save_price)
        TextView save_price;
    }
}

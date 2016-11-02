package com.wbh.week8_project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wbh.week8_project.R;
import com.wbh.week8_project.javabean.Component;
import com.wbh.week8_project.mycallback.OnItemClickLinstener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class SaveAdapter  extends RecyclerView.Adapter<SaveAdapter.MySaveViewHolder> {
    private List<Component> componentList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickLinstener onItemClickLinstener;

    public SaveAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickLinstener(OnItemClickLinstener onItemClickLinstener) {
        this.onItemClickLinstener = onItemClickLinstener;
    }

    public void setItemsList(List<Component> componentList) {
        this.componentList = componentList;
    }

    @Override
    public MySaveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.save_item_layout, parent, false);
        return new MySaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MySaveViewHolder holder, int position) {
        String picUrl = componentList.get(position).getPicUrl();
        Picasso.with(context).load(picUrl).placeholder(R.mipmap.game_default_image).into(holder.save_image);
        holder.save_desc.setText(componentList.get(position).getDescription());
        holder.save_price.setText(componentList.get(position).getPrice());
        //取出最新的位置
        final int pos = holder.getLayoutPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLinstener != null){
//                    Log.e("Bing", "******pos: ******"+pos);
                    onItemClickLinstener.setOnMyItemClickLinstener(pos);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return componentList.size();
    }

    class MySaveViewHolder extends RecyclerView.ViewHolder {
        ImageView save_image;
        TextView save_desc, save_price;

        public MySaveViewHolder(View itemView) {
            super(itemView);
            save_image = (ImageView) itemView.findViewById(R.id.save_image);
            save_desc = (TextView) itemView.findViewById(R.id.save_desc);
            save_price = (TextView) itemView.findViewById(R.id.save_price);
        }
    }
}
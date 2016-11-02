package com.wbh.week8_project.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wbh.week8_project.R;
import com.wbh.week8_project.javabean.DetailListBean;
import com.wbh.week8_project.mycallback.OnItemClickLinstener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/20.
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {
    private ArrayList<DetailListBean.DataBean.ItemsBean> itemsList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickLinstener onItemClickLinstener;

    public DetailAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickLinstener(OnItemClickLinstener onItemClickLinstener) {
        this.onItemClickLinstener = onItemClickLinstener;
    }

    public void setItemsList(ArrayList<DetailListBean.DataBean.ItemsBean> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.detail_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String picUrl = itemsList.get(position).getComponent().getPicUrl();
        Picasso.with(context).load(picUrl).placeholder(R.mipmap.game_default_image).into(holder.detail_image);
        holder.detail_desc.setText(itemsList.get(position).getComponent().getDescription());
        holder.detail_newPrice.setText(itemsList.get(position).getComponent().getPrice());
        holder.detail_oldPrice.setText(itemsList.get(position).getComponent().getOrigin_price());
        //给TextView设置中间横线
        holder.detail_oldPrice.setPaintFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        holder.detail_number.setText(itemsList.get(position).getComponent().getSales());
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
        return itemsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView detail_image;
        TextView detail_desc, detail_newPrice, detail_oldPrice, detail_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            detail_image = (ImageView) itemView.findViewById(R.id.detail_image);
            detail_desc = (TextView) itemView.findViewById(R.id.detail_desc);
            detail_newPrice = (TextView) itemView.findViewById(R.id.detail_newPrice);
            detail_oldPrice = (TextView) itemView.findViewById(R.id.detail_oldPrice);
            detail_number = (TextView) itemView.findViewById(R.id.detail_number);
        }
    }
}

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
import com.wbh.week8_project.javabean.HomeBean;
import com.wbh.week8_project.mycallback.OnItemClickLinstener;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class HomeIntroAdapter extends RecyclerView.Adapter<HomeIntroAdapter.MyViewHolder> {
    private List<HomeBean.DataBean.ItemsBean.ComponentBean.ItemsBean2> items2List = null;
    private Context context;
    private LayoutInflater inflater;
    OnItemClickLinstener onMyItemClickLinstener;

    public HomeIntroAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setItems2List(List<HomeBean.DataBean.ItemsBean.ComponentBean.ItemsBean2> items2List) {
        this.items2List = items2List;
    }

    public void setOnMyItemClickLinstener(OnItemClickLinstener onMyItemClickLinstener) {
        this.onMyItemClickLinstener = onMyItemClickLinstener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.right_item_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //图片地址
        String picUrl = items2List.get(position).getComponent().getPicUrl();
        Picasso.with(context).load(picUrl)
                .placeholder(R.mipmap.game_default_image)
                .into(holder.imageView);
        holder.textView.setText(items2List.get(position).getComponent().getWord());
        //取出最新的位置
        final int pos = holder.getLayoutPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMyItemClickLinstener != null){
//                    Log.e("Bing", "******pos: ******"+pos);
                    onMyItemClickLinstener.setOnMyItemClickLinstener(pos);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items2List.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.right_clothes_image)
        ImageView imageView;
        //        @BindView(R.id.right_clothes_tv)
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.right_clothes_image);
            textView = (TextView) itemView.findViewById(R.id.right_clothes_tv);
        }
    }
}

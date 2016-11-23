package com.bawei.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.test.R;
import com.bawei.test.bean.DataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 王浩雷 on 2016/11/11.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
private ArrayList<DataBean.Data> mDatas;
private Context context;
public  interface OnItemClickLitener
{
    void onItemClick(View view, int position);
    void onItemLongClick(View view , int position);
}

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public MyAdapter(ArrayList<DataBean.Data> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
        View v = View.inflate(context, R.layout.recy_item, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position).efficacy);
        ImageLoader.getInstance().displayImage(mDatas.get(position).goods_img,
                holder.iv);
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.tv, pos);
                    removemDatas(pos);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public void addmDatas(int position)
    {
        mDatas.add(position,mDatas.get(position));
        notifyItemInserted(position);
    }


    public void removemDatas(int position)
    {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv;
    ImageView iv;

    public MyViewHolder(View view) {
        super(view);
        tv = (TextView) view.findViewById(R.id.tv_recy);
        iv = (ImageView) view.findViewById(R.id.iv_recy);
    }
}
}
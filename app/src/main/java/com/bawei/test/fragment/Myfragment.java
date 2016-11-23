package com.bawei.test.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.test.R;
import com.bawei.test.adapter.MyAdapter;
import com.bawei.test.bean.DataBean;
import com.bawei.test.okhttputils.OkHttp;
import com.bawei.test.view.PullBaseView;
import com.bawei.test.view.PullRecyclerView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;


public class Myfragment extends Fragment implements PullRecyclerView.OnFooterRefreshListener,PullRecyclerView.OnHeaderRefreshListener{


    private String url;
    private PullRecyclerView mRecyclerView;
    private MyAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment1, null);

        //获得子控件
        mRecyclerView = (PullRecyclerView) view.findViewById(R.id.id_recyclerview);
        mRecyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
        mRecyclerView.setOnFooterRefreshListener(this);//设置上拉监听
       mRecyclerView.setCanScrollAtRereshing(true);//设置正在刷新时是否可以滑动，默认不可滑动
      //  mRecyclerView.setCanPullDown(false);//设置是否可下拉
      // mRecyclerView.setCanPullUp(false);//设置是否可上拉
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //acti传递的参数
        fragmentbundle();
        //设置布局管理
        //listview展示
        LinearLayoutManager mgr = new LinearLayoutManager(getActivity());
        mgr.setOrientation(LinearLayoutManager.VERTICAL);//方向（纵、横）
        //mRecyclerView.setLayoutManager(mgr);

        //GridView展示
        GridLayoutManager mGrid = new GridLayoutManager(getActivity(), 3);//
        mRecyclerView.setLayoutManager(mGrid);
        intEvnt();

//        //设置动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //okhttp请求网络
        httpjson();
        return view;
    }

    private void intEvnt() {
        adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
               Toast.makeText(getActivity(), position + " long click",
                       Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fragmentbundle() {
        //acti传递的参数
        Bundle bundle = getArguments();
        url = bundle.getString("urls");
    }

    private void httpjson() {
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                System.out.print("数据加载失败。。。。。。。。。。。。。。。。。。。。。。。。。。。");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                String json = result;
                Gson gson = new Gson();
                DataBean db = gson.fromJson(json, DataBean.class);
                ArrayList<DataBean.Data> mDatas = db.data;
                MyAdapter adapter = new MyAdapter(mDatas, getActivity());
                mRecyclerView.setAdapter(adapter);
            }
        });


    }

    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mDatas.add("TEXT更多");
                adapter.notifyDataSetChanged();
               mRecyclerView.onFooterRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //  mDatas.add(0, "TEXT新增");
                adapter.notifyDataSetChanged();
                mRecyclerView.onHeaderRefreshComplete();
            }
        }, 3000);
    }
}

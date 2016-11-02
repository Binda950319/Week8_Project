package com.wbh.week8_project.myfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wbh.week8_project.R;
import com.wbh.week8_project.adapter.SaveListAdapter;
import com.wbh.week8_project.javabean.Component;
import com.wbh.week8_project.mysql.MyDBManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveFragment extends Fragment {
    @BindView(R.id.save_listView)
    ListView save_listView;
    private List<Component> componentList = new ArrayList<>();
    private SaveListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save, container, false);
        ButterKnife.bind(getActivity());
        initRecycler();
        return view;
    }

    private void initRecycler() {
        componentList = MyDBManager.getInstance(getActivity()).queryAllData(null);
        Log.e("Bing", "******ComponentList: ******"+componentList.size());
        adapter = new SaveListAdapter(componentList, getActivity());
        save_listView.setAdapter(adapter);
    }



    //刷新列表
//    public void refreshList(List<Component> list) {
//        adapter.notifyDataSetChanged();
//    }

}

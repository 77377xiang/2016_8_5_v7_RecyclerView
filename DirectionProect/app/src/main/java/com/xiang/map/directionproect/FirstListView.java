package com.xiang.map.directionproect;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class FirstListView extends Activity {
    RecyclerView recyView;
    List<Persen> persens = new ArrayList<>();
    ListRecyViewAdapter listRecyViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        recyView = (RecyclerView) findViewById(R.id.recyView);
        initData();
        listRecyViewAdapter = new ListRecyViewAdapter(FirstListView.this, persens);
        //横向
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyView.setLayoutManager(layoutManager);
        //纵向
       // recyView.setLayoutManager(new LinearLayoutManager(this));

        recyView.setAdapter(listRecyViewAdapter);
        listRecyViewAdapter.setListViewClick(new ListRecyViewAdapter.ListViewClick() {
            @Override
            public void listItemClick(View view, int position) {
                Toast.makeText(FirstListView.this,"点击了"+position,Toast.LENGTH_LONG).show();
            }

            @Override
            public void listItemLongClick(View view, int position) {
                Toast.makeText(FirstListView.this,"长按了"+position,Toast.LENGTH_LONG).show();
            }
        });

    }

    public  void initData(){
        for (int i = 0; i < 50; i++) {
            Persen persen=new Persen();
            persen.setName("小明");
            persen.setHeight("170");
            persens.add(persen);
        }
    }
}
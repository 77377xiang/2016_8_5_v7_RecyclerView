package com.xiang.map.directionproect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ListRecyViewAdapter extends RecyclerView.Adapter<ListRecyViewAdapter.MyViewHolder> {
    List<Persen> persens;
    Context context;
    private ListViewClick listViewClick;

    public void setListViewClick(ListViewClick listViewClick) {
        this.listViewClick = listViewClick;
    }

    public ListRecyViewAdapter(Context context, List<Persen> persens) {
        this.context = context;
        this.persens = persens;
    }

    @Override
    public int getItemCount() {
        return persens.size();
    }

    //返回item的布局
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_listview, null));
        return holder;
    }

    //对控件的操作
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.nameTV.setText(persens.get(position).getName());
        holder.heightTV.setText(persens.get(position).getHeight());
        if (listViewClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //自己定义变量是因为自己可以可以增加头和未 这里的点击位置保持
                    int pos = holder.getLayoutPosition();
                    listViewClick.listItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    listViewClick.listItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    //内部类初始化item 布局控件
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, heightTV;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.nameTV);
            heightTV = (TextView) itemView.findViewById(R.id.heightTV);
        }
    }

    public interface ListViewClick {
        void listItemClick(View view, int position);

        void listItemLongClick(View view, int position);
    }
}
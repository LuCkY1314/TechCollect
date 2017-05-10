package com.example.siqingchan.trytime.filter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.siqingchan.trytime.R;
import com.example.siqingchan.trytime.filter.data.District;
import com.example.siqingchan.trytime.filter.listener.OnFilterMenuItemClickListener;

import java.util.List;


/**
 * Created by siqingchan on 2017/5/8.
 */

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.DistrictHolder> {
    private Context context;
    private List<District.DistrictsBean> list;
    private OnFilterMenuItemClickListener<District.DistrictsBean> listener;
    private static int ALL_TYPE = 1, BLOCKS_TYPE = 2;

    public List<District.DistrictsBean> getList() {
        return list;
    }

    public void setList(List<District.DistrictsBean> list) {
        this.list = list;
    }

    public OnFilterMenuItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnFilterMenuItemClickListener listener) {
        this.listener = listener;
    }

    public DistrictAdapter(Context context, List<District.DistrictsBean> data) {
        super();
        this.context = context;
        this.list = data;
    }

    @Override
    public DistrictHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == ALL_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_filter_distict_menu_all, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_filter_distict_menu, parent, false);
        }
        return new DistrictHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        District.DistrictsBean bean = list.get(position);
        if (bean.getBlocks() == null) {
            return ALL_TYPE;
        } else {
            return BLOCKS_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(DistrictHolder holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DistrictHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public DistrictHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.selector_tv);
            imageView = (ImageView) itemView.findViewById(R.id.selector_iv);
        }
    }
}

package com.example.siqingchan.trytime.filter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siqingchan.trytime.R;

import java.util.List;

/**
 * Created by siqingchan on 2017/5/9.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeHolder> {
    private Context context;
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public TypeAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public TypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fiter_menu_type, null);
        return new TypeHolder(view);
    }

    @Override
    public void onBindViewHolder(TypeHolder holder, final int position) {
        holder.textView.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TypeHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public TypeHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}

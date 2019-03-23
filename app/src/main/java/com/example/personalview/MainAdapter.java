package com.example.personalview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends BaseAdapter {
    private List<String> mBtns;
    private LayoutInflater mInflator;

    private MainAdapterClickListener mListener;

    public MainAdapter(Context context) {
        mInflator = LayoutInflater.from(context);
    }

    public void setListItemListener(MainAdapterClickListener mainAdapterClickListener) {
        mListener = mainAdapterClickListener;
    }

    public void setBtns(List<String> btns) {
        if (mBtns == null) {
            mBtns = new ArrayList<>();
        } else {
            mBtns.clear();
        }
        mBtns.addAll(btns);
    }

    @Override
    public int getCount() {
        return mBtns == null ? 0 : mBtns.size();
    }

    @Override
    public Object getItem(int position) {
        return mBtns == null ? null : mBtns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflator.inflate(R.layout.item_main_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mBtn = convertView.findViewById(R.id.btn_h);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mBtns != null) {
            viewHolder.mBtn.setText(mBtns.get(position));
            viewHolder.mBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null) {
                        mListener.onBtnClick(position);
                    }
                }
            });
        }
        return convertView;
    }

    private class ViewHolder {
        Button mBtn;
    }

    public interface MainAdapterClickListener{
        void onBtnClick(int position);
    }
}

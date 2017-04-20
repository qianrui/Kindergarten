package com.kindergarten.adapter;

import android.content.Context;

import com.kindergarten.R;
import com.kindergarten.adapter.base.BaseAdapter;
import com.kindergarten.adapter.base.BaseViewHolder;
import com.kindergarten.entity.DynamicModel;

import java.util.List;

/**
 * Created by Rui on 2017/4/20 0020.
 */
public class DynamicAdapter extends BaseAdapter<DynamicModel,BaseViewHolder> {
    public DynamicAdapter(Context context, int layoutResId, List<DynamicModel> datas) {
        super(context, layoutResId, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, DynamicModel item) {
        holder.setText(R.id.tv_content,item.getContent());
    }
}

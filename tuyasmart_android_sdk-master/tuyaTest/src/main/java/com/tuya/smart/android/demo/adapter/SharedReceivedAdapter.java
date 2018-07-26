package com.tuya.smart.android.demo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuya.smart.android.demo.R;
import com.tuya.smart.android.demo.widget.ViewHolder;
import com.tuya.smart.android.user.bean.GroupReceivedMemberBean;
import com.tuya.smart.android.user.bean.PersonBean;

import java.util.ArrayList;

/**
 * Created by leaf on 15/12/18.
 * 收到的共享
 */
public class SharedReceivedAdapter extends BaseAdapter {

    private static final String TAG = "SharedReceivedAdapter";

    private final LayoutInflater mInflater;
    private final Context mContext;
    private ArrayList<GroupReceivedMemberBean> mReceivedMembers;

    public SharedReceivedAdapter(Context context) {
        this.mContext = context;
        mReceivedMembers = new ArrayList<>();
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<GroupReceivedMemberBean> groupMembers) {
        mReceivedMembers.clear();
        if (groupMembers != null) {
            mReceivedMembers.addAll(groupMembers);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mReceivedMembers.size();
    }

    @Override
    public GroupReceivedMemberBean getItem(int position) {
        return mReceivedMembers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView)
            convertView = mInflater.inflate(R.layout.list_shared_sent, null);

        TextView name = ViewHolder.get(convertView, R.id.name);
        TextView phone = ViewHolder.get(convertView, R.id.phone);
        View line = ViewHolder.get(convertView, R.id.line);
        View topLine = ViewHolder.get(convertView, R.id.top_line);
        View bottomLine = ViewHolder.get(convertView, R.id.bottom_line);

        PersonBean person = mReceivedMembers.get(position).getUser();

        name.setText(person.getMname());

        String info = person.getMobile();
        if (TextUtils.isEmpty(info)) {
            info = person.getUsername();
        }
        phone.setText(info);

        line.setVisibility(mReceivedMembers.size() == position + 1 ? View.GONE : View.VISIBLE);
        topLine.setVisibility(0 == position ? View.VISIBLE : View.GONE);
        bottomLine.setVisibility(mReceivedMembers.size() == position + 1 ? View.VISIBLE : View.GONE);

        return convertView;
    }
}

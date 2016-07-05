package com.example.admin.blueui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import java.util.List;

/**
 * Created by admin on 01/07/2016.
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {

    private final Context mContext;
    private final List<String> mValues;
    public CustomArrayAdapter(Context context, List<String> values) {
        super(context, -1 ,values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.lvw_devices_element, parent, false);

        String deviceDetails = getItem(position);
        TextView txtVwDeviceDetails = (TextView) view.findViewById(R.id.txtElement);
        ImageButton imgBtnDelete = (ImageButton) view.findViewById(R.id.imgBtnDelete);
        txtVwDeviceDetails.setText(deviceDetails);
        imgBtnDelete.setTag(position);
        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                mValues.remove(position);
                notifyDataSetChanged();
            }
        });
        txtVwDeviceDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityControl.class);
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}

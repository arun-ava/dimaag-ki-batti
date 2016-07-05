package com.example.admin.blueui;

/**
 * Created by admin on 04/07/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class CustomCursorAdapter extends CursorAdapter {

    private DbHelper mDb;
    private Cursor mCursor;
    public CustomCursorAdapter(Context context, Cursor c, DbHelper Db) {
        super(context, c, true);
        mDb = Db;
        mCursor = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.lvw_devices_element,parent, false);
    }

    @Override
    public void bindView(final View view, Context context,final Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.txtElement);
        String name = cursor.getString(1);
        int id = cursor.getInt(0);
        textView.setText(name);
        ImageButton imgbtnDelete = (ImageButton) view.findViewById(R.id.imgBtnDelete);
        imgbtnDelete.setTag(id);
        imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                boolean result = mDb.DeleteById(tag);
                mCursor = mDb.GetAll();
                swapCursor(mCursor);
                notifyDataSetChanged();
            }
        });
    }
}
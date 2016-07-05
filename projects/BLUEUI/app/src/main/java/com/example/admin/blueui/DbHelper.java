package com.example.admin.blueui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 04/07/2016.
 */
public class DbHelper {
    private static final String DATABASE_NAME = "Blue";
    private static final String DATABASE_TABLE = "BluetoothDevice";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "device_name";
    public static final String KEY_ADDRESS = "device_address";

    private static final String TAG = "NamesDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mContext;

    private static final String CreateTableNames = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " ( "
            + KEY_ID + " integer PRIMARY KEY autoincrement, "
            + KEY_NAME + " text , "
            + KEY_ADDRESS + " text , "
            + " UNIQUE (  " + KEY_ADDRESS + " ));";

    private static final String GetTableByNameQuery = " SELECT * FROM " + DATABASE_TABLE + " WHERE "+ KEY_NAME + " LIKE ";
    private static final String GetTableByAddressQuery = " SELECT * FROM " + DATABASE_TABLE + " WHERE "+ KEY_ADDRESS + " LIKE ";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTableNames);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public DbHelper(Context context) {
        mContext = context;
    }

    public DbHelper Open() throws SQLiteException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void Close() {
        if(mDbHelper!=null) {
            mDbHelper.close();
        }
    }

    public long Insert(String deviceName, String deviceAddress) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, deviceName);
        contentValues.put(KEY_ADDRESS, deviceAddress);
        return mDb.insert(DATABASE_TABLE, null, contentValues);
    }

    public Cursor GetByName(String name){
        return mDb.rawQuery( GetTableByNameQuery + name , null);
    }

    public Cursor GetByAddress(String address){
        return mDb.rawQuery( GetTableByAddressQuery + address , null);
    }

    public Cursor GetAll() {
        Cursor res =  mDb.rawQuery( "select * from "+DATABASE_TABLE, null );

        if(res!=null) {
            res.moveToFirst();
        }
        return res;
    }

    public int DeleteTableName() {
        return mDb.delete(DATABASE_TABLE,null,null);
    }

    public boolean DeleteById(int id) {
        String where = KEY_ID + " = " + id;
        return mDb.delete(DATABASE_TABLE, where, null) > 0;
    }
}


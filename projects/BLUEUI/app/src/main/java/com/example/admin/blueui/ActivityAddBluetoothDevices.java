package com.example.admin.blueui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.app.Activity;
import java.util.ArrayList;
import java.util.List;


public class ActivityAddBluetoothDevices extends Activity {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter arrayAdapter;

    final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                arrayAdapter.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__add__bluetooth__devices);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.lvw_element_discovered_devices, R.id.txtvwDiscoveredDevice);
        this.createDeviceList();
    }

    private void createDeviceList() {
        Bundle b = getIntent().getExtras();
        ArrayList<String> existingBluetoothDevices = b.getStringArrayList("devices");
        ArrayList<String> discoveredBluetoothDevices;

        IntentFilter intentFilter  = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_UUID);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, intentFilter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        final ListView deviceList = (ListView) findViewById(R.id.lvwDiscoveredDevices);
        deviceList.setAdapter(arrayAdapter);
        Button btnAddDevices = (Button) findViewById(R.id.btnAddSelectedDevices);
        btnAddDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<deviceList.getCount();i++) {
                    View rowView = deviceList.getChildAt(i);
                    CheckBox chkbxDevice = (CheckBox) rowView.findViewById(R.id.chkbxDiscoveredDevice);
                    if(chkbxDevice.isChecked()) {
                        arrayAdapter.remove(arrayAdapter.getItem(i));
                    }
                }
            }
        });
        bluetoothAdapter.startDiscovery();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

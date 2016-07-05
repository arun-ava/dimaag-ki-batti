package com.example.admin.blueui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;
import android.app.Activity;

public class ActivityManageBlueToothDevices extends Activity {

    BluetoothAdapter bluetoothAdapter;
    ListView lvwDevices;
    ArrayList<String> listOfDevices;
    CustomArrayAdapter customArrayAdapter;
    private final int REQUEST_ENABLE_BT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_bluetooth_devices);

        listOfDevices = new ArrayList<String>();
        listOfDevices.add("Test Device 1");
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent intentEnableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intentEnableBluetooth, REQUEST_ENABLE_BT);
        }
        else
            if(bluetoothAdapter.isEnabled()) {
                this.initialiseDeviceList();
            }
        this.addButtonListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ENABLE_BT) {
            this.initialiseDeviceList();
        }
    }
    private void addButtonListeners() {
        Button btnAddDevices = (Button) findViewById(R.id.btnAddBluetoothDevice);
        btnAddDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityManageBlueToothDevices.this, ActivityAddBluetoothDevices.class);
                Bundle b = new Bundle();
                b.putStringArrayList("Devices", listOfDevices);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
/*
    private void initialiseDeviceList() {

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        lvwDevices = (ListView) findViewById(R.id.lvwDevices);
        //lvwDevices.setChoiceMode(lvwDevices.CHOICE_MODE_MULTIPLE);
        listOfDevices = new ArrayList<String>();

        for (BluetoothDevice pairedBluetoothDevice : pairedDevices) {
            listOfDevices.add(pairedBluetoothDevice.getName() + "\n" + pairedBluetoothDevice.getAddress());
        }

        aaDevices = new ArrayAdapter<String>(this, R.layout.lvw_single_text, R.id.singleTxtElement, listOfDevices);
        lvwDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(view.getId() == R.id.imgBtnDelete) {
                    lvwDevices.removeViewAt(position);
                }
                else {
                    Intent intent = new Intent(ActivityManageBlueToothDevices.this, ActivityControl.class);
                    startActivity(intent);
                }
            }
        });
        lvwDevices.setAdapter(aaDevices);
    }*/

    private void initialiseDeviceList() {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        lvwDevices = (ListView) findViewById(R.id.lvwDevices);

        for (BluetoothDevice pairedBluetoothDevice : pairedDevices) {
            listOfDevices.add(pairedBluetoothDevice.getName() + "\n" + pairedBluetoothDevice.getAddress());
        }

        customArrayAdapter = new CustomArrayAdapter(this, listOfDevices);
        lvwDevices.setAdapter(customArrayAdapter);
    }

    @Override
    public void onBackPressed() {
        // do nothing.
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


}

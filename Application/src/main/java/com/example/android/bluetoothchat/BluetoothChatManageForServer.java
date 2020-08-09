package com.example.android.bluetoothchat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.example.android.common.logger.Log;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import android.os.Handler;

public class BluetoothChatManageForServer {
    BluetoothAdapter mBluetoothAdapter;
    ArrayList<String> deviceName = new ArrayList<String>(); //페어링된 기기 이름을 저장할 리스트
    ArrayList<String> deviceHardwareAddress = new ArrayList<String>(); // 페어링된 기기 MAC Address 저장할 리스트
    BluetoothChatService[] chatServices = new BluetoothChatService[7];
    private final Handler mHandler;
    private Context context;
    int deviceCount; // 페어링된 기기 개수
    ArrayList<UUID> MY_UUID_SECURE = new ArrayList<UUID>();

    private static final String TAG = "BluetoothChatManageForServer";
    private static final String NAME_SECURE = "BluetoothChatServer";


    //페어링된 기기를 불러오고 기기들의 이름과 MAC주소 받아오기 -> 안해도 ok
    BluetoothChatManageForServer(Context mcontext, Handler handler){
        mHandler = handler;
        context = mcontext;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        deviceCount = pairedDevices.size();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                deviceName.add(device.getName());
                deviceHardwareAddress.add(device.getAddress());
            }
        }

        makeDevicesUUID();
    }


    void makeDevicesUUID(){
        MY_UUID_SECURE.add(UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a11"));
        MY_UUID_SECURE.add(UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a22"));
        MY_UUID_SECURE.add(UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a33"));
        MY_UUID_SECURE.add(UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a44"));
        MY_UUID_SECURE.add(UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a55"));
        MY_UUID_SECURE.add(UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66"));
        MY_UUID_SECURE.add(UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a77"));
    }


    void serverStart(){

        for(int i = 0; i < 7; i++){
            chatServices[i] = new BluetoothChatService(context, mHandler, MY_UUID_SECURE.get(i));
        }

    }

    void printPairedDevice(){
        Log.d(TAG, "");
    }



}
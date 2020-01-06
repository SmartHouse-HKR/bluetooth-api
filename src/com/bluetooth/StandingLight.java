package com.bluetooth;

import java.util.ArrayList;

public class StandingLight extends BluetoothDevice {


    public StandingLight(String name, String blueToothURL, String type) {
        super(name, blueToothURL, type);
        initializeArray(getCommandArray());
    }

    public void turnOnLamp () {
        changeState(getCommandArray().get(0));
    }

    public void turnOffLamp () {
        changeState(getCommandArray().get(1));
    }

    private void initializeArray (ArrayList<byte[]> commandArray) {
            String bytes = "A";
            commandArray.add(bytes.getBytes());
            bytes = "B";
            commandArray.add(bytes.getBytes());
    }


}

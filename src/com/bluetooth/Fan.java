package com.bluetooth;

import java.util.ArrayList;

public class Fan extends BluetoothDevice {

    public Fan(String name, String blueToothURL, String type) {
        super(name, blueToothURL, type);
        initializeArray(getCommandArray());
    }

    public void turnOnFan () {
        changeState(getCommandArray().get(0));
    }
    public void turnOffFan () {
        changeState(getCommandArray().get(1));
    }
    public void speedUp () {
        changeState(getCommandArray().get(2));
    }
    public void speedDown () {
        changeState(getCommandArray().get(3));
    }
    public void changeMode () {
        changeState(getCommandArray().get(4));
    }
    public void changeTimer () {
        changeState(getCommandArray().get(5));
    }
    public void changeSwing () {
        changeState(getCommandArray().get(6));
    }

    private void initializeArray (ArrayList<byte[]> commandArray) {
        String bytes = "A";
        commandArray.add(bytes.getBytes());
        bytes = "S";
        commandArray.add(bytes.getBytes());
        bytes = "F";
        commandArray.add(bytes.getBytes());
        bytes = "G";
        commandArray.add(bytes.getBytes());
        bytes = "D";
        commandArray.add(bytes.getBytes());
        bytes = "H";
        commandArray.add(bytes.getBytes());
        bytes = "J";
        commandArray.add(bytes.getBytes());
    }
}

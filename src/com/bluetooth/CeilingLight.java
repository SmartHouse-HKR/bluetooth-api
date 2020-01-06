package com.bluetooth;

import java.util.ArrayList;

public class CeilingLight extends BluetoothDevice {

    public CeilingLight(String name, String blueToothURL, String type) {
        super(name, blueToothURL, type);
        initializeArray(getCommandArray());
    }

    private void turnOnLamp1 () {
        changeState(getCommandArray().get(0));
    }

    private void turnOnLamp2() {
        changeState(getCommandArray().get(1));
    }

    private void turnOnLamp3() {
        changeState(getCommandArray().get(2));
    }

    private void turnOnLamp4() {
        changeState(getCommandArray().get(3));
    }

    private void turnOffLamp1() {
        changeState(getCommandArray().get(4));
    }

    private void turnOffLamp2() {
        changeState(getCommandArray().get(5));
    }

    private void turnOffLamp3() {
        changeState(getCommandArray().get(6));
    }

    private void turnOffLamp4() { changeState(getCommandArray().get(7)); }

    @Deprecated
    public void turnOnAllLamps () {
        String all = "PIYK";
        changeState(all.getBytes());
    }

    @Deprecated
    public void turnOffAllLamps () {
        String all = "OULJ";
        changeState(all.getBytes());
    }

    private void initializeArray (ArrayList<byte[]> commandArray) {
        String bytes = "P";
        commandArray.add(bytes.getBytes());
        bytes = "I";
        commandArray.add(bytes.getBytes());
        bytes = "Y";
        commandArray.add(bytes.getBytes());
        bytes = "K";
        commandArray.add(bytes.getBytes());
        bytes = "O";
        commandArray.add(bytes.getBytes());
        bytes = "U";
        commandArray.add(bytes.getBytes());
        bytes = "L";
        commandArray.add(bytes.getBytes());
        bytes = "J";
        commandArray.add(bytes.getBytes());

    }

    public void turnOnSet (String input) {
        char c = input.charAt(0);
        if (c == '1') {
            turnOnLamp1();
        } else {
            turnOffLamp1();
        }

        c = input.charAt(1);
        if (c == '1') {
            turnOnLamp2();
        } else {
            turnOffLamp2();
        }

        c = input.charAt(2);
        if (c == '1') {
            turnOnLamp3();
        } else {
            turnOffLamp3();
        }

        c = input.charAt(3);
        if (c == '1') {
            turnOnLamp4();
        } else {
            turnOffLamp4();
        }

    }

}

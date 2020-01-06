package com.bluetooth;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public abstract class BluetoothDevice {

    private String name;
    private String type;
    private String blueToothURL;
    private ArrayList<byte[]> commandArray = new ArrayList<>();
    private StreamConnection streamConnection;
    private OutputStream outputStream;

    public BluetoothDevice(String name, String blueToothURL, String type) {
        this.name = name;
        this.blueToothURL = blueToothURL;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<byte[]> getCommandArray() {
        return commandArray;
    }

    public void setCommandArray(ArrayList<byte[]> commandArray) {
        this.commandArray = commandArray;
    }

    public String getName() {
        return name;
    }

    private String getBlueToothURL() {
        return blueToothURL;
    }

    public void setBlueToothURL(String blueToothURL) {
        this.blueToothURL = blueToothURL;
    }

    private StreamConnection getConnection() throws IOException {
        streamConnection = (StreamConnection) Connector.open(getBlueToothURL());
        return streamConnection;
    }

    public OutputStream getOutputStream () {
        if (outputStream == null) {
            try {
                outputStream = getConnection().openOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outputStream;

    }

    public void changeState(byte[] bytes) {
        try {
            OutputStream out = getOutputStream();
            out.write(bytes);
        } catch (IOException e) {
           if (streamConnection != null) {
               try {
                   streamConnection.close();
                   streamConnection = null;
                   OutputStream out = getOutputStream();
                   out.write(bytes);
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
        }
    }

    private void closeConnection () {
        try {
            if (streamConnection != null) {
                streamConnection.close();
            }
            streamConnection = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String toString() {
        return "BluetoothDevice{" +
                "Name=" + name +
                ", blueToothURL='" + blueToothURL + '\'' +
                '}';
    }
}


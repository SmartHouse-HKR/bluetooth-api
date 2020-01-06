package com.bluetooth;

import javax.bluetooth.*;
import java.util.Vector;

class RemoteDeviceDiscovery {

    Vector<RemoteDevice> getDevices() {
        final Vector<RemoteDevice> devicesDiscovered = new Vector<>();
        try {
            final Object inquiryCompletedEvent = new Object();
            LocalDevice device = LocalDevice.getLocalDevice();
            printLocalDevice(device);
            RemoteDevice[] devices = device.getDiscoveryAgent().retrieveDevices(DiscoveryAgent.PREKNOWN);
            //printPairedDevices(devices);
            DiscoveryListener listener = new DiscoveryListener() {

                public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                    /* Get devices paired with system or in range(Without Pair) */
                    devicesDiscovered.addElement(btDevice);
                }

                public void inquiryCompleted(int discType) {
                    /* Notify thread when inquiry completed */
                    synchronized (inquiryCompletedEvent) {
                        inquiryCompletedEvent.notifyAll();
                    }
                }

                /* To find service on bluetooth */
                public void serviceSearchCompleted(int transID, int respCode) {
                }

                /* To find service on bluetooth */
                public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                }
            };

            synchronized (inquiryCompletedEvent) {
                /* Start device discovery */
                boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
                if (started) {
                    System.out.println("wait for device inquiry to complete...");
                    inquiryCompletedEvent.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Return list of devices */
        return devicesDiscovered;
    }


    private void printLocalDevice (LocalDevice device) {
        System.out.println("Address : " + device.getBluetoothAddress());
        System.out.println("Name : " + device.getFriendlyName());
        System.out.println("Powered : " + LocalDevice.isPowerOn());
    }
}

package com.bluetooth;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.bluetooth.*;

public class ServicesSearch {

    private int URL_ATTRIBUTE = 0X0100;
    private UUID SPP = new UUID(0x1101);

    public Map<String, List<String>> getBluetoothDevices() {
        /* Initialize UUID Array */
        UUID[] searchUuidSet = new UUID[]{SPP};
        final Object serviceSearchCompletedEvent = new Object();
        int[] attrIDs = new int[]{URL_ATTRIBUTE};

        RemoteDeviceDiscovery remoteDeviceDiscovery = new RemoteDeviceDiscovery();
        final Map<String, List<String>> mapReturnResult = new HashMap<>();

        try {
            DiscoveryListener listener = new DiscoveryListener() {

                public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {

                }

                public void inquiryCompleted(int discType) {
                }

                public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                    for (ServiceRecord serviceRecord : servRecord) {
                        String url = serviceRecord.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                        if (url == null) {
                            continue;
                        }
                        RemoteDevice rd = serviceRecord.getHostDevice();
                        mapReturnResult.get(rd.getBluetoothAddress()).add(url);
                    }
                }

                public void serviceSearchCompleted(int transID, int respCode) {
                    synchronized (serviceSearchCompletedEvent) {
                        serviceSearchCompletedEvent.notifyAll();
                    }
                }
            };

            for (Enumeration en = remoteDeviceDiscovery.getDevices().elements(); en.hasMoreElements();) {

                RemoteDevice btDevice = (RemoteDevice) en.nextElement();
                List<String> listDeviceDetails = new ArrayList<>();

                try {
                    listDeviceDetails.add(btDevice.getFriendlyName(false));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mapReturnResult.put(btDevice.getBluetoothAddress(), listDeviceDetails);
                synchronized (serviceSearchCompletedEvent) {
                    LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
                    serviceSearchCompletedEvent.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapReturnResult;
    }
}



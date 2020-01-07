package com.bluetooth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BluetoothAdapter {
    private Map<String, List<String>> services;

    public BluetoothAdapter() {
        ServicesSearch servicesSearch = new ServicesSearch();
        services = servicesSearch.getBluetoothDevices();
    }


    public List<BluetoothDevice> getDevices () {
        List<BluetoothDevice> devices = new ArrayList<>();
        services.forEach((k,v)-> {
            System.out.println(v.get(0));

               if (v.get(0).startsWith("Ceiling lamp"))  {
                   try {
                       CeilingLight ceilingLight = new CeilingLight(v.get(0),v.get(1),"C_LAMP");
                       devices.add(ceilingLight);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
            }

                if (v.get(0).startsWith("RNBT")) {
                    try {
                        Fan fan = new Fan (v.get(0), v.get(1), "FAN");
                        devices.add(fan);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                if (v.get(0).startsWith("FireFly")) {
                    try {
                        StandingLight standingLight = new StandingLight(v.get(0), v.get(1), "S_LAMP");
                        devices.add(standingLight);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

        });
        return devices;
    }

}

package com.synergy.synergydhrishtiplus;

import android.app.Application;

import com.koushikdutta.async.AsyncSocket;
import java.util.HashMap;

public class DPApplication extends Application {
    HashMap<Integer, AsyncSocket> socketHashMap = new HashMap<>();

    public void setSocketData(AsyncSocket socket, int portNo){
        socketHashMap.put(portNo, socket);
    }

    public HashMap<Integer, AsyncSocket> getSocketHashMap() {
        return socketHashMap;
    }
}

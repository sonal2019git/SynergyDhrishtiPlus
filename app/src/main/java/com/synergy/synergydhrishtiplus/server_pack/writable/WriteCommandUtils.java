package com.synergy.synergydhrishtiplus.server_pack.writable;

import android.util.Log;

import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.synergy.synergydhrishtiplus.utils.DataConversion;

public class WriteCommandUtils {

    public static void writeASCIIData(String commands, AsyncSocket socket) {
        Util.writeAll(socket, DataConversion.convertToAscii(commands).getBytes(), new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                Log.d("TAG", "Complete");
            }
        });
    }
}

package com.synergy.synergydhrishtiplus.server_pack;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.AsyncServerSocket;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.ListenCallback;
import com.synergy.synergydhrishtiplus.DPApplication;
import com.synergy.synergydhrishtiplus.dialogs.ScannerDialogFragment;
import com.synergy.synergydhrishtiplus.listners.CheckConnectedAndDisconnectedListner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class SocketCreate {
    private InetAddress host;
    private int port;
    private static AsyncSocket socket;
    private static AsyncServer asyncServer;
    static CheckConnectedAndDisconnectedListner checkConnectedAndDisconnectedListner;

    public SocketCreate(String host, int port) {
        try {
            this.host = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        this.port = port;

        setUp();
    }

    public static void updateListner(CheckConnectedAndDisconnectedListner checkConnectedAndDisconnectedListner){
        SocketCreate.checkConnectedAndDisconnectedListner = checkConnectedAndDisconnectedListner;
    }

    public static AsyncSocket getSocket() {
        return socket;
    }

    public static AsyncServer getAsyncServer() {
        return asyncServer;
    }

    private void setUp() {
        asyncServer = new AsyncServer(String.valueOf(port));
        asyncServer.listen(host, port, new ListenCallback() {
            @Override
            public void onAccepted(final AsyncSocket socket) {
                Log.d("TAG", "Receive socket: " + socket.getServer());
                checkConnectedAndDisconnectedListner.updateSocketGlobal(port);
                SocketCreate.socket = socket;
            }

            @Override
            public void onListening(AsyncServerSocket socket) {
                Log.d("TAG", "Server Listning start");
                SocketCreate.socket = null;
            }

            @Override
            public void onCompleted(Exception ex) {
                Log.d("TAG", "Server complete stop");
            }
        });
    }
}

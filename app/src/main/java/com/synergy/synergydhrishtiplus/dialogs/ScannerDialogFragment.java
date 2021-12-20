package com.synergy.synergydhrishtiplus.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.koushikdutta.async.AsyncSocket;
import com.synergy.synergydhrishtiplus.CommonActivity;
import com.synergy.synergydhrishtiplus.DPApplication;
import com.synergy.synergydhrishtiplus.LoginActivity;
import com.synergy.synergydhrishtiplus.R;
import com.synergy.synergydhrishtiplus.SplashActivity;
import com.synergy.synergydhrishtiplus.listners.CheckConnectedAndDisconnectedListner;
import com.synergy.synergydhrishtiplus.server_pack.SocketCreate;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ScannerDialogFragment extends DialogFragment {
    View scanning_bar_view;
    public TextView totalConnected, totalDisconnected;
    List<Integer> portNo = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scanner_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO remove after implement api
        portNo.add(54307);
        portNo.add(54309);
        portNo.add(54306);
        //TODO remove this block after implement
        init(view);
        startScanning();
    }

    private void init(View view) {
        scanning_bar_view = view.findViewById(R.id.scanning_bar_view);
        totalConnected = view.findViewById(R.id.totalConnected);
        totalDisconnected = view.findViewById(R.id.totalDisconnected);
    }

    private void startScanning() {
        Animation animation = AnimationUtils.loadAnimation(requireContext(), R.anim.scanner_anim_file);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                SocketCreate.updateListner(new CheckConnectedAndDisconnectedListner() {
                    @Override
                    public void updateSocketGlobal(int portNo) {
                        try {
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((DPApplication) requireActivity().getApplication()).setSocketData(SocketCreate.getSocket(), portNo);
                                    checkConnectedStatus();
                                }
                            });
                        } catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });

                for (int i = 0; i < portNo.size(); i++) {
                    new SocketCreate("192.168.1.103", portNo.get(i));
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        scanning_bar_view.startAnimation(animation);
    }

    @SuppressLint("SetTextI18n")
    private void checkConnectedStatus() {
        boolean isConnect = false;
        int portSize = portNo.size() - 1;
        HashMap<Integer, AsyncSocket> socketHashMap = ((DPApplication) requireActivity().getApplication()).getSocketHashMap();
        if (socketHashMap.size() > 0) {
            for (int i = 0; i < portNo.size(); i++) {
                if (socketHashMap.containsKey(portNo.get(i))) {
                    if (socketHashMap.size() == portSize) {
                        isConnect = true;
                    }
                }
            }
        } else {
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    totalConnected.setText("Disconnected");
                    totalDisconnected.setText("Disconnected");
                }
            });
        }

        if (socketHashMap.size() == portSize && isConnect) {
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    totalConnected.setText("Connected");
                    totalDisconnected.setText("Connected");
                }
            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(requireContext() != null) {
                        startActivity(new Intent(requireContext(), CommonActivity.class));
                        requireActivity().finish();
                    }
                }
            }, 5000);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

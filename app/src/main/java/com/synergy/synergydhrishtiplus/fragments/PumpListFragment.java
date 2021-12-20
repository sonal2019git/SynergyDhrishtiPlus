package com.synergy.synergydhrishtiplus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.synergy.synergydhrishtiplus.R;
import com.synergy.synergydhrishtiplus.adapters.DispensersAdapter;
import com.synergy.synergydhrishtiplus.adapters.NozzelsAdapter;
import com.synergy.synergydhrishtiplus.adapters.TankAdapter;
import com.synergy.synergydhrishtiplus.data_model.DispenserModel;
import com.synergy.synergydhrishtiplus.data_model.NozzleModel;
import com.synergy.synergydhrishtiplus.data_model.TankModel;
import com.synergy.synergydhrishtiplus.listners.DispenserSelectListner;

import java.util.ArrayList;
import java.util.List;

public class PumpListFragment extends Fragment {
    RecyclerView dispenserListRecyclerView, tankRecyclerView, nozzleRecyclerView, transactionRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pump_list, container, false);
        init(v);

        //TODO extra code remove when use api
        List<NozzleModel> nozzleModels = setAllData();
        List<NozzleModel> nozzleModels11 = new ArrayList<>();
        for(NozzleModel nozzleModel : nozzleModels){
            if(nozzleModel.getPumpId().equals("1")){
                nozzleModels11.add(nozzleModel);
            }
        }
        setNozzleData(nozzleModels11);
        //TODO extra code remove when use api

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setDispenserData();
        setTankData();
    }

    private void init(View v) {
        dispenserListRecyclerView   = v.findViewById(R.id.dispenserListRecyclerView);
        tankRecyclerView            = v.findViewById(R.id.tankRecyclerView);
        nozzleRecyclerView          = v.findViewById(R.id.nozzleRecyclerView);
        transactionRecyclerView     = v.findViewById(R.id.transactionRecyclerView);
    }

    private void setDispenserData(){
        //TODO remove after implement api
        List<DispenserModel> dispenserModels = new ArrayList<>();
        DispenserModel dispenserModel = new DispenserModel();
        dispenserModel.setDispenserId("1");
        dispenserModels.add(dispenserModel);
        dispenserModel = new DispenserModel();
        dispenserModel.setDispenserId("2");
        dispenserModels.add(dispenserModel);

        dispenserModel = new DispenserModel();
        dispenserModel.setDispenserId("3");
        dispenserModels.add(dispenserModel);

        dispenserModel = new DispenserModel();
        dispenserModel.setDispenserId("4");
        dispenserModels.add(dispenserModel);
        //TODO remove after implement api

        DispensersAdapter dispensersAdapter = new DispensersAdapter(requireContext(), dispenserModels, new DispenserSelectListner() {
            @Override
            public void onSelect(String id) {
                List<NozzleModel> nozzleModels = setAllData();
                List<NozzleModel> nozzleModels11 = new ArrayList<>();
                for(NozzleModel nozzleModel : nozzleModels){
                    if(nozzleModel.getPumpId().equals(id)){
                        nozzleModels11.add(nozzleModel);
                    }
                }
                setNozzleData(nozzleModels11);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        dispenserListRecyclerView.setHasFixedSize(true);
        dispenserListRecyclerView.setLayoutManager(linearLayoutManager);
        dispenserListRecyclerView.setAdapter(dispensersAdapter);
    }

    private void setTankData(){
        //TODO remove after implement api
        List<TankModel> dispenserModels = new ArrayList<>();
        TankModel dispenserModel = new TankModel();
        dispenserModel.setProductName("Petrol");
        dispenserModels.add(dispenserModel);
        dispenserModel = new TankModel();
        dispenserModel.setProductName("Diesel");
        dispenserModels.add(dispenserModel);
        dispenserModel = new TankModel();
        dispenserModel.setProductName("Xtra Premium");
        dispenserModels.add(dispenserModel);
        dispenserModel = new TankModel();
        dispenserModel.setProductName("Xtra Premium 95");
        dispenserModels.add(dispenserModel);
        //TODO remove after implement api

        TankAdapter dispensersAdapter = new TankAdapter(requireContext(), dispenserModels);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 1,LinearLayoutManager.VERTICAL, false);
        tankRecyclerView.setHasFixedSize(true);
        tankRecyclerView.setLayoutManager(linearLayoutManager);
        tankRecyclerView.setAdapter(dispensersAdapter);
    }

    private void setNozzleData(List<NozzleModel> nozzleModels){
        NozzelsAdapter dispensersAdapter = new NozzelsAdapter(requireContext(), nozzleModels);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(requireContext(), 4, LinearLayoutManager.VERTICAL, false);
        nozzleRecyclerView.setHasFixedSize(true);
        nozzleRecyclerView.setLayoutManager(linearLayoutManager);
        nozzleRecyclerView.setAdapter(dispensersAdapter);
    }

    //TODO remove after implement api
    private List<NozzleModel> setAllData(){
        List<NozzleModel> dispenserModels = new ArrayList<>();
        NozzleModel dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("1");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("1");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("2");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("1");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("3");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("1");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);


        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("4");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("1");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);


        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("5");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("1");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("6");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("1");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("7");
        dispenserModel.setProduct("XP 95");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("1");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("8");
        dispenserModel.setProduct("XP");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("1");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);


        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("GVR");
        dispenserModel.setNozzleId("1");
        dispenserModel.setProduct("XP");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("2");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("2");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("2");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("3");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("2");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);


        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("4");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("2");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);


        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("5");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("2");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("6");
        dispenserModel.setProduct("Petrol");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("2");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("7");
        dispenserModel.setProduct("XP 95");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("2");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);

        dispenserModel = new NozzleModel();
        dispenserModel.setAmount("0");
        dispenserModel.setVolume("0");
        dispenserModel.setMakeOfDispenser("Tokheim");
        dispenserModel.setNozzleId("8");
        dispenserModel.setProduct("XP");
        dispenserModel.setStatus("IDLE");
        dispenserModel.setPumpId("2");
        dispenserModel.setPrice("94.50");
        dispenserModels.add(dispenserModel);
        return dispenserModels;
    }
    //TODO remove after implement api
}
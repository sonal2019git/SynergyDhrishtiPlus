package com.synergy.synergydhrishtiplus.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.synergy.synergydhrishtiplus.R;
import com.synergy.synergydhrishtiplus.data_model.TankModel;

import org.w3c.dom.Text;

import java.util.List;

public class TankAdapter extends RecyclerView.Adapter<TankAdapter.TankViewHolder> {
    private Context context;
    private List<TankModel> tankModels;

    public TankAdapter(Context context, List<TankModel> tankModels) {
        this.context = context;
        this.tankModels = tankModels;
    }

    @NonNull
    @Override
    public TankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tank_list_single_layout, null);
        return new TankViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TankViewHolder holder, int position) {
        TankModel tankModel = tankModels.get(position);
        holder.tankHeading.setText("Tank "+ (position+1));
        holder.tankProduct.setText(tankModel.getProductName());
    }

    @Override
    public int getItemCount() {
        return tankModels.size();
    }

    class TankViewHolder extends RecyclerView.ViewHolder{
        TextView tankHeading, tankProduct;

        public TankViewHolder(@NonNull View itemView) {
            super(itemView);
            tankHeading = itemView.findViewById(R.id.tankHeading);
            tankProduct = itemView.findViewById(R.id.tankProduct);
        }

    }
}

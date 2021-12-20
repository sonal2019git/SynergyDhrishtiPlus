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
import com.synergy.synergydhrishtiplus.data_model.NozzleModel;

import java.util.List;

public class NozzelsAdapter extends RecyclerView.Adapter<NozzelsAdapter.NozzleViewHolder> {
    private Context context;
    private List<NozzleModel> nozzleModels;

    public NozzelsAdapter(Context context, List<NozzleModel> nozzleModels) {
        this.context        = context;
        this.nozzleModels   = nozzleModels;
    }

    @NonNull
    @Override
    public NozzleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nozzle_list_single_layout, null);
        return new NozzleViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NozzleViewHolder holder, int position) {
        NozzleModel nozzleModel = nozzleModels.get(position);
        holder.nozzleHeading.setText(nozzleModel.getMakeOfDispenser()+" "+nozzleModel.getPumpId()+" - "+nozzleModel.getNozzleId());
        holder.nozzleStatus.setText("STATUS "+nozzleModel.getStatus());
        holder.volumeTv.setText("Vol: "+nozzleModel.getVolume());
        holder.amountTv.setText("Amt: "+nozzleModel.getAmount());
        holder.priceTv.setText("Price: "+nozzleModel.getPrice());
        holder.productTv.setText("Product: "+nozzleModel.getProduct());
    }

    @Override
    public int getItemCount() {
        return nozzleModels.size();
    }

    class NozzleViewHolder extends RecyclerView.ViewHolder{
        TextView nozzleHeading, nozzleStatus, volumeTv, amountTv, priceTv, productTv;

        public NozzleViewHolder(@NonNull View itemView) {
            super(itemView);
            nozzleHeading   = itemView.findViewById(R.id.nozzleHeading);
            nozzleStatus    = itemView.findViewById(R.id.nozzleStatus);
            volumeTv        = itemView.findViewById(R.id.volumeTv);
            amountTv        = itemView.findViewById(R.id.amountTv);
            priceTv         = itemView.findViewById(R.id.priceTv);
            productTv       = itemView.findViewById(R.id.productTv);

        }
    }
}

package cg.nic.bilaspur.covidenquiry.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import cg.nic.bilaspur.covidenquiry.Activity.CovidEntry;

import cg.nic.bilaspur.covidenquiry.Activity.ResolveScreen;
import cg.nic.bilaspur.covidenquiry.Model.Accident;
import cg.nic.bilaspur.covidenquiry.R;

public class AccidentAdapter extends RecyclerView.Adapter<AccidentAdapter.ViewHolder>{

    private Context context;
    private List<Accident> list;


    public AccidentAdapter(Context context, List<Accident> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.accident_details_screen, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Accident status = list.get(position);

        holder.textAddress.setText(status.getAddress());
        holder.textType.setText(String.valueOf(status.getType()));
        holder.textDescription.setText(String.valueOf(status.getDescription()));
        holder.textLocation.setText(status.getLocation());
        holder.textCreatedAt.setText(status.getCreatedAt());
        holder.textStatus.setText(status.getStatus());

        Glide.with(context).load(status.getPhoto()).into(holder.nicPhoto);

        holder.itemView.setOnClickListener(v-> {
            Intent intent= new Intent(context, ResolveScreen.class);
            intent.putExtra("address", status.getAddress());
            intent.putExtra("type", status.getType());
            intent.putExtra("description", status.getDescription());
            intent.putExtra("location", status.getLocation());
            intent.putExtra("createdAt", status.getCreatedAt());
            intent.putExtra("status", status.getStatus());

            intent.putExtra("image", status.getPhoto());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            //((CovidEntry)context).adminForwardAccident();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textAddress, textType, textDescription, textLocation, textCreatedAt, textStatus;
        public ImageView nicPhoto;
        public Button forward;

        public ViewHolder(View itemView) {
            super(itemView);

            textAddress = itemView.findViewById(R.id.address);
            textType = itemView.findViewById(R.id.type);
            textDescription = itemView.findViewById(R.id.description);
            textLocation = itemView.findViewById(R.id.location);
            textCreatedAt = itemView.findViewById(R.id.created_at);
            textStatus = itemView.findViewById(R.id.status);

            nicPhoto = itemView.findViewById(R.id.photo);


        }
    }

}
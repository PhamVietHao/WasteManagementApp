package com.example.wastemanagementapp;


import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class BinPageActivity extends AppCompatActivity {
    private RecyclerView binList;
    private BinAdapter binAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_page);

        binList = findViewById(R.id.bin_list);
        binList.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        // Sample data
        List<Bin> bins = getSampleData();
        binAdapter = new BinAdapter(bins);
        binList.setAdapter(binAdapter);
    }

    private List<Bin> getSampleData() {
        List<Bin> bins = new ArrayList<>();
        bins.add(new Bin("Bin 1", "Address 1", 50));
        bins.add(new Bin("Bin 2", "Address 2", 85));
        bins.add(new Bin("Bin 3", "Address 3", 30));
        bins.add(new Bin("Bin 4", "Address 4", 90));
        bins.add(new Bin("Bin 5", "Address 5", 60));
        bins.add(new Bin("Bin 6", "Address 6", 10));
        return bins;
    }

    class BinAdapter extends RecyclerView.Adapter<BinAdapter.BinViewHolder> {
        private List<Bin> binList;

        public BinAdapter(List<Bin> binList) {
            this.binList = binList;
        }

        @NonNull
        @Override
        public BinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bin_item, parent, false);
            return new BinViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BinViewHolder holder, int position) {
            Bin bin = binList.get(position);
            holder.binName.setText(bin.getName());
            holder.binAddress.setText(bin.getAddress());
            holder.binFullness.setText("Fullness: " + bin.getFullness() + "%");

            if (bin.getFullness() >= 80) {
                holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(android.R.color.holo_red_light));
                holder.binImage.setImageResource(R.drawable.full_bin);
            } else {
                holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(android.R.color.holo_green_light));
                holder.binImage.setImageResource(R.drawable.bin);
            }

            holder.checkButton.setOnClickListener(v -> {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Confirm")
                        .setMessage("Are you sure you want to mark this bin as checked?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Handle the positive button action
                        })
                        .setNegativeButton("No", null)
                        .show();
            });
        }

        @Override
        public int getItemCount() {
            return binList.size();
        }

        class BinViewHolder extends RecyclerView.ViewHolder {
            TextView binName, binAddress, binFullness;
            ImageView binImage;
            Button checkButton;

            public BinViewHolder(@NonNull View itemView) {
                super(itemView);
                binName = itemView.findViewById(R.id.bin_name);
                binAddress = itemView.findViewById(R.id.bin_address);
                binFullness = itemView.findViewById(R.id.bin_fullness);
                binImage = itemView.findViewById(R.id.bin_image);
                checkButton = itemView.findViewById(R.id.check_button);
            }
        }
    }

    class Bin {
        private String name;
        private String address;
        private int fullness;

        public Bin(String name, String address, int fullness) {
            this.name = name;
            this.address = address;
            this.fullness = fullness;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public int getFullness() {
            return fullness;
        }
    }
}



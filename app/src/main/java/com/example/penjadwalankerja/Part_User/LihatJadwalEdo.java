package com.example.penjadwalankerja.Part_User;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penjadwalankerja.GoogleMapsActivity;
import com.example.penjadwalankerja.Model.PelangganEdo;
import com.example.penjadwalankerja.ViewHolder.PelangganViewHolderEdo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.penjadwalankerja.R;

public class LihatJadwalEdo extends AppCompatActivity {

    Button btnMaps;

    // [START define_database_reference]
    public DatabaseReference mDatabase;
    // [END define_database_reference]

    public FirebaseRecyclerAdapter<PelangganEdo, PelangganViewHolderEdo> mAdapter;
    public RecyclerView mRecycler;
    public LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal_edo);
        setTitle("Daftar Jadwal Edo");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.list_pelanggan);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<PelangganEdo>()
                .setQuery(query, PelangganEdo.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<PelangganEdo, PelangganViewHolderEdo>(options) {
            @Override
            public PelangganViewHolderEdo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new PelangganViewHolderEdo(inflater.inflate(R.layout.item_pelanggan, parent, false));
            }
            @Override
            protected void onBindViewHolder(@NonNull PelangganViewHolderEdo holder, int position, @NonNull final PelangganEdo model) {
                holder.bindToPelanggan(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(model.alamat));
                        startActivity(intent);
                    }
                });
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        btnMaps = (Button)findViewById(R.id.maps);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatJadwalEdo.this, GoogleMapsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase){
        Query query = mDatabase.child("Edo");
        return query;
    }
}
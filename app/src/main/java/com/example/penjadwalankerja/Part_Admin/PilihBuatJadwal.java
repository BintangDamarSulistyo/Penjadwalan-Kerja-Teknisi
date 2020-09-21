package com.example.penjadwalankerja.Part_Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.penjadwalankerja.R;

public class PilihBuatJadwal extends AppCompatActivity {

    CardView cvBuatEcha, cvBuatEdo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_buat_jadwal);

        cvBuatEcha = (CardView) findViewById(R.id.cvbuatecha);
        cvBuatEdo = (CardView) findViewById(R.id.cvbuatedo);

        cvBuatEcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihBuatJadwal.this, BuatJadwalEcha.class);
                startActivity(intent);
            }
        });

        cvBuatEdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihBuatJadwal.this, BuatJadwalEdo.class);
                startActivity(intent);
            }
        });

    }
}
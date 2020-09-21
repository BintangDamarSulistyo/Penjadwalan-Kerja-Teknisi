package com.example.penjadwalankerja.Part_Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.penjadwalankerja.R;

public class PilihUbahJadwal extends AppCompatActivity {

    CardView cvubahecha, cvubahedo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_ubah_jadwal);

        cvubahecha = (CardView) findViewById(R.id.cvubahecha);
        cvubahedo = (CardView) findViewById(R.id.cvubahedo);

        cvubahecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihUbahJadwal.this, UbahJadwalEcha.class);
                startActivity(intent);
            }
        });

        cvubahedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihUbahJadwal.this, UbahJadwalEdo.class);
                startActivity(intent);
            }
        });

    }
}
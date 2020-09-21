package com.example.penjadwalankerja.UbahSingleJadwal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.penjadwalankerja.Model.CustomerEcha;
import com.example.penjadwalankerja.R;

public class UbahSingleJadwal extends AppCompatActivity {

    private EditText etNamaPelanggan;
    private EditText etAlamat;
    private EditText etNoTelepon;
    private EditText etTimeResult;
    private EditText etDateResult;
    private Button btSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_jadwal_echa);
        etNamaPelanggan = (EditText) findViewById(R.id.et_namapelanggan);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        etNoTelepon = (EditText) findViewById(R.id.et_notelepon);
        etTimeResult = (EditText) findViewById(R.id.et_timeresult);
        etDateResult = (EditText) findViewById(R.id.et_dateresult) ;
        btSave = (Button) findViewById(R.id.bt_save);

        etNamaPelanggan.setEnabled(false);
        etAlamat.setEnabled(false);
        etNoTelepon.setEnabled(false);
        etTimeResult.setEnabled(false);
        etDateResult.setEnabled(false);
        btSave.setVisibility(View.GONE);

        CustomerEcha customer = (CustomerEcha) getIntent().getSerializableExtra("data");
        if(customer!=null){
            etNamaPelanggan.setText(customer.getNamaPelanggan());
            etAlamat.setText(customer.getAlamat());
            etNoTelepon.setText(customer.getNoTelepon());
            etTimeResult.setText(customer.getTimeResult());
            etDateResult.setText(customer.getDateResult());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, UbahSingleJadwal.class);
    }
}
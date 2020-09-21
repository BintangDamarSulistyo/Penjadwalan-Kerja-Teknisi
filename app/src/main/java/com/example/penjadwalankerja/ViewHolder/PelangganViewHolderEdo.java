package com.example.penjadwalankerja.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.penjadwalankerja.Model.PelangganEdo;
import com.example.penjadwalankerja.R;


public class PelangganViewHolderEdo extends RecyclerView.ViewHolder {

    public TextView tvNamaPelanggan;
    public TextView tvAlamat;
    public TextView tvNoTelepon;

    public PelangganViewHolderEdo(View itemView) {
        super(itemView);
        tvNamaPelanggan = itemView.findViewById(R.id.tv_namapelanggan);
        tvAlamat = itemView.findViewById(R.id.tv_alamat);
        tvNoTelepon = itemView.findViewById(R.id.tv_notelepon);
    }

    public void bindToPelanggan(PelangganEdo Pelangganedo, View.OnClickListener onClickListener){
        tvNamaPelanggan.setText(Pelangganedo.namapelanggan);
        tvAlamat.setText(Pelangganedo.alamat);
        tvNoTelepon.setText(Pelangganedo.notelepon);
    }
}
package com.example.penjadwalankerja.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.penjadwalankerja.Model.PelangganEcha;
import com.example.penjadwalankerja.R;


public class PelangganViewHolderEcha extends RecyclerView.ViewHolder {

    public TextView tvNamaPelanggan;
    public TextView tvAlamat;
    public TextView tvNoTelepon;

    public PelangganViewHolderEcha(View itemView) {
        super(itemView);
        tvNamaPelanggan = itemView.findViewById(R.id.tv_namapelanggan);
        tvAlamat = itemView.findViewById(R.id.tv_alamat);
        tvNoTelepon = itemView.findViewById(R.id.tv_notelepon);
    }

    public void bindToPelanggan(PelangganEcha Pelangganecha, View.OnClickListener onClickListener){
        tvNamaPelanggan.setText(Pelangganecha.namapelanggan);
        tvAlamat.setText(Pelangganecha.alamat);
        tvNoTelepon.setText(Pelangganecha.notelepon);
    }
}
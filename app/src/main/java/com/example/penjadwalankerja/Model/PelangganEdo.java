package com.example.penjadwalankerja.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Avin on 08/08/2018.
 */

public class PelangganEdo {
    public String namapelanggan;
    public String alamat;
    public String notelepon;

    public PelangganEdo() {
    }

    public PelangganEdo(String nmplgn, String almt, String notlp) {
        this.namapelanggan = nmplgn;
        this.alamat = almt;
        this.notelepon = notlp;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama", namapelanggan);
        result.put("alamat", alamat);
        result.put("telepon", notelepon);
        return result;
    }
}

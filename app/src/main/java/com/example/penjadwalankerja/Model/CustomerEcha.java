package com.example.penjadwalankerja.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class CustomerEcha implements Serializable {

    private String namapelanggan;
    private String alamat;
    private String notelepon;
    private String timeresult;
    private String dateresult;
    private String key;

    public CustomerEcha() {

    }

    public String getKey () {
        return key;
    }

    public void setKey (String key){
        this.key = key;
    }

    public String getNamaPelanggan () {
        return namapelanggan;
    }

    public void setNamaPelanggan (String namapelanggan){
        this.namapelanggan = namapelanggan;
    }

    public String getAlamat () {
        return alamat;
    }

    public void setAlamat (String alamat){
        this.alamat = alamat;
    }

    public String getNoTelepon () {
        return notelepon;
    }

    public void setNoTelepon (String notelepon){
        this.notelepon = notelepon;
    }

    public String getTimeResult () {
        return timeresult;
    }

    public void setTimeResult (String timeresult){
        this.timeresult = timeresult;
    }

    public String getDateResult () { return dateresult; }

    public void setDateResult (String dateresult){
        this.dateresult = dateresult;
    }

    @Override
    public String toString () {
        return " " + namapelanggan + "\n" +
                " " + alamat + "\n" +
                " " + notelepon + "\n" +
                " " + timeresult + "\n" +
                " " + dateresult;
    }

    public CustomerEcha(String nmplgn, String almt, String notlp, String jm, String tgl)
    {
        namapelanggan = nmplgn;
        alamat = almt;
        notelepon = notlp;
        timeresult = jm;
        dateresult = tgl;
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

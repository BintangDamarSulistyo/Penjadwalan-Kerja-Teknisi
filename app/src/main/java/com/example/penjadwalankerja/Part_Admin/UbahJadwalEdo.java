package com.example.penjadwalankerja.Part_Admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penjadwalankerja.Model.CustomerEdo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.example.penjadwalankerja.Adapter.AdapterDataRecyclerViewEdo;
import com.example.penjadwalankerja.R;

public class UbahJadwalEdo extends AppCompatActivity implements AdapterDataRecyclerViewEdo.FirebaseDataListener {

    /**
     * Mendefinisikan variable yang akan dipakai
     */
    public DatabaseReference database;
    public RecyclerView rvView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    public ArrayList<CustomerEdo> daftarCustomerEdo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Mengeset layout
         */
        setContentView(R.layout.activity_ubah_jadwal_edo);
        setTitle("Daftar Jadwal Edo");

        /**
         * Inisialisasi RecyclerView & komponennya
         */
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        /**
         * Inisialisasi dan mengambil Firebase Database Reference
         */
        database = FirebaseDatabase.getInstance().getReference();

        /**
         * Mengambil data customer dari Firebase Realtime DB
         */
        database.child("Edo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarCustomerEdo = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Barang
                     * Dan juga menyimpan primary key pada object Barang
                     * untuk keperluan Edit dan Delete data
                     */
                    CustomerEdo customeredo = noteDataSnapshot.getValue(CustomerEdo.class);
                    customeredo.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object Barang yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarCustomerEdo.add(customeredo);
                }

                /**
                 * Inisialisasi adapter dan data barang dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new AdapterDataRecyclerViewEdo(daftarCustomerEdo, UbahJadwalEdo.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, UbahJadwalEdo.class);
    }

    @Override
    public void onDeleteData(CustomerEdo customeredo, final int position) {
        /**
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter lewat interface.
         * Yang kemudian akan mendelete data di Firebase Realtime DB
         * berdasarkan key barang.
         * Jika sukses akan memunculkan SnackBar
         */
        if(database!=null){
            database.child("Edo").child(customeredo.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(UbahJadwalEdo.this,"success delete", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
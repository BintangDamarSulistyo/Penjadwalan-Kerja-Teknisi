package com.example.penjadwalankerja.Part_Admin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.penjadwalankerja.Model.CustomerEcha;
import com.example.penjadwalankerja.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuatJadwalEcha extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private EditText etNamaPelanggan;
    private EditText etAlamat;
    private EditText etNoTelepon;
    private EditText etTimeResult;
    private EditText etDateResult;
    private Button btSave;

    private Button btTimePicker;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button btDatePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_jadwal_echa);

        etTimeResult = (EditText) findViewById(R.id.et_timeresult);
        btTimePicker = (Button) findViewById(R.id.bt_showtimepicker);
        btTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    private void showTimeDialog() {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                etTimeResult.setText(+hourOfDay+":"+minute);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();

        /**
         * Kita menggunakan format tanggal dd-MM-yyyy
         * jadi nanti tanggal nya akan diformat menjadi
         * misalnya 01-12-2017
         */
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        etDateResult = (EditText) findViewById(R.id.et_dateresult);
        btDatePicker = (Button) findViewById(R.id.bt_datepicker);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                etDateResult.setText(""+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();

        // inisialisasi fields EditText dan Button
        etNamaPelanggan = (EditText) findViewById(R.id.et_namapelanggan);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        etNoTelepon = (EditText) findViewById(R.id.et_notelepon);
        etTimeResult = (EditText) findViewById(R.id.et_timeresult);
        etDateResult = (EditText) findViewById(R.id.et_dateresult);
        btSave = (Button) findViewById(R.id.bt_save);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final CustomerEcha customerecha = (CustomerEcha) getIntent().getSerializableExtra("data");

        if (customerecha != null) {
            etNamaPelanggan.setText(customerecha.getNamaPelanggan());
            etAlamat.setText(customerecha.getAlamat());
            etNoTelepon.setText(customerecha.getNoTelepon());
            etTimeResult.setText(customerecha.getTimeResult());
            etDateResult.setText(customerecha.getDateResult());
            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customerecha.setNamaPelanggan(etNamaPelanggan.getText().toString());
                    customerecha.setAlamat(etAlamat.getText().toString());
                    customerecha.setNoTelepon(etNoTelepon.getText().toString());
                    customerecha.setTimeResult(etTimeResult.getText().toString());
                    customerecha.setDateResult(etDateResult.getText().toString());

                    updateCustomer(customerecha);
                }
            });
        } else {
            btSave.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.CUPCAKE)
                @Override
                public void onClick(View view) {
                    if(!isEmpty(etNamaPelanggan.getText().toString()) && !isEmpty(etAlamat.getText().toString()) && !isEmpty(etNoTelepon.getText().toString()) && !isEmpty(etTimeResult.getText().toString())&& !isEmpty(etDateResult.getText().toString()))
                        submitCustomer(new CustomerEcha(etNamaPelanggan.getText().toString(), etAlamat.getText().toString(), etNoTelepon.getText().toString(), etTimeResult.getText().toString(), etDateResult.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.bt_save), "Data Tidak Boleh Kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            etNamaPelanggan.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s){
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateCustomer(CustomerEcha customerecha) {
        /**
         * Baris kode yang digunakan untuk mengupdate data customer
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("Echa") //akses parent index, ibaratnya seperti nama tabel
                .child(customerecha.getKey()) //select customer berdasarkan key
                .setValue(customerecha) //set value customer yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update data sukses
                         */
                        Snackbar.make(findViewById(R.id.bt_save), "Data Berhasil Diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitCustomer(CustomerEcha customeredo) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("Echa").push().setValue(customeredo).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNamaPelanggan.setText("");
                etAlamat.setText("");
                etNoTelepon.setText("");
                etTimeResult.setText("");
                etDateResult.setText("");
                Snackbar.make(findViewById(R.id.bt_save), "Data Berhasil Ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, BuatJadwalEcha.class);
    }
}

package com.example.penjadwalankerja.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.penjadwalankerja.Model.CustomerEdo;
import com.example.penjadwalankerja.Part_Admin.BuatJadwalEdo;
import com.example.penjadwalankerja.Part_Admin.UbahJadwalEdo;
import com.example.penjadwalankerja.UbahSingleJadwal.UbahSingleJadwal;
import com.example.penjadwalankerja.R;

public class AdapterDataRecyclerViewEdo extends RecyclerView.Adapter<AdapterDataRecyclerViewEdo.ViewHolder> {

    public ArrayList<CustomerEdo> daftarCustomerEdo;
    public Context context;
    FirebaseDataListener listener;

    public AdapterDataRecyclerViewEdo(ArrayList<CustomerEdo> customers, Context ctx) {
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarCustomerEdo = customers;
        context = ctx;
        listener = (UbahJadwalEdo)ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_dataorder);
            cvMain = (CardView) v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_order, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarCustomerEdo.get(position).getNamaPelanggan();
        System.out.println("Data CustomerEcha one by one "+position+daftarCustomerEdo.size());
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
                context.startActivity(UbahSingleJadwal.getActIntent((Activity) context).putExtra("data", daftarCustomerEdo.get(position)));
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(BuatJadwalEdo.getActIntent((Activity) context).putExtra("data", daftarCustomerEdo.get(position)));
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                listener.onDeleteData(daftarCustomerEdo.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarCustomerEdo.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(CustomerEdo customeredo, int position);
    }
}
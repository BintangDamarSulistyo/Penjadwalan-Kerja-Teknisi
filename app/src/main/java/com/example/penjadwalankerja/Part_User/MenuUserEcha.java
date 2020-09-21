package com.example.penjadwalankerja.Part_User;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.penjadwalankerja.R;
import com.example.penjadwalankerja.Tentang;

public class MenuUserEcha extends AppCompatActivity {

    CardView _cvlihat, _cvtentang, _cvexit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user_echa);

        _cvlihat = (CardView) findViewById(R.id.cvlihat);
        _cvtentang = (CardView) findViewById(R.id.cvtentang);

        _cvlihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUserEcha.this, LihatJadwalEcha.class);
                startActivity(intent);
            }
        });

        _cvtentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUserEcha.this, Tentang.class);
                startActivity(intent);
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        _cvexit = (CardView) findViewById(R.id.cvexit);
        _cvexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        moveTaskToBack(true);
                        finish();
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                moveTaskToBack(true);
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
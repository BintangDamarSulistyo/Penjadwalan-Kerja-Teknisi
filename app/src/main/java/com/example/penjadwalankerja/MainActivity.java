package com.example.penjadwalankerja;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.penjadwalankerja.Part_Admin.MenuAdmin;
import com.example.penjadwalankerja.Part_User.MenuUserEcha;
import com.example.penjadwalankerja.Part_User.MenuUserEdo;

public class MainActivity extends AppCompatActivity {
    EditText _txtUser;
    Button _btnLogin;
    Spinner _spinner;
    private EditText _txtPass;
    private CheckBox ShowPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowPass = (CheckBox) findViewById(R.id.showPass);
        _txtPass = (EditText) findViewById(R.id.txtPass);
        _txtUser = (EditText) findViewById(R.id.txtUser);
        _btnLogin = (Button) findViewById(R.id.btnLogin);
        _spinner = (Spinner) findViewById(R.id.spinner);

        //Set onClickListener, untuk menangani kejadian saat Checkbox diklik
        ShowPass.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(ShowPass.isChecked()) {
                                                //Saat Checkbox dalam keadaan Checked, maka password akan ditampilkan
                                                _txtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                            } else {
                                                //Jika tidak, maka password akan disembunyikan
                                                _txtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                            }
                                        }
                                    }
        );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();
                if (_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin")) {
                    Intent intent = new Intent(MainActivity.this, MenuAdmin.class);
                    startActivity(intent);
                }
                else if (_txtUser.getText().toString().equals("edo") && _txtPass.getText().toString().equals("edo"))
                {
                    Intent intent = new Intent(MainActivity.this, MenuUserEdo.class);
                    startActivity(intent);
                }
                else if (_txtUser.getText().toString().equals("echa") && _txtPass.getText().toString().equals("echa"))
                {
                    Intent intent = new Intent(MainActivity.this, MenuUserEcha.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
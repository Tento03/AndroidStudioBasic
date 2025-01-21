package com.example.sqllite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tambah_data extends AppCompatActivity {

    EditText nama,alamat;
    Button simpan;
    Mahasiswa mahasiswa;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        mahasiswa=new Mahasiswa(getApplicationContext());

        nama=findViewById(R.id.nama);
        alamat=findViewById(R.id.alamat);
        simpan=findViewById(R.id.simpandata);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpann_data();
                finish();
            }
        });

    }

    void simpann_data(){
        mahasiswa.simpan_data(nama.getText().toString(), alamat.getText().toString());
    }
}
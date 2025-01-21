package com.example.sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends AppCompatActivity {

    EditText nama,alamat;
    Button edit;
    Mahasiswa mahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edit=findViewById(R.id.edit);
        nama=findViewById(R.id.nama);
        alamat=findViewById(R.id.alamat);

        getSupportActionBar().setTitle("Edit"+getIntent().getStringExtra("nama"));
        mahasiswa=new Mahasiswa(getApplicationContext());
        nama.setText(getIntent().getStringExtra("nama"));
        alamat.setText(getIntent().getStringExtra("alamat"));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mahasiswa.update_data(
                        getIntent().getStringExtra("id"),
                        nama.getText().toString(),
                        alamat.getText().toString()
                );
            }
        });

    }
}

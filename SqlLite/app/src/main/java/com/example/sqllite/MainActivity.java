package com.example.sqllite;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button tambah_data;
    customAdapter adapter;
    Cursor cursor;
    Mahasiswa mahasiswa;
    ArrayList<Objek> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        tambah_data=findViewById(R.id.tambah);

        mahasiswa=new Mahasiswa(getApplicationContext());

        tambah_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),tambah_data.class);
                startActivity(intent);
            }
        });
        ambil_data();
    }
    void ambil_data(){
        list=new ArrayList<Objek>();
        cursor=mahasiswa.tampil_data();
        if(cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                String id=cursor.getString(0);
                String nama=cursor.getString(1);
                String alamat=cursor.getString(2);
                list.add(new Objek(
                        id,
                        nama,
                        alamat
                ));
            }
            adapter=new customAdapter(getApplicationContext(),list,MainActivity.this);
            listView.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ambil_data();
    }
}

class customAdapter extends BaseAdapter {
    Activity activity;
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Objek> model;
    Mahasiswa mahasiswa;

    customAdapter(Context context,ArrayList<Objek>model,Activity activity){
        this.context=context;
        this.model=model;
        this.activity=activity;
        layoutInflater = LayoutInflater.from(context);
        mahasiswa=new Mahasiswa(context);
    }

    @Override

    //hitung besar array list
    public int getCount() {
        return model.size();
    }

    @Override
    //cari posisi array list
    public Object getItem(int i) {
        return model.get(i);
    }

    @Override
    //cari index dlm array list
    public long getItemId(int i) {
        return i;
    }

    TextView id,nama,alamat;
    Button edit,hapus;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=layoutInflater.inflate(R.layout.list_data,viewGroup,false);

        id=view1.findViewById(R.id.id_data);
        nama=view1.findViewById(R.id.nama);
        alamat=view1.findViewById(R.id.alamat);
        edit=view1.findViewById(R.id.edit);
        hapus=view1.findViewById(R.id.hapus);

        id.setText(model.get(i).getId());
        nama.setText(model.get(i).getNama());
        alamat.setText(model.get(i).getAlamat());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Edit.class);
                intent.putExtra("nama",model.get(i).getNama());
                intent.putExtra("alamat",model.get(i).getAlamat());
                intent.putExtra("id",model.get(i).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setTitle("Tanya");
                builder.setMessage("Apakah ingin dihapus?");
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mahasiswa.delete_data(model.get(i).getId());
//                        ((Activity)context).overridePendingTransition(0,0);

                        Intent intent=new Intent(context,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
//                        ((Activity)context).finish();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });


        return view1;
    }
}

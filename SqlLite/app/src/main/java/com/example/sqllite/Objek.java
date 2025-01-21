package com.example.sqllite;

public class Objek {
    String id="";
    String nama="";
    String alamat="";

    Objek(String id,String nama,String alamat){
        this.nama=nama;
        this.alamat=alamat;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }
}

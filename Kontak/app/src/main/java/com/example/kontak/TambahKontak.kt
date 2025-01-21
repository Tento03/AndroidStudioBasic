package com.example.kontak

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class TambahKontak : AppCompatActivity() {
    lateinit var simpan:Button;
    lateinit var keluar:Button;
    lateinit var nama:EditText;
    lateinit var nim:EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kontak)

        simpan=findViewById(R.id.simpan);
        keluar=findViewById(R.id.keluar);
        nama=findViewById(R.id.tambahnama);
        nim=findViewById(R.id.tambahnim);
        keluar.setOnClickListener(){
            finish();
        }
        simpan.setOnClickListener(){
            simpan_data();
        }
    }
    fun simpan_data(){
        var url:String="http://192.168.237.125/latihan/insert_data.php";
        var stringrequest=object :StringRequest(Request.Method.POST,url,
            Response.Listener {response->
                try {
                    var jsonobject=JSONObject(response);
                    var jsonarray=jsonobject.getJSONArray("server_response");
                    var obj=jsonarray.getJSONObject(0);
                    var resp=obj.getString("status");
                    if (resp=="sukses"){
                        Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT)
                            .show()
                        nama.setText("");
                        nim.setText("");
                    } else {
                        Toast.makeText(this, "Registrasi gagal", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                catch (e: JSONException) {
                    e.printStackTrace();
                }
            },
        Response.ErrorListener {error->
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
        }){
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["nama"] = nama.getText().toString();
                params["nim"] = nim.getText().toString();
                return params
            }
        }
        var requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);
    }
}
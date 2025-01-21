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

class UpdateKontak : AppCompatActivity() {
    lateinit var simpan:Button;
    lateinit var keluar:Button;
    lateinit var nama:EditText;
    lateinit var nim:EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_kontak);

        simpan=findViewById(R.id.simpan);
        keluar=findViewById(R.id.keluar);
        nama=findViewById(R.id.updatenama);
        nim=findViewById(R.id.updatenim);
        keluar.setOnClickListener(){
            finish();
        }
        simpan.setOnClickListener(){
            simpan_data();
        }
    }

    fun simpan_data(){
        var up=Lihat_kontak();
        up.update_data();
    }
    fun update_data(){
        var id=intent.getStringExtra("id")
        var url:String="http://192.168.237.125/latihan/update_data.php?id=$id"
        var stringRequest=object :StringRequest(Request.Method.POST,url,
            {response ->
                try {
                    var jsonobject=JSONObject(response);
                    var jsonarray=jsonobject.getJSONArray("status");
                    var obj=jsonarray.getJSONObject(0);
                    var resp=obj.getString("status");
                    if (resp=="sukses"){
                        Toast.makeText(this, "berhasil diupdate", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else{
                        Toast.makeText(this, "data gagal dihapus", Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e:JSONException){
                    e.printStackTrace()
                }
            },
            {

            }){
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["nama"] = nama.getText().toString();
                params["nim"] = nim.getText().toString();
                return params
            }
        }
        var responsequeue=Volley.newRequestQueue(this)
        responsequeue.add(stringRequest)
    }
}
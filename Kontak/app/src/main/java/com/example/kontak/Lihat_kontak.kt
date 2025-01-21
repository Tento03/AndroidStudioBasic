package com.example.kontak

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kontak.databinding.ActivityLihatKontakBinding
import org.json.JSONException
import org.json.JSONObject

class Lihat_kontak : AppCompatActivity() {

    private lateinit var binding: ActivityLihatKontakBinding
    lateinit var nama:TextView
    lateinit var nim:TextView
    lateinit var hapus:Button;
    lateinit var update:Button;
    lateinit var upnama:EditText;
    lateinit var upnim:EditText;
    lateinit var list:ArrayList<Objek>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLihatKontakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hapus=findViewById(R.id.hapus);
        update=findViewById(R.id.update);

        nama=findViewById(R.id.contentnama)
        nim=findViewById(R.id.contentnim)

        upnama=findViewById(R.id.upnama);
        upnim=findViewById(R.id.upnim);
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        tampil_data()
        hapus.setOnClickListener {
            hapus_data()
            nama.setText("");
            nim.setText("");
            finish();
            tampil_data()
        }
        update.setOnClickListener(){
           update_data();
            finish();
            tampil_data();
        }
    }
    fun tampil_data(){
        var id=intent.getStringExtra("id")
        var url:String="http://192.168.237.125/latihan/view_data.php?id=$id"
        var stringRequest=StringRequest(Request.Method.GET,url,
            {response ->
                try {
                    val jsonObject = JSONObject(response)
                    nama.text = jsonObject.getString("nama")
                    nim.text = jsonObject.getString("nim")
                }
                catch (e:JSONException){
                    e.printStackTrace()
                }
            },
            {

            })
        var responsequeue=Volley.newRequestQueue(this)
        responsequeue.add(stringRequest)
    }
    fun hapus_data(){
        var id=intent.getStringExtra("id")
        var url:String="http://192.168.237.125/latihan/hapus_data.php?id=$id"
        var stringRequest=StringRequest(Request.Method.GET,url,
            {response ->
                try {
                    var jsonobject=JSONObject(response);
                    var jsonarray=jsonobject.getJSONArray("status");
                    var obj=jsonarray.getJSONObject(0);
                    var resp=obj.getString("status");
                    if (resp=="dihapus"){
                        Toast.makeText(this, "berhasil dihapus", Toast.LENGTH_SHORT)
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

            })
        var responsequeue=Volley.newRequestQueue(this)
        responsequeue.add(stringRequest)
    }
    fun update_data(){
            val id = intent.getStringExtra("id")
            val url = "http://192.168.237.125/latihan/update_data.php?id=$id"
            val stringRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener { response ->
                    try {
                        val jsonobject = JSONObject(response)
                        val jsonarray = jsonobject.getJSONArray("status")
                        val obj = jsonarray.getJSONObject(0)
                        val resp = obj.getString("status")
                        if (resp == "sukses") {
                            Toast.makeText(this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Data gagal diupdate", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["nama"] = upnama.text.toString()
                    params["nim"] = upnim.text.toString()
                    return params
                }
            }
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)
        }

    }

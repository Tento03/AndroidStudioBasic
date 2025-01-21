package com.example.kontak

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.sql.Types.NULL

class Cari : AppCompatActivity() {
    lateinit var cari:EditText;
    lateinit var clear:Button;
    lateinit var batal:Button;
    lateinit var listview:ListView;
    lateinit var carinama:Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari)

        batal=findViewById(R.id.batal);
        clear=findViewById(R.id.clear);
        cari=findViewById(R.id.cari);
        listview=findViewById(R.id.listviewcari);
        carinama=findViewById(R.id.carinama);

        clear.setOnClickListener(){
            cari.setText("");
        }
        batal.setOnClickListener(){
            finish();
        }
        cari.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                cari_data(p0.toString());
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
    fun cari_data(cari:String){
        var url="http://192.168.237.125/latihan/cari_data.php?q=$cari";
        var stringreq=StringRequest(Request.Method.GET,url,
            Response.Listener {response ->
//                Toast.makeText(this,response,Toast.LENGTH_SHORT).show();
                try {
                    var jsonobject=JSONObject(response);
                    var jsonArray=jsonobject.getJSONArray("hasil");
                    var list=ArrayList<Objek>();
                    for(i in 0 until jsonArray.length()){
                        var objek=jsonArray.getJSONObject(i);
                        var id_mhs:String=objek.getString("id");
                        var nama:String=objek.getString("nama");
                        var nim:String=objek.getString("nim");
                        list.add(Objek(id_mhs,nama,nim));
                    }
                    var adapter=custom_adaptor(this,list);
                    listview.adapter=adapter;
                    listview.setOnItemClickListener { adapterView, view, i, l ->
                        var intent=Intent(this,Lihat_kontak::class.java);
                        intent.putExtra("id",list.get(i).id);
                        startActivity(intent);
                    };
                }
                catch (e:JSONException){

                }
            },
            Response.ErrorListener {error ->
                Toast.makeText(this,"Gagal",Toast.LENGTH_SHORT).show();
            });
        var reqQue=Volley.newRequestQueue(this);
        reqQue.add(stringreq);
    }
}

class custom_adaptor(var context:Context,var list:ArrayList<Objek>):BaseAdapter(){
    var layoutinflater=LayoutInflater.from(context);
    override fun getCount(): Int {
        return list.size;
    }

    override fun getItem(p0: Int): Any {
        return list[p0];
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view=p1;
        if(view==null){
            view=layoutinflater.inflate(R.layout.list_item,p2,false);
        }
        lateinit var nama:TextView;
        lateinit var nim:TextView;
        nama=view!!.findViewById(R.id.hasilnama);
        nim=view!!.findViewById(R.id.hasilnim);
        nama.setText(list[p0].nama);
        nim.setText(list[p0].nim);
        return view;
    }

}
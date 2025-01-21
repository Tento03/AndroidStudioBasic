package com.example.kontak

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var tambahKontak:FloatingActionButton;
    lateinit var buttoncari:Button;
    lateinit var list:ArrayList<Objek>;
    lateinit var listview:ListView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tambahKontak=findViewById(R.id.tambahkontak);
        buttoncari=findViewById(R.id.buttoncari);
        listview=findViewById(R.id.listview);
        tambahKontak.setOnClickListener(){
            var intent=Intent(this,TambahKontak::class.java);
            startActivity(intent);
        }
        buttoncari.setOnClickListener(){
            var intent=Intent(this,Cari::class.java);
            startActivity(intent);
        }
        load_data();

    }
    fun load_data(){
        var url="http://192.168.237.125/latihan/tampil_data.php";
        var stringreq=StringRequest(1,url,
        Response.Listener {response ->
//                Toast.makeText(this,response,Toast.LENGTH_SHORT).show();
            try {
                var jsonobject=JSONObject(response);
                var jsonArray=jsonobject.getJSONArray("hasil");
                var list=ArrayList<Objek>();
                for(i in 0 until jsonArray.length()){
                    var objek=jsonArray.getJSONObject(i);
                    var id:String=objek.getString("id");
                    var nama:String=objek.getString("nama");
                    var nim:String=objek.getString("nim");
                    list.add(Objek(id,nama,nim));
                }
                var adapter=custom_adapter(this,list);
                listview.adapter=adapter;
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

class custom_adapter(var context:Context,var list:ArrayList<Objek>):BaseAdapter(){
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
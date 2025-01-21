package com.example.tes

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
    lateinit var listview:ListView;
    lateinit var kirim:Button;
//    lateinit var list:ArrayList<Objek>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listview=findViewById(R.id.listview);
        kirim=findViewById(R.id.hasil);
        kirim.setOnClickListener(){
            hubungi_server();
        }

}
    fun hubungi_server(){
        var url="http://192.168.237.125/latihan/tampil_data.php";
        var request=StringRequest(1,url,
          Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val jsonArray = jsonObject.getJSONArray("hasil")
                    var list = ArrayList<Objek>()
                    for (i in 0 until jsonArray.length()) {
                        val getdata = jsonArray.getJSONObject(i)
                        val nama = getdata.getString("nama")
                        val nim = getdata.getString("nim")
                        list.add(Objek(nama,nim));
                    }
//                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
//                    listview.adapter = adapter
                    val adapter=custom_adapter(this,list);
                    listview.adapter=adapter;
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
//        Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
            },
            Response.ErrorListener{ error->
               // Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }

        )
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    };
}

class custom_adapter(var context:Context,var list:ArrayList<Objek>):BaseAdapter(){
    var layoutInflater=LayoutInflater.from(context);
    override fun getCount(): Int {
        return list.size
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
            view=layoutInflater.inflate(R.layout.list_item,p2,false);
        }
        lateinit var nama:TextView;
        lateinit var nim:TextView;

        nama=view!!.findViewById(R.id.nama);
        nim=view!!.findViewById(R.id.nim);
        nama.setText(list[p0].nama);
        nim.setText(list[p0].nim);
        return view;
    }

}
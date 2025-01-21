package com.example.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button hasil;
    List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        hasil=findViewById(R.id.hasil);
        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hubungi_server();
            }
        });
    }
    private void hubungi_server(){
        String url=Global.base_url;
        StringRequest request=new StringRequest(
                1, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("hasil");
                    list=new ArrayList<String>();
                    for(int i=0;i < jsonArray.length();i++){
                        JSONObject getdata=jsonArray.getJSONObject(i);
                        String nama=getdata.getString("nama");
                        String nim=getdata.getString("nim");
                        list.add(nama+"\n"+nim);
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }
}
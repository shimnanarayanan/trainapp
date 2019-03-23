package com.example.task3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Trainroute extends AppCompatActivity {

    EditText ed;
    Button bt;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainroute);
        ed=findViewById(R.id.editText1);
        bt=findViewById(R.id.button1);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Train Route");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String trainname= ed.getText().toString();

               // Log.d("date->", date);

                url="https://api.railwayapi.com/v2/route/train/"+trainname+"/apikey//4rio5zmxpa";

                Log.d("url data",url);

                if ( trainname.equals("")) {
                    Toast.makeText(Trainroute.this, "Field Required", Toast.LENGTH_SHORT).show();

                }
                else {
                }

                StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    //JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = new JSONObject(response);
                                    //String name = jsonObject.getString("name");


                                    Toast.makeText(Trainroute.this, response, Toast.LENGTH_LONG).show();



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Trainroute.this,"error", Toast.LENGTH_SHORT).show();


                        error.printStackTrace();
                        // requestQueue.stop();



                    }}


                );

                Mysingleton.getInstance(Trainroute.this).addToRequestQueue(stringRequest);

            }
        });








    }
}

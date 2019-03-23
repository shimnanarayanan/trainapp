package com.example.task3;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Cancelledtrain extends AppCompatActivity {

    TextView tx1;
    Button bt;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelledtrain);

        tx1=findViewById(R.id.textView1);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Cancelled Trains");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-7);
        final Date result = calendar.getTime();
        final int d = calendar.get(Calendar.DAY_OF_MONTH);
        final int m = calendar.get(Calendar.MONTH);
        final int y = calendar.get(Calendar.YEAR);


        tx1=findViewById(R.id.textView1);
        bt=findViewById(R.id.button);

        tx1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerDialog pickerDialog = new DatePickerDialog(Cancelledtrain.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String finalDate = getDate(year,month,date);
                        tx1.setText(finalDate);



                    }
                }, y, m, d);

                pickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()+24*60*60*1000);
                pickerDialog.getDatePicker().setMinDate(result.getTime());
                pickerDialog.show();





            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String date= tx1.getText().toString();

                Log.d("date->", date);

                url="https://api.railwayapi.com/v2/cancelled/date/"+date+"/apikey/4rio5zmxpa";

                Log.d("url data",url);

                if ( date.equals("")) {
                    Toast.makeText(Cancelledtrain.this, "Field Required", Toast.LENGTH_SHORT).show();

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


                                    Toast.makeText(Cancelledtrain.this, response, Toast.LENGTH_LONG).show();



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Cancelledtrain.this,"error", Toast.LENGTH_SHORT).show();


                        error.printStackTrace();
                        // requestQueue.stop();



                    }}


                );

                Mysingleton.getInstance(Cancelledtrain.this).addToRequestQueue(stringRequest);

            }
        });


    }





    public String getDate( int year, int month, int date)
    {

        String date_1="",month_1="",final_date="";


        if(date<10)
        {
            date_1 = "0"+date;
        }
        else
        {
            date_1 = ""+date;
        }

        if((month+1) <10)
        {
            month_1 = "0"+(month+1);
        }
        else
        {
            month_1 = ""+(month+1);
        }

        final_date = date_1+"-"+month_1+"-"+year;

        return final_date;
    }

}


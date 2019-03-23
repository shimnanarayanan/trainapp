package com.example.task3;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Seatavailability extends AppCompatActivity  {

    EditText ed1,ed2,ed3;
    Button b1;
    TextView tx;
    String url;
    private RecyclerView.LayoutManager rlm;


    private List<String> list;
    private RecyclerAdapter2 adapter;
    private RecyclerView rc2;
    private RecyclerView.LayoutManager rlm2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatavailability);

        ed1=findViewById(R.id.editText1);
        ed2=findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText3);
        b1=findViewById(R.id.button1);
        tx=findViewById(R.id.textView2);

        rc2=findViewById(R.id.recyclerview);
        rlm2=new LinearLayoutManager(this);
        rc2.setLayoutManager(rlm2);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Seat Availability");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-7);
        final Date result = calendar.getTime();
        final int d = calendar.get(Calendar.DAY_OF_MONTH);
        final int m = calendar.get(Calendar.MONTH);
        final int y = calendar.get(Calendar.YEAR);

        final Spinner spinner1 = findViewById(R.id.spinner1);
        final Spinner spinner2 = findViewById(R.id.spinner2);



        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Seatavailability.this, R.array.Class, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);


        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(Seatavailability.this, spinner1.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        ArrayAdapter<CharSequence> adapter2= ArrayAdapter.createFromResource(Seatavailability.this, R.array.quota, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Toast.makeText(Seatavailability.this, spinner2.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });



        tx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerDialog pickerDialog = new DatePickerDialog(Seatavailability.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String finalDate = getDate(year,month,date);
                        tx.setText(finalDate);

                    }
                }, y, m, d);

                pickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()+24*60*60*1000);
                pickerDialog.getDatePicker().setMinDate(result.getTime());
                pickerDialog.show();


            }
        });





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String trainname = ed1.getText().toString();
                String source= ed2.getText().toString();
                String destination= ed3.getText().toString();
                String date=tx.getText().toString();
                String spinner= spinner1.getSelectedItem().toString();
                String spinner1=spinner2.getSelectedItem().toString();


                Log.d("date->",date);

                url="https://api.railwayapi.com/v2/check-seat/train/"+trainname+"/source/"+source+"/dest/"+destination+"/date/"+date+"/pref/"+spinner+"/quota/"+spinner1+"/apikey/4rio5zmxpa";

                Log.d("url data",url);

                if (trainname.equals("") || source.equals("") || date.equals("")  || destination.equals("") || date.equals("")|| spinner.equals("")|| spinner1.equals("")) {
                    Toast.makeText(Seatavailability.this, "Field Required", Toast.LENGTH_SHORT).show();

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


                                   // Toast.makeText(Seatavailability.this, response, Toast.LENGTH_LONG).show();

//                                    adapter=new RecyclerAdapter2(response);
//
//                                    rc2.setHasFixedSize(true);
//                                    rc2.setAdapter(adapter);

                                    String trainname,date;
                                    trainname=jsonObject.getString("trainname");
                                    date=jsonObject.getString("date");
//


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Seatavailability.this,"error", Toast.LENGTH_SHORT).show();


                        error.printStackTrace();
                        // requestQueue.stop();



                    }}


                );

                Mysingleton.getInstance(Seatavailability.this).addToRequestQueue(stringRequest);

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




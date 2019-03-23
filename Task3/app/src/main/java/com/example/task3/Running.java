package com.example.task3;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


public class Running extends AppCompatActivity {
    EditText ed1,ed2;
    Button bt1;
    TextView tx;
     String url;
    ActionBar actionBar;
    Toolbar toolbar;
    CardView cv;
    TextView tx1,tx2,tx3;
    TextView tx4,tx5,tx6,tx7,tx8,tx9,tx10;



    private GridLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Running Status");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-7);
        final Date result = calendar.getTime();
        final int d = calendar.get(Calendar.DAY_OF_MONTH);
        final int m = calendar.get(Calendar.MONTH);
        final int y = calendar.get(Calendar.YEAR);

        tx=findViewById(R.id.textView2);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        bt1=findViewById(R.id.button);
        cv=findViewById(R.id.cardView2);

        tx1=findViewById(R.id.stationname);
        tx2=findViewById(R.id.stationcode);
        tx3=findViewById(R.id.position);

        tx4=findViewById(R.id.mon1);
        tx5=findViewById(R.id.tue1);
        tx6=findViewById(R.id.wed1);
        tx7=findViewById(R.id.thu1);
        tx8=findViewById(R.id.fri1);
        tx9=findViewById(R.id.sat1);
        tx10=findViewById(R.id.sun1);

//




        tx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerDialog pickerDialog = new DatePickerDialog(Running.this, new DatePickerDialog.OnDateSetListener() {
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


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String trainname = ed1.getText().toString();
                final String stationcode= ed2.getText().toString();
                String date= tx.getText().toString();

                Log.d("date->",date);

                url="https://api.railwayapi.com/v2/live/train/"+trainname+"/station/"+stationcode+"/date/"+date+"/apikey/4rio5zmxpa";

                Log.d("url data",url);

                if (trainname.equals("") || stationcode.equals("") || date.equals("")) {
                    Toast.makeText(Running.this, "Field Required", Toast.LENGTH_SHORT).show();

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

                                    JSONObject train = jsonObject.getJSONObject("train");
                                    JSONArray days = train.getJSONArray("days");


                                    for( int i=0;i<days.length();i++)
//
                                    {

                                        JSONObject jsonObject1 = days.getJSONObject(i);
                                       String code=jsonObject1.getString("code");

                                        String status = null;
                                        switch (code)

                                        {


                                          case "MON":
                                              String run = jsonObject1.getString("runs");

                                              status = checkmon(run);
                                              tx4.setText(status);


                                            case "TUE":
                                                String run1 = jsonObject1.getString("runs");

                                                status = checktues(run1);
                                                tx5.setText(status);

                                            case "WED":
                                                String run2 = jsonObject1.getString("runs");

                                                status = checkwed(run2);
                                                tx6.setText(status);

                                            case "THU":
                                                String run3 = jsonObject1.getString("runs");

                                                status = checkthu(run3);
                                                tx7.setText(status);

                                            case "FRI":
                                                String run4 = jsonObject1.getString("runs");

                                                status = checkfri(run4);
                                                tx8.setText(status);

                                            case "SAT":
                                                String run5 = jsonObject1.getString("runs");

                                                status = checksat(run5);
                                                tx9.setText(status);


                                            case "SUN":
                                                String run6 = jsonObject1.getString("runs");

                                                status = checksun(run6);
                                                tx10.setText(status);




                                        }
                                        }





//
//                                    tx5.setText(code);
//                                    tx6.setText(code);
//                                    tx7.setText(code);
//                                    tx8.setText(code);
//                                    tx9.setText(code);
//                                    tx10.setText(code);


                                   // String name=station.getString("name");
                                   // String code=station.getString("code");

                                  // String position=jsonObject.getString("position");

//                                    tx1.setText(name);
//                                    tx2.setText(code);
//                                    tx3.setText(position);

                                   // JSONArray days=train.getJSONArray("days");


//                              String run = null;
//                              String code = null;
                              //int length=days.length();

//                              if(days.length()==6)
//                              {
//                                  JSONObject jsonObject1 = days.getJSONObject(6);
//                                  run=jsonObject1.getString("run");
//                                  code=jsonObject1.getString("code");
//                              }

//                                    for( int i=0;i<days.length();i++)
//
//                                    {
//
//                                        JSONObject jsonObject1 = days.getJSONObject(i);
//
//                                        code = jsonObject1.getString("code");
//
//
//                                             if(code.equals("SUN")) {
//
//                                                 run = jsonObject1.getString("run");
//
//
//                                                 //  run = jsonObject1.getString("run");
//
//                                                 Toast.makeText(Running.this, run, Toast.LENGTH_LONG).show();
//                                             }
//                                             else{
//                                        Toast.makeText(Running.this,"not sunday", Toast.LENGTH_LONG).show();
//                                    }
//
//                                    }
//
                                    
                                    
                                   // String name = jsonObject.getString("name");
                                // jsonObject.getJSONObject("station").getString("name");

                                   // String name =  jsonObject.getJSONObject("station").getString("name");


                                        //String num= jsonObject.getJSONObject("train").getString("days");

                                      // String name=jsonObject.getJSONObject("train").getString("name");
                                      // String name=jsonObject.getJSONObject("train").getString("name");







                                  //  Toast.makeText(Running.this,"not sunday", Toast.LENGTH_LONG).show();


//                                    Intent i = new Intent(getApplicationContext(), Result1.class);
//                                    i.putExtra("Key",response);
//                                    startActivity(i);









//







                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Running.this,"error", Toast.LENGTH_SHORT).show();


                        error.printStackTrace();
                        // requestQueue.stop();



                    }}


                );

                Mysingleton.getInstance(Running.this).addToRequestQueue(stringRequest);

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

public String checkmon(String run)
{

String status;

    if(run.equals("Y"))
    {

        status="YES";
    }
    else{
        status="NO";
    }

return status;

}

    public String checktues(String run1)
    {

        String status;

        if(run1.equals("Y"))
        {

            status="YES";
        }
        else{
            status="NO";
        }

        return status;

    }



    public String checkwed(String run2)
    {

        String status;

        if(run2.equals("Y"))
        {

            status="YES";
        }
        else{
            status="NO";
        }

        return status;

    }

    public String checkthu(String run3)
    {

        String status;

        if(run3.equals("Y"))
        {

            status="YES";
        }
        else{
            status="NO";
        }

        return status;

    }

    public String checkfri(String run4)
    {

        String status;

        if(run4.equals("Y"))
        {

            status="YES";
        }
        else{
            status="NO";
        }

        return status;

    }

    public String checksat(String run5)
    {

        String status;

        if(run5.equals("Y"))
        {

            status="YES";
        }
        else{
            status="NO";
        }

        return status;

    }

    public String checksun(String run6)
    {

        String status;

        if(run6.equals("Y"))
        {

            status="YES";
        }
        else{
            status="NO";
        }

        return status;

    }
    }
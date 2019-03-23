package com.example.task3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Trainarrivals extends AppCompatActivity {

    EditText ed1;
    Button b1;
    TextView tx;
    RadioGroup rg;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainarrivals);
        ed1 = findViewById(R.id.editText);
        //rb1=findViewById(R.id.radio);
        rg=findViewById(R.id.radioGroup);
        b1=findViewById(R.id.button1);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Train Arrivals");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        tx = findViewById(R.id.textView5);
        tx.setEnabled(false);

        //final String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();



        rg.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

               // final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
//                RadioButton rb= (RadioButton) findViewById(checkedId);
//                final String hours= rb.getText().toString();
                final String hours = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();


                Toast.makeText(getBaseContext(), hours, Toast.LENGTH_SHORT).show();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stationcode = ed1.getText().toString();
                //String rb=rg.getCheckedRadioButtonId().
              int hours = rg.getCheckedRadioButtonId();
                //String hours2= String.valueOf(hours);
               //int hours2= Integer.parseInt(String.valueOf(hours));



                Log.d("hours->", String.valueOf(hours));

                url = "https://api.railwayapi.com/v2/arrivals/station/"+stationcode+"/hours/"+String.valueOf(hours)+"/apikey/4rio5zmxpa";

                Log.d("url data", url);

                if (stationcode.equals("") ) {
                    Toast.makeText(Trainarrivals.this, "Field Required", Toast.LENGTH_SHORT).show();

                } else {
                }

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    //JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = new JSONObject(response);
                                    //String name = jsonObject.getString("name");


                                    Toast.makeText(Trainarrivals.this, response, Toast.LENGTH_LONG).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Trainarrivals.this, "error", Toast.LENGTH_SHORT).show();


                        error.printStackTrace();
                        // requestQueue.stop();


                    }
                }


                );

                Mysingleton.getInstance(Trainarrivals.this).addToRequestQueue(stringRequest);

            }
        });


    }
}


//    private void selecthours(View view) {
//
//
//
//        boolean checked=((RadioButton) view).isChecked();
//            switch (view.getId()) {
//
//
//                case R.id.radio:
//                    if (checked) {
//
//                        tx.setText("2 selected");
//                        tx.setEnabled(true);
//
//                    } else {
//                        tx.setEnabled(false);
//                    }
//                    break;
//                case R.id.radio2:
//                    if (checked) {
//
//                        tx.setText("4 selected");
//                        tx.setEnabled(true);
//
//                    } else {
//
//                        tx.setEnabled(false);
//                    }
//                    break;
//
//            }
//        final String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
//                        .getText().toString();
//
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }




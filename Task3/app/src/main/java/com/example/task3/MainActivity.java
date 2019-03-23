package com.example.task3;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView rc;
    private GridLayoutManager layoutManager;

    private String nameList[]={

            "running status","Seat Availability","Cancelled Train","Rescheduled Train","Train Route","Train Arrivals"
    };
    private int IconId[]={

                            R.drawable.live,R.drawable.pnr,R.drawable.ct,R.drawable.rt,R.drawable.tr,R.drawable.ta

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView=findViewById(R.id.navigationview);
        drawerLayout=findViewById(R.id.drawable_layout);
        rc=findViewById(R.id.recyclerview);

        layoutManager=new GridLayoutManager(MainActivity.this,2);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(layoutManager);

        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(MainActivity.this,IconId, nameList);
        rc.setAdapter(recyclerAdapter);





        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        item.setChecked(true);
                        displaymessage("home selected");
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_share:
                        item.setChecked(true);
                        displaymessage("share selected");
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_rateus:
                        item.setChecked(true);
                        displaymessage("rate us selected");
                        drawerLayout.closeDrawers();
                        return true;


                }
                return false;
            }
        });


        final ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menunav);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.appmenubar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Toast.makeText(this, "share selected", Toast.LENGTH_LONG).show();
                break;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                return true;
            // default:
        }
                return super.onOptionsItemSelected(item);


    }

    private void displaymessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }




    }


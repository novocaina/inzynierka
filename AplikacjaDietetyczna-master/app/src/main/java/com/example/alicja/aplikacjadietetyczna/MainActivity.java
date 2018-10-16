package com.example.alicja.aplikacjadietetyczna;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout linearLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    drawerLayout=(DrawerLayout)findViewById(R.id.drawer_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     linearLayout=findViewById(R.id.linear_layout);
      setSingleEvent(linearLayout);
    }

    private void setSingleEvent(LinearLayout linearLayout) {
        for(int i=0;i<linearLayout.getChildCount();i++) {
            CardView cardView=(CardView)linearLayout.getChildAt(i);
            final int finall=i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(finall==0)
                    {
                        Intent intent=new Intent(MainActivity.this,Parameters.class);
                        startActivity(intent);
                    }
                    if(finall==1)
                    {
                        Intent intent=new Intent(MainActivity.this,DietInfo.class);
                        startActivity(intent);
                    }
                    if(finall==4)
                    {
                        Intent intent=new Intent(MainActivity.this,FoodListActivity.class);
                        startActivity(intent);
                    }
                    if(finall==5)
                    {
                        Intent intent=new Intent(MainActivity.this,CateringActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.nav_stat:
                startActivity(new Intent(MainActivity.this,Statistics.class));
                drawerLayout.closeDrawers();
                return true;
            case R.id.nav_param:
                startActivity(new Intent(MainActivity.this,Parameters.class));
                drawerLayout.closeDrawers();
                return true;
                case R.id.nav_list:
                 startActivity(new Intent(MainActivity.this,FoodListActivity.class));
                 drawerLayout.closeDrawers();
             return true;
            case R.id.nav_map:
                startActivity(new Intent(MainActivity.this,CateringActivity.class));
                return true;}
return false;
}
}

package com.example.alicja.aplikacjadietetyczna;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodListActivity extends AppCompatActivity {
    ListItemHelper listHelper;
    ArrayAdapter<String> adapter;
    @BindView(R.id.shop_list)
    ListView listItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        ButterKnife.bind(this);

        listHelper =new ListItemHelper(this);
        loadItemList();
    }

    private void loadItemList() {
        ArrayList<String> itemList=listHelper.getItemsList();
        if(adapter==null){
            adapter=new ArrayAdapter<String>(this,R.layout.items,R.id.item_title,itemList);
            listItemView.setAdapter(adapter);
        }
        else {
            adapter.clear();
            adapter.addAll(itemList);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.item_menu,menu);
        Drawable icon=menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_item:
                final EditText itemEditText=new EditText(this);
                AlertDialog dialog=new AlertDialog.Builder(this)
                        .setTitle(R.string.add_new_title)
                        .setMessage(R.string.add_new)
                        .setView(itemEditText)
                        .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                String item=String.valueOf(itemEditText.getText());
                                listHelper.insertItem(item);
                                loadItemList();
                            }
                        })
                        .setNegativeButton(R.string.cancel,null)
                        .create();
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
public void deleteItem(View view){
        View parent=(View)view.getParent();
        TextView itemTextView=(TextView)findViewById(R.id.item_title);
    String item=String.valueOf(itemTextView.getText());
    listHelper.deleteItem(item);
    loadItemList();
}
}

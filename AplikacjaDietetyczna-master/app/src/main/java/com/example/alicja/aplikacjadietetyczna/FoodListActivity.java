package com.example.alicja.aplikacjadietetyczna;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    DatabaseHelper listHelper;
    ArrayAdapter<String> adapter;
    @BindView(R.id.shop_list)
    ListView listItemView;
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        ButterKnife.bind(this);

        listHelper =new DatabaseHelper(this);
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
    @OnClick(R.id.floating_action_button)
    void OnClick() {
        final EditText itemEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.add_new_title)
                .setMessage(R.string.add_new)
                .setView(itemEditText)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String item = String.valueOf(itemEditText.getText());
                        listHelper.insertItem(item);
                        loadItemList();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
        dialog.show();
    }

public void deleteItem(View view){
        View parent=(View)view.getParent();
        TextView itemTextView=(TextView)findViewById(R.id.item_title);
    String item=String.valueOf(itemTextView.getText());
    listHelper.deleteItem(item);
    loadItemList();
}
}

package com.example.alicja.aplikacjadietetyczna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Alicja on 2018-06-10.
 */

public class ListItemHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "DataList";
    private final static int DB_VER = 1;
    private final static String DB_TABLE1 = "ShopList";
    private final static String DB_COLUMN_ITEM = "ItemName";

    public ListItemHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);", DB_TABLE1, DB_COLUMN_ITEM);
        db.execSQL(query);
        db.execSQL(User.CREATE_TABLE);
        db.execSQL(DailyMeal.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DailyMeal.TABLE2);
        onCreate(db);

    }

    public void insertItem(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN_ITEM, task);
        db.insertWithOnConflict(DB_TABLE1, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteItem(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE1, DB_COLUMN_ITEM + " = ?", new String[]{task});
        db.close();
    }

    public ArrayList<String> getItemsList() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE1, new String[]{DB_COLUMN_ITEM}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DB_COLUMN_ITEM);
            taskList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }

    public void insertUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.CPM, user.getCpm());
        values.put(User.GOAL, user.getGoal());
        values.put(User.PREFERENCE, user.getPrefer());
        db.insert(User.TABLE, null, values);
        db.close();
    }

    public void updateUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.CPM, user.getCpm());
        values.put(User.GOAL, user.getGoal());
        values.put(User.PREFERENCE, user.getPrefer());
        db.insert(User.TABLE, null, values);


        db.update(User.TABLE, values, User.ID + " = ?",
                new String[]{"1"});
    }

    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + User.TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public User getUser() {
        String Query = "SELECT * FROM " + User.TABLE + " WHERE " + User.ID + " = " + 1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        cursor.moveToFirst();

        User user = new User();
        user.setCpm(cursor.getDouble(cursor.getColumnIndex(User.CPM)));
        user.setGoal(cursor.getString(cursor.getColumnIndex(User.GOAL)));
        user.setPrefer(cursor.getString(cursor.getColumnIndex(User.PREFERENCE)));

        cursor.close();
        db.close();
        return user;

    }

    public void insertMeal(DailyMeal meal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DailyMeal.NAME, meal.getName());
        values.put(DailyMeal.CALORIES, meal.getCalories());
        db.insert(DailyMeal.TABLE2, null, values);
        db.close();
    }
    public int getMealCount() {
        String countQuery = "SELECT  * FROM " + DailyMeal.TABLE2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public DailyMeal getMeal(int id) {
        String Query = "SELECT * FROM " + DailyMeal.TABLE2 + " WHERE " + DailyMeal.ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        cursor.moveToFirst();

        DailyMeal meal=new DailyMeal();
        meal.setName(cursor.getString(cursor.getColumnIndex(DailyMeal.NAME)));
        meal.setCalories(cursor.getDouble(cursor.getColumnIndex(DailyMeal.CALORIES)));


        cursor.close();
        db.close();
        return meal;

    }
}
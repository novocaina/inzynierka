package com.example.alicja.aplikacjadietetyczna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alicja.aplikacjadietetyczna.Objects.DailyMeal;
import com.example.alicja.aplikacjadietetyczna.Objects.User;
import com.example.alicja.aplikacjadietetyczna.Objects.XYValue;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Alicja on 2018-06-10.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "DataList";
    private final static int DB_VER = 1;
    private final static String DB_TABLE1 = "ShopList";
    private final static String DB_COLUMN_ITEM = "ItemName";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);", DB_TABLE1, DB_COLUMN_ITEM);
        db.execSQL(query);
        db.execSQL(User.CREATE_TABLE);
        db.execSQL(DailyMeal.CREATE_TABLE);
        db.execSQL(DailyMeal.CREATE_MEAL_TABLE);
        db.execSQL(XYValue.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + XYValue.TABLE3);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DailyMeal.TABLE2);
        db.execSQL("DROP TABLE IF EXISTS " + DailyMeal.TABLE4);
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
        values.put(User.WEIGHT, user.getWeight());
        values.put(User.HEIGHT, user.getHeight());
        values.put(User.GOAL, user.getGoal());
        values.put(User.PREFERENCE, user.getPrefer());
        values.put(User.SEX, user.getSex());
        values.put(User.ACTIVITY, user.getActivity());
        values.put(User.AGE, user.getAge());
        values.put(User.PREFERENCE, user.getPrefer());
        values.put(User.ELIMINATION, user.getElimination());
        db.insert(User.TABLE, null, values);
        db.close();
    }

    public void updateUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.CPM, user.getCpm());
        values.put(User.WEIGHT, user.getWeight());
        values.put(User.HEIGHT, user.getHeight());
        values.put(User.GOAL, user.getGoal());
        values.put(User.PREFERENCE, user.getPrefer());
        values.put(User.SEX, user.getSex());
        values.put(User.ACTIVITY, user.getActivity());
        values.put(User.AGE, user.getAge());
        values.put(User.PREFERENCE, user.getPrefer());
        values.put(User.ELIMINATION, user.getElimination());
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
        user.setSex(cursor.getString(cursor.getColumnIndex(User.SEX)));
        user.setAge(cursor.getInt(cursor.getColumnIndex(User.AGE)));
        user.setHeight(cursor.getDouble(cursor.getColumnIndex(User.HEIGHT)));
        user.setWeight(cursor.getDouble(cursor.getColumnIndex(User.WEIGHT)));
        user.setActivity(cursor.getDouble(cursor.getColumnIndex(User.ACTIVITY)));
        user.setElimination(cursor.getString(cursor.getColumnIndex(User.ELIMINATION)));
        cursor.close();
        db.close();
        return user;

    }

    public void insertMeal(DailyMeal meal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DailyMeal.NAME, meal.getName());
        values.put(DailyMeal.CALORIES, meal.getCalories());
        values.put(DailyMeal.PROTEINS, meal.getProteins());
        values.put(DailyMeal.CARBOHYDRATES, meal.getCarbohydrates());
        values.put(DailyMeal.FAT, meal.getFat());
        values.put(DailyMeal.PREPARE, meal.getPrepare());
        values.put(DailyMeal.INGREDIENTS, meal.getIngredients());
        values.put(DailyMeal.PORTIONS, meal.getPortions());
        values.put(DailyMeal.URL, meal.getUrl());
        values.put(DailyMeal.IMAGE, meal.getImageUrl());
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

        DailyMeal meal = new DailyMeal();
        meal.setName(cursor.getString(cursor.getColumnIndex(DailyMeal.NAME)));
        meal.setCalories(cursor.getDouble(cursor.getColumnIndex(DailyMeal.CALORIES)));
        meal.setProteins(cursor.getDouble(cursor.getColumnIndex(DailyMeal.PROTEINS)));
        meal.setCarbohydrates(cursor.getDouble(cursor.getColumnIndex(DailyMeal.CARBOHYDRATES)));
        meal.setFat(cursor.getDouble(cursor.getColumnIndex(DailyMeal.FAT)));
        meal.setPrepare(cursor.getString(cursor.getColumnIndex(DailyMeal.PREPARE)));
        meal.setIngredients(cursor.getString(cursor.getColumnIndex(DailyMeal.INGREDIENTS)));
        meal.setPortions(cursor.getString(cursor.getColumnIndex(DailyMeal.PORTIONS)));
        meal.setUrl(cursor.getString(cursor.getColumnIndex(DailyMeal.URL)));
        meal.setImageUrl(cursor.getString(cursor.getColumnIndex(DailyMeal.IMAGE)));

        cursor.close();
        db.close();
        return meal;

    }

    public void updateMeal(DailyMeal meal, int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DailyMeal.NAME, meal.getName());
        values.put(DailyMeal.CALORIES, meal.getCalories());
        values.put(DailyMeal.PROTEINS, meal.getProteins());
        values.put(DailyMeal.CARBOHYDRATES, meal.getCarbohydrates());
        values.put(DailyMeal.FAT, meal.getFat());
        values.put(DailyMeal.PREPARE, meal.getPrepare());
        values.put(DailyMeal.INGREDIENTS, meal.getIngredients());
        values.put(DailyMeal.PORTIONS, meal.getPortions());
        values.put(DailyMeal.URL, meal.getUrl());
        values.put(DailyMeal.IMAGE, meal.getImageUrl());
        db.insert(DailyMeal.TABLE2, null, values);

        db.update(DailyMeal.TABLE2, values, DailyMeal.ID + "=" + id, null);
    }

    public void insertXYValues(XYValue xyValue) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(XYValue.XNAME, xyValue.getX());
        values.put(XYValue.YNAME, xyValue.getY());
        db.insert(XYValue.TABLE3, null, values);
        db.close();
    }


    public XYValue getXYValue(int id) {
        String Query = "SELECT * FROM " + XYValue.TABLE3 + " WHERE " + XYValue.ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        cursor.moveToFirst();

        XYValue xyValue = new XYValue();
        xyValue.setX(cursor.getLong(cursor.getColumnIndex(XYValue.XNAME)));
        xyValue.setY(cursor.getDouble(cursor.getColumnIndex(XYValue.YNAME)));
        cursor.close();
        db.close();
        return xyValue;

    }

    public int getXYValuesCount() {
        String countQuery = "SELECT  * FROM " + XYValue.TABLE3;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void insertUserMeal(DailyMeal meal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DailyMeal.MEAL_NAME, meal.getName());
        values.put(DailyMeal.MEAL_CALORIES, meal.getCalories());
        values.put(DailyMeal.MEAL_PROTEINS, meal.getProteins());
        values.put(DailyMeal.MEAL_CARBOHYDRATES, meal.getCarbohydrates());
        values.put(DailyMeal.MEAL_FAT, meal.getFat());
        values.put(DailyMeal.MEAL_PREPARE, meal.getPrepare());
        values.put(DailyMeal.MEAL_INGREDIENTS, meal.getIngredients());
        values.put(DailyMeal.MEAL_PORTIONS, meal.getPortions());
        values.put(DailyMeal.MEAL_URL, meal.getUrl());
        values.put(DailyMeal.MEAL_IMAGE, meal.getImageUrl());
        db.insert(DailyMeal.TABLE4, null, values);
        db.close();
    }

    public int getUserMealCount() {
        String countQuery = "SELECT  * FROM " + DailyMeal.TABLE4;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public DailyMeal getUserMeal(int id) {
        String Query = "SELECT * FROM " + DailyMeal.TABLE4 + " WHERE " + DailyMeal.MEAL_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        cursor.moveToFirst();

        DailyMeal meal = new DailyMeal();
        meal.setName(cursor.getString(cursor.getColumnIndex(DailyMeal.MEAL_NAME)));
        meal.setCalories(cursor.getDouble(cursor.getColumnIndex(DailyMeal.MEAL_CALORIES)));
        meal.setProteins(cursor.getDouble(cursor.getColumnIndex(DailyMeal.MEAL_PROTEINS)));
        meal.setCarbohydrates(cursor.getDouble(cursor.getColumnIndex(DailyMeal.MEAL_CARBOHYDRATES)));
        meal.setFat(cursor.getDouble(cursor.getColumnIndex(DailyMeal.MEAL_FAT)));
        meal.setPrepare(cursor.getString(cursor.getColumnIndex(DailyMeal.MEAL_PREPARE)));
        meal.setIngredients(cursor.getString(cursor.getColumnIndex(DailyMeal.MEAL_INGREDIENTS)));
        meal.setPortions(cursor.getString(cursor.getColumnIndex(DailyMeal.MEAL_PORTIONS)));
        meal.setUrl(cursor.getString(cursor.getColumnIndex(DailyMeal.MEAL_URL)));
        meal.setImageUrl(cursor.getString(cursor.getColumnIndex(DailyMeal.MEAL_IMAGE)));

        cursor.close();
        db.close();
        return meal;

    }


}
package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


public class JSONHelper {


      public static String readJSONFromAsset(Context context) {
          String json = null;
          try {
              InputStream is = context.getAssets().open("database.json");
              int size = is.available();
              byte[] buffer = new byte[size];
              is.read(buffer);
              is.close();
              json = new String(buffer, "UTF-8");
          } catch (IOException ex) {
              ex.printStackTrace();
              return null;
          }
          return json;
      }

      public static void addObjectToJSON(String path,String name, String calories,String type, String kind, String ingredients, String recipe, String url, String proteins, String carbohydrates, String fat,String portions) throws JSONException, IOException {
          JSONObject jsonObject = new JSONObject(path);
          JSONArray meals = jsonObject.getJSONArray("Baza");
          JSONObject jsonObj= new JSONObject();

          jsonObj.put("Nazwa", name);
          jsonObj.put("Kalorie", calories);
          jsonObj.put("Typ", type);
          jsonObj.put("Rodzaj", kind);
          jsonObj.put("Składniki", ingredients);
          jsonObj.put("Przepis", recipe);
          jsonObj.put("Porcje", portions);
          jsonObj.put("Źródło", url);
          jsonObj.put("Białka", proteins);
          jsonObj.put("Weglowodany", carbohydrates);
          jsonObj.put("Tłuszcze", fat);
          meals.put(jsonObj);
          FileWriter file = new FileWriter("database.json");
          file.write(meals.toString());
          file.flush();
          file.close();


      }
  }

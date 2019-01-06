package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;


import java.io.IOException;
import java.io.InputStream;



public class JSONHelper {


      public static String readJSONFromAsset(Context context) {
          String json;

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


  }

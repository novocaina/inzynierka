package com.example.alicja.aplikacjadietetyczna.Objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class XYValue {
    public XYValue(){}
  private double y;
    private long x;

    public double getY() {
        return y;
    }

    public XYValue(long x, double y) {
        this.y = y;
        this.x = x;
    }

    public void setY(double y) {

        this.y = y;
    }
public double returnOdds(ArrayList<XYValue> xyValues, int i){
        if(i>0) {
            return xyValues.get(i).getY() - xyValues.get(i - 1).getY();
        }
        else
            return 0;
}
    public static String getDate(long milliSeconds, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public static final String TABLE3 = "series";
    public static final String ID = "id";
    public static final String XNAME = "xName";
    public static final String YNAME = "yName";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE3 + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    XNAME + " NUMBER, " +
                    YNAME + " NUMBER " +
                    "); ";
}

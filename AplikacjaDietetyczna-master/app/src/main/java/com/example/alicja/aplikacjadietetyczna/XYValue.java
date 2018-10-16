package com.example.alicja.aplikacjadietetyczna;

class XYValue {
    public XYValue(){}
    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public XYValue(String x, String y) {
        this.x = x;
        this.y = y;
    }

    private String x,y;

    public static final String TABLE3 = "values ";
    public static final String ID = "id";
    public static final String XNAME = "xName";
    public static final String YNAME = "yName";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE3 + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    XNAME + " INTEGER, " +
                    YNAME + " NUMBER " +
                    "); ";
}

package com.example.alicja.aplikacjadietetyczna;

class XYValue {
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public XYValue(double x, double y) {
        this.x = x;
        this.y = y;
    }
    private double x,y;
}

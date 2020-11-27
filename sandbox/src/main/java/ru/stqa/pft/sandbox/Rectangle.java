package ru.stqa.pft.sandbox;

public class Rectangle {
    public double len;
    public double wid;

    public Rectangle(double len, double wid) {
        this.len = len;
        this.wid = wid;
    }

    public double area() {
        return len *wid;
    }
}
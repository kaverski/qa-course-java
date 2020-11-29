package ru.stqa.pft.sandbox;

public class PointDemo {
    public static void main(String[] args) {
        Point point1 = new Point(5,3);
        Point point2 = new Point(10,12);
        System.out.println(point1.distance(point2));
    }
}
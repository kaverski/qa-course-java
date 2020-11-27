package ru.stqa.pft.sandbox;

public class PointTest {
    public static void main(String[] args) {
        Point point1 = new Point(5,3);
        Point point2 = new Point(10,12);
        //function
        System.out.println(Point.distance(point1, point2));

        //method
        System.out.println(point1.distance(point2));
    }
}
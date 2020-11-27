package ru.stqa.pft.sandbox;

public class Second {
    public static void main(String[] args) {
        String somebody = "world";
        System.out.println("Hello Java training");
        System.out.println();

        Square s = new Square(5);
        System.out.println(s.area());

        Rectangle r = new Rectangle(4,5);
        System.out.println(r.area());
    }
}
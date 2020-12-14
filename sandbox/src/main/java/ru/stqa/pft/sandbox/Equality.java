package ru.stqa.pft.sandbox;

public class Equality {
    public static void main(String[] args) {
        String s1 = "firefox";
        String s2 = new String(s1);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        String s3 = "chrome";
        String s4 = "chrome";
        System.out.println(s3 == s4);
    }
}

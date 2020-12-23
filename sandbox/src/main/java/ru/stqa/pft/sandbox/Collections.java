package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "PHP"};

        List<String> languages = new ArrayList<>(List.of(langs));
        List<String> languages1 = new ArrayList<>(Arrays.asList(langs));
        List<String> languages2 = Arrays.asList(langs);

        System.out.println(languages);
        System.out.println(languages1);
        System.out.println(languages2);

        for (String lang : languages2) {
            System.out.println("Я хочу выучить " + lang);
        }
        //
        for (int i = 0; i < languages.size(); i++) {
            System.out.println(languages.get(i));
        }

        //произвольного типа список
        List languages3 = Arrays.asList(langs);
        for (Object lang : languages3) {
            System.out.println(lang);
        }
    }
}
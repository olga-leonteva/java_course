package ru.stqa.course.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by leonto on 3/17/2016.
 */
public class Collections {

  public static void main(String[] args) {
    String [] langs = {"Java", "C#", "Python", "PHP"};

    List<String> language = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : language){
      System.out.println("I want to learn " + l);
    }
  }
}

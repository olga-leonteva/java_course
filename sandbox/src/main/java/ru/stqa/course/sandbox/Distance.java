package ru.stqa.course.sandbox;

/**
 * Created by leonto on 2/25/2016.
 */
public class Distance {

  public static void main(String[] args){
    Point p =new Point();

    Point p1 = new Point(1,2);


    Point p2 = new Point(3,4);


    System.out.println("First point p1 has coordinates (" + p1.a + ";" + p1.b + ")" );
    System.out.println("Second point p2 has coordinates (" + p2.a + ";" + p2.b + ")" );
    System.out.println("Distance between p1 and p2 = " + p.distance(p1, p2));
  }


}

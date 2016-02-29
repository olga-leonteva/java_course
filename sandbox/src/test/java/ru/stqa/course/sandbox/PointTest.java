package ru.stqa.course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by leonto on 2/26/2016.
 */
public class PointTest {

  @Test
  public void testDistance(){
    Point p1 = new Point(1,2);
    Point p2 = new Point(3,4);
    Point p = new Point();
    Assert.assertEquals(p.distance(p1,p2), 2.8284271247461903);
  }

  @Test
  public void testDistance2(){
      Point p1 = new Point(0,0);
      Point p2 = new Point(0,0);
      Point p = new Point();
      Assert.assertNotEquals(p.distance(p1,p2),2.8284271247461903);
  }
}

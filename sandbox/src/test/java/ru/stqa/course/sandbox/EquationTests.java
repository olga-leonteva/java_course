package ru.stqa.course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by leonto on 3/8/2016.
 */
public class EquationTests {

  @Test
  public void test0() {
    Equation e = new Equation(1,1,1);
    Assert.assertEquals(e.rootNumber(), 0);
  }

  @Test
  public void test1() {
    Equation e = new Equation(1,2,1);
    Assert.assertEquals(e.rootNumber(), 1);
  }

  @Test
  public void test2() {
    Equation e = new Equation(1,5,6);
    Assert.assertEquals(e.rootNumber(), 2);
  }
}


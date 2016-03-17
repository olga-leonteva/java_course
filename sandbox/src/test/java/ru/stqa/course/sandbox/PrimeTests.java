package ru.stqa.course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by leonto on 3/17/2016.
 */
public class PrimeTests {
  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test (enabled = false)
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

    @Test
    public void testNonPrimes(){
      Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }


  }

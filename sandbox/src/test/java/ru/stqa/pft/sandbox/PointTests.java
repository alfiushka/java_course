package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class PointTests {
    SoftAssert softAssert = new SoftAssert();
    @Test
    public void testPoint () {
        Point p = new Point(2,3);
        Point p2 = new Point(3,4);


        softAssert.assertEquals(p.distance(p2), 1.4142135623730951, "Проверка вычисления расстояния между точками");
        softAssert.assertEquals(Math.pow(p2.x - p.x, 2), 2, "Проверка возведения в квадрат разницы x координат");
        softAssert.assertEquals(Math.pow(p2.y- p.y, 2), 2, "Проверка возведения в квадрат разницы y координат");
        softAssert.assertAll();
    }
}

package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class PointTests {
    @Test
    public void testPoint () {
        Point p = new Point(2,3);
        Point p2 = new Point(3,4);
        Assert.assertEquals(p.distance(p2), 1.4142135623730951, "Проверка вычисления расстояния между точками");
    }

    @Test
    public void test2Point () {
        Point p = new Point(3,5);
        Point p2 = new Point(6,7);
        Assert.assertEquals(p.distance(p2), 3.605551275463989, "Проверка вычисления расстояния между точками");
    }

    @Test
    public void test3Point () {
        Point p = new Point(3.5,4);
        Point p2 = new Point(6.2,7.3);
        Assert.assertEquals(p.distance(p2), 4.263801121065568, "Проверка вычисления расстояния между точками");
    }
}

package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;



public class PointTests {

    @Test
    public void testPoint () {
        Point p = new Point(2,3);
        Point p2 = new Point(3,4);

        Assert.assertEquals(p.distance(p2), 1.4142135623730951);
        Assert.assertNotEquals(p.distance(p2), 2);
    }
}

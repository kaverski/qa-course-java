package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point point1 = new Point(5,2);
        Point point2 = new Point(10,12);
        Assert.assertEquals(point1.distance(point2), 11.180339887498949);
    }
}
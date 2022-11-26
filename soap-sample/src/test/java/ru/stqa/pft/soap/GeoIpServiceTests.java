package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("176.232.57.79");
        assertEquals(geoIp, "<GeoIP><Country>TR</Country><State>34</State></GeoIP>");
        System.out.println(geoIp);
    }

}

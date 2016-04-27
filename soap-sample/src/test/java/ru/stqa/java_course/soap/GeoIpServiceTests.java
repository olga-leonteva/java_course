package ru.stqa.java_course.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by leonto on 4/27/2016.
 */
public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("152.62.109.208");
        assertEquals(geoIP.getCountryCode(), "USA");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("152.62.109.xxx");
        assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
    }
}

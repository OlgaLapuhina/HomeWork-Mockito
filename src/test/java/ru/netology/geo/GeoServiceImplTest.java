package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Location;

@DisplayName("Проверить работу метода public Location byIp(String ip)")
public class GeoServiceImplTest {
    private GeoServiceImpl geoServiceImpl;
    private Location location;

    @Test
    void byIp() {
        geoServiceImpl = new GeoServiceImpl();
        location = geoServiceImpl.byIp(GeoServiceImpl.MOSCOW_IP);
        String expected = location.toString();
        String actual = "MoscowRUSSIALenina15";

        Assertions.assertEquals(expected, actual);
    }
}


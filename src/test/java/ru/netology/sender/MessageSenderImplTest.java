package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";

    @Mock
    private GeoService geoService;
    @Mock
    private LocalizationService localizationService;

    private MessageSenderImpl messageSenderImpl;

    @BeforeEach
    void setUp() {
        messageSenderImpl = new MessageSenderImpl(geoService, localizationService);

    }

    @DisplayName("MessageSenderImpl всегда отправляет только русский текст, " +
            "если ip относится к российскому сегменту адресов")
    @Test
    void sendRus() {
        Location location = new Location("Krasnodar", Country.RUSSIA, null, 0);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP);

        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(geoService.byIp(Mockito.eq(MOSCOW_IP))).thenReturn(location);

        String actual = messageSenderImpl.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, actual);
    }
    @DisplayName("MessageSenderImpl всегда отправляет только английский текст," +
            " если ip относится к американскому сегменту адресов")
    @Test
    void sendEng() {
        Location location = new Location("Chicago", Country.USA, null, 0);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, NEW_YORK_IP);

        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(geoService.byIp(Mockito.eq(NEW_YORK_IP))).thenReturn(location);

        String actual = messageSenderImpl.send(headers);
        String expected = "Welcome";
        Assertions.assertEquals(expected, actual);
    }
}
package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

@DisplayName("Проверить работу метода public String locale(Country country)")
public class LocalizationServiceImplTest {

    @Test
    void locale() {
        LocalizationServiceImpl localizationServiceImpl = new LocalizationServiceImpl();
        String expected = "Welcome";
        String actual = localizationServiceImpl.locale(Country.GERMANY);

        Assertions.assertEquals(expected, actual);
    }
}

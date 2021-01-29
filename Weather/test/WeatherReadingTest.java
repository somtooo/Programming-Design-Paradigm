import static org.junit.Assert.assertEquals;

import org.junit.Test;
import weather.WeatherReading;

/**
 * Weather ReadingsTest to test Weather Reading Class.
 */

public class WeatherReadingTest {
    private final WeatherReading weather = new WeatherReading(20.00, 10.00, 30.00, 40.00);

    /**
     * Calculates various weather readings in terms of recorded temperature, dewPoint windSpeed and
     * totalRain.
     */

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDewPoint() {
        new WeatherReading(10.00, 20.00, 30.00, 40.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidWindSpeed() {
        new WeatherReading(20.00, 10.00, -30.00, 40.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTotalRain() {
        new WeatherReading(20.00, 10.00, 30.00, -40.00);
    }

    @Test

    public void relativeHumidityTest() {
        assertEquals(50, weather.getRelativeHumidity());
    }

    @Test

    public void getTemperatureTest() {
        assertEquals(20, weather.getTemperature());
    }

    @Test

    public void getDewPointTest() {
        assertEquals(10, weather.getDewPoint());
    }

    @Test

    public void getWindSpeedTest() {
        assertEquals(30, weather.getWindSpeed());
    }

    @Test

    public void getTotalRainTest() {
        assertEquals(40, weather.getTotalRain());
    }

    @Test

    public void getHeatIndexTest() {
        assertEquals(25, weather.getHeatIndex());
    }

    @Test

    public void getWindChill() {
        assertEquals(66, weather.getWindChill());
    }

    @Test

    public void toStringTest() {
        assertEquals("Reading: T = 20, D = 10, v = 30, rain = 40",
                weather.toString());
    }


}
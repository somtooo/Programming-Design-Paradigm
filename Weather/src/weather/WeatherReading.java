package weather;

/**
 * Weather Readings represented as temperature, dewPoint, windSeed and totalRain.
 */
public final class WeatherReading {

    private final double airTemperature;
    private final double dewPoint;
    private final double windSpeed;
    private final double totalRain;

    /**
     * Calculates various weather readings in terms of recorded temperature, dewPoint windSpeed and
     * totalRain.
     *
     * @param airTemperature temperature in Celsius.
     * @param dewPoint temperature in Celsius.
     * @param windSpeed the speed in miles per hour.
     * @param totalRain the amount of rain in millimeters.
     * @throws IllegalArgumentException if any argument is negative.
     */


    public WeatherReading(final double airTemperature, final double dewPoint, final double windSpeed,
                          final double totalRain)throws IllegalArgumentException {

        if (dewPoint > airTemperature) {
            throw new IllegalArgumentException("Dew Point cannot be greater than Air Temperature");
        } else if (windSpeed < 0) {
            throw new IllegalArgumentException("Wind Speed cannot be Negative");
        } else if (totalRain < 0) {
            throw new IllegalArgumentException("Total Rain cannot be Negative");
        }

        this.airTemperature = airTemperature;
        this.dewPoint = dewPoint;
        this.windSpeed = windSpeed;
        this.totalRain = totalRain;

    }

    public int getTemperature() {
        return (int)airTemperature;
    }

    public int getDewPoint() {
        return (int)dewPoint;
    }

    public int getWindSpeed() {
        return (int)windSpeed;
    }

    public int getTotalRain() {
        return (int)totalRain;
    }


    public int getRelativeHumidity() {
        return (int)calculateRelativeHumidity();
    }


    public int getHeatIndex() {
        return (int)calculateHeatIndex();
    }


    public int getWindChill() {
        return (int)calculateWindChill();
    }


    private double calculateRelativeHumidity() {
        return 5 * (dewPoint - airTemperature) + 100;
    }


    private double calculateHeatIndex() {
        final double c1 = -8.78469475556;
        final double c2 = 1.61139411;
        final double c3 = 2.33854883889;
        final double c4 = -0.14611605;
        final double c5 = -0.012308094;
        final double c6 = -0.0164248277778;
        final double c7 = 0.002211732;
        final double c8 = 0.00072546;
        final double c9 = -0.000003582;
        final double relativeHumidity = calculateRelativeHumidity();
        return (c1 + c2 * airTemperature)
                + (c3 * relativeHumidity)
                + (c4 * airTemperature * relativeHumidity)
                + (c5 * Math.pow(airTemperature, 2))
                + (c6 * Math.pow(relativeHumidity, 2))
                + (c7 * Math.pow(airTemperature, 2) * relativeHumidity)
                + (c8 * airTemperature * Math.pow(relativeHumidity, 2))
                + (c9 * Math.pow(airTemperature, 2)
                * Math.pow(relativeHumidity, 2));

    }


    private double calculateWindChill() {
        double newTemperature = toFahrenheit(airTemperature);
        return 35.74 + (0.6215 * newTemperature) - (35.75 * Math.pow(windSpeed, 0.16))
                + (0.4275 * newTemperature * Math.pow(windSpeed, 0.16));
    }

    private int toFahrenheit(double temperature) {
        return (int)(temperature * (9.0 / 5.0) + 32);
    }

    @Override
    public String toString() {
        return String.format("Reading: T = %d, D = %d, v = %d, rain = %d",
                (int)airTemperature, (int)dewPoint, (int)windSpeed, (int)totalRain);

    }



}

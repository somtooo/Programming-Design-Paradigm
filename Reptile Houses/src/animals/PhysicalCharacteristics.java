package animals;

import java.time.temporal.ValueRange;

public class PhysicalCharacteristics {
    private final ValueRange temperature;
    private final String naturalFeature;
    private final String size;

    public PhysicalCharacteristics(String size, ValueRange temperature, String naturalFeature) {
        this.temperature = temperature;
        this.naturalFeature = naturalFeature;
        this.size = size;
    }

    public ValueRange getTemperature() {
        return temperature;
    }

    public String getNaturalFeature() {
        return naturalFeature;
    }

    public String getSize() {
        return size;
    }
}

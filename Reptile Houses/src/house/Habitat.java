package house;
import animals.Animals;
import java.util.ArrayList;

public class Habitat extends AbstractHabitat {

    private final String[] naturalFeatures;
    private  final int size;
    private final String name;
    private ArrayList<Animals> animals;

    public Habitat(String[] naturalFeatures, int size, String name) {
        this.naturalFeatures = naturalFeatures;
        this.size = size;
        this.name = name;
    }

    @Override
    public String[] getNaturalFeatures() {
        return naturalFeatures;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected AbstractHabitat addAnimal(Animals animal, String size) {
        throw new IllegalCallerException();
    }

    @Override
    public String toString() {
        throw new IllegalCallerException();
    }


}

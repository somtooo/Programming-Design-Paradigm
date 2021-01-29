package house;

import animals.Animals;

public abstract class AbstractHabitat {

    public abstract String[] getNaturalFeatures();


    public abstract int getSize();


    public abstract String getName();

    protected abstract AbstractHabitat addAnimal(Animals animal, String size);
}

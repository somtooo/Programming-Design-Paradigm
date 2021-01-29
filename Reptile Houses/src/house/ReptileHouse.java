package house;

import animals.Animals;
import java.util.ArrayList;
import java.util.HashMap;

public class ReptileHouse {
    private final ArrayList<AbstractHabitat> habitat;
    private final int numOfHabitats;
    private ArrayList<Animals> noHome;



    public ReptileHouse(ArrayList<AbstractHabitat> habitat, int numOfHabitats) throws IllegalArgumentException {
        this.habitat = habitat;
        this.numOfHabitats = numOfHabitats;
    }

   public void addAnimalToHabitat(Animals animals){
    throw new IllegalCallerException();
   }

    public String reportNaturalFeatures(){
        throw new IllegalCallerException();
    }

    public String findSpeciesByHabitat(String species){
        throw new IllegalCallerException();
    }

    public void addHabitat(AbstractHabitat habitat){throw new IllegalCallerException();}

    public void printSign(){
        throw new IllegalCallerException();
    }

    public void printMap(){
        throw new IllegalCallerException();
    }
    public void printIndex(){
        throw new IllegalCallerException();
    }

    @Override
    public String toString() {
        throw new IllegalCallerException();
    }
}

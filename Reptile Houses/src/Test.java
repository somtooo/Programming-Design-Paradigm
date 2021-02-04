import animals.Animals;
import animals.HerpetologyAnimals;
import animals.TypeOfSpecies;
import house.Habitat;
import house.AbstractHabitat;

import java.time.temporal.ValueRange;

public class Test {
    public static void main(String[] args) {
////        Habitat habitat = new Habitat(new String[3],5,"Somtoo");
//        AbstractHabitat habitat = new Habitat(new String[3],5,"Somtoo");
//        Species species = new Species("frog", TypeOfSpecies.REPTILE);
//        System.out.println(species.getSpeciesType());
        ValueRange range = ValueRange.of(0,123);
        System.out.println(range);

//        Animals sizeToBigToAddToHabitatAnimal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);


    }
}

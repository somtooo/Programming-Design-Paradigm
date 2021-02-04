import animals.*;
import house.AbstractHabitat;
import house.Habitat;
import house.ReptileHouse;
import org.junit.Before;
import org.junit.Test;

import java.time.temporal.ValueRange;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ReptileHouseTest {
  ReptileHouse reptileHouse;

  Species species = new Species("frog", TypeOfSpecies.AMPHIBIAN, "broken leg");
  PhysicalCharacteristics physicalCharacteristics = new PhysicalCharacteristics(SizeofSpecies.LARGE, ValueRange.of(30, 33), "water");
  PersonalFeatures personalFeatures = new PersonalFeatures(false, Danger.ENDANGERED, true);
  ArrayList<String> features = new ArrayList<String>();
  ArrayList<AbstractHabitat> habitats = new ArrayList<AbstractHabitat>();
  ArrayList<String> featuresWithWater = new ArrayList<String>();


  @Before
  public void setUp() throws Exception {
    features.add("water");
    features.add("pond");
    featuresWithWater.add("water");
    featuresWithWater.add("lilly");
    AbstractHabitat habitat = new Habitat(features, 10, "Habitat A");
    AbstractHabitat secondHabitat = new Habitat(featuresWithWater, 10, "Habitat B");
    habitats.add(habitat);
    habitats.add(secondHabitat);
    reptileHouse = new ReptileHouse(habitats, 3);
  }

  @Test
  public void testConstructor() {
    assertEquals(habitats, reptileHouse.getHabitats());
    assertEquals(3, reptileHouse.getNumOfHabitats());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNegativeValue() {
    new ReptileHouse(habitats, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNumberOfHabitatsInArray() {
    new ReptileHouse(habitats, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalArrayLength() {
    ArrayList<AbstractHabitat> emptyHabitats = new ArrayList<AbstractHabitat>();
    new ReptileHouse(emptyHabitats, 3);
  }

  @Test
  public void addAnimalToHabitat() {
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(true, reptileHouse.addAnimalToHabitat(animal));
    assertEquals(false,reptileHouse.addAnimalToHabitat(animal));
  }

  @Test
  public void reportNaturalFeatures() {
    assertEquals("ggtdd",reptileHouse.reportNaturalFeatures());
  }

  @Test
  public void findSpeciesByHabitat() {
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    assertEquals(true, reptileHouse.addAnimalToHabitat(animal));
    assertEquals("hhyyy", reptileHouse.findSpeciesByHabitat("frog"));
  }

  @Test
  public void addHabitat() {
    AbstractHabitat habitatToAdd = new Habitat(features, 20, "Habitat C");
    reptileHouse.addHabitat(habitatToAdd);
    assertEquals(3,reptileHouse.getHabitats().size());

  }

  @Test
  public void printSign() {
//    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
//    reptileHouse.addAnimalToHabitat(animal);
//    Animals animal2 = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
//    reptileHouse.addAnimalToHabitat(animal2);
    assertEquals(true,reptileHouse.printSign());
  }

  @Test
  public void printMap() {
        Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal);
    Animals animal2 = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal2);
    assertEquals(true,reptileHouse.printMap());
  }

  @Test
  public void printIndex() {
    Animals animal = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal);
    Animals animal2 = new HerpetologyAnimals(species, physicalCharacteristics, personalFeatures);
    reptileHouse.addAnimalToHabitat(animal2);
    assertEquals(true, reptileHouse.printIndex());
  }

  @Test
  public void testToString() {
    assertEquals("There are currently 0 Animals not in any Habitat",reptileHouse.toString());
  }
}

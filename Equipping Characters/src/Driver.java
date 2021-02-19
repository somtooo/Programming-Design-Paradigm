
import character.Character;
import character.CharacterInterFace;
import wearable.Wearable;
import wearable.footWear.FootWear;
import wearable.footWear.FootWearName;
import wearable.handGear.HandGear;
import wearable.handGear.HandGearName;
import wearable.headGear.HeadGear;
import wearable.headGear.HeadGearName;
import wearable.jewelry.Jewelry;
import wearable.jewelry.JewelryName;
import java.util.*;

public class Driver {
    private final int numberOfHeadGear;
    private final int numberOfFootWear;
    private final int numberOfHandGear;
    private final int numberOfJewelry;
    private final List<Wearable> chest;
    private final Wearable handGearDummy;
    private final Wearable headGearDummy;
    private  final Wearable footWearDummy;
    private final Wearable jewelryDummy;
    private int numberOfTimesEquipped;

    public Driver(int numberOfHeadGear, int numberOfFootWear, int numberOfHandGear, int numberOfJewelry) {
        this.numberOfHeadGear = numberOfHeadGear;
        this.numberOfFootWear = numberOfFootWear;
        this.numberOfHandGear = numberOfHandGear;
        this.numberOfJewelry = numberOfJewelry;
        footWearDummy = new FootWear(FootWearName.BOOTS, "of height",false,20);
        headGearDummy = new HeadGear(HeadGearName.HATS, "of love", false, 100);
        jewelryDummy =  new Jewelry(JewelryName.AMULET, "of flight", false, 100, 50);
        handGearDummy = new HandGear(HandGearName.GLOVE, "of flight", false, 100,40);
        chest = new ArrayList<Wearable>();

    }

    public void buildChest() {
        List<Boolean> wearsOut = new ArrayList<Boolean>();
        wearsOut.add(true);
        wearsOut.add(false);
        List<HeadGearName> headGearNames = new ArrayList<HeadGearName>(Arrays.asList(HeadGearName.values()));
        List<String> descriptions = new ArrayList<String>();
        descriptions.add("of Flight");
        descriptions.add("of Electricity");
        descriptions.add("of Electrical Shock");
        descriptions.add("of Speed");
        descriptions.add("of Heft");
        descriptions.add("of Swimming");
        descriptions.add("of Drunkenness");
        descriptions.add("of Invisibility");
        descriptions.add("of Sight");
        Random random = new Random();


        List<FootWearName> footWearNames = new ArrayList<FootWearName>(Arrays.asList(FootWearName.values()));
        for (int i = 0; i< numberOfFootWear; i++) {
            int footWearNamesIndex = random.nextInt((footWearNames.size()-1) + 1) ;
            int descriptionsIndex = random.nextInt((descriptions.size()-1) + 1);
            int wearsOutIndex = random.nextInt((1 + 1));

            Wearable footWearWearable = new FootWear(footWearNames.get(footWearNamesIndex),descriptions.get(descriptionsIndex),
                    wearsOut.get(wearsOutIndex),random.nextInt((10-(-2)) + 1) + (-2));
            chest.add(footWearWearable);
        }

        for (int i = 0; i< numberOfHeadGear; i++) {
            int headGearNamesIndex = random.nextInt((headGearNames.size()-1) + 1) ;
            int descriptionsIndex = random.nextInt((descriptions.size()-1) + 1);
            int wearsOutIndex = random.nextInt((1 + 1));
            Wearable headGearWearable = new HeadGear(headGearNames.get(headGearNamesIndex),descriptions.get(descriptionsIndex),
                    wearsOut.get(wearsOutIndex),random.nextInt((10-(-2)) + 1) + (-2));
            chest.add(headGearWearable);
        }

        List<JewelryName> jewelryNames = new ArrayList<JewelryName>(Arrays.asList(JewelryName.values()));
        for (int i = 0; i< numberOfJewelry; i++) {
            int jewelryNamesIndex = random.nextInt((jewelryNames.size()-1) + 1) ;
            int descriptionsIndex = random.nextInt((descriptions.size()-1) + 1);
            int wearsOutIndex = random.nextInt((1 + 1));

            Wearable jewelryWearable = new Jewelry(jewelryNames.get(jewelryNamesIndex),descriptions.get(descriptionsIndex),
                    wearsOut.get(wearsOutIndex),random.nextInt((10-(-2)) + 1) + (-2),
                    random.nextInt((10-(-2)) + 1) + (-2));
            chest.add(jewelryWearable);
        }

        List<HandGearName> handGearNames = new ArrayList<HandGearName>(Arrays.asList(HandGearName.values()));
        for (int i = 0; i< numberOfHandGear; i++) {
            int handGearNamesIndex = random.nextInt((handGearNames.size()-1) + 1) ;
            int descriptionsIndex = random.nextInt((descriptions.size()-1) + 1);
            int wearsOutIndex = random.nextInt((1 + 1));

            Wearable handGearWearable = new HandGear(handGearNames.get(handGearNamesIndex),descriptions.get(descriptionsIndex),
                    wearsOut.get(wearsOutIndex),random.nextInt((10-(-2)) + 1) + (-2),
                    random.nextInt((10-(-2)) + 1) + (-2));
            chest.add(handGearWearable);
        }


    }



    public void equipCharacter(CharacterInterFace character) {
        if (numberOfTimesEquipped > 0 ) {
            character.wearOutWearableItems();
        }
        Random random = new Random();
        for (int index = 0; index < 20; index++) {
            int randomItemIndex = random.nextInt(((chest.size() - 1)));
            findCorrespondingClassAndAddGiveCharacter(chest.get(randomItemIndex), character);
        }
        numberOfTimesEquipped++;
    }


    private void findCorrespondingClassAndAddGiveCharacter(Wearable wearable, CharacterInterFace character) {
        if (headGearDummy.equals(wearable)) {
            character.addToHeadSlot(wearable);
        } else if (footWearDummy.equals(wearable)) {
            character.addToFeetSlot(wearable);
        } else if (handGearDummy.equals(wearable)) {
            character.addToHandSlot(wearable);
        } else if (jewelryDummy.equals(wearable)) {
            character.addToNeckSlot(wearable);
        }
    }

    public void predict(CharacterInterFace firstCharacter, CharacterInterFace secondCharacter) {
        int firstCharacterDamage = secondCharacter.getAttackPower() - firstCharacter.getDefensePower();
        int secondCharacterDamage = firstCharacter.getAttackPower() - secondCharacter.getDefensePower();
        if (firstCharacterDamage < 1) {
            firstCharacterDamage = 1;
        } else if (secondCharacterDamage < 1) {
            secondCharacterDamage = 1;
        }
        int firstCharacterRound = firstCharacter.getHitPoints()/firstCharacterDamage;
        int secondCharacterRound = secondCharacter.getHitPoints()/secondCharacterDamage;
        if (firstCharacterRound == secondCharacterRound) {
            System.out.println(" This match is a tie");
        } else if (firstCharacterRound > secondCharacterRound) {
            System.out.printf("%s has won the fight it took %s rounds%n",firstCharacter.getName(),firstCharacterRound);
        } else {
            System.out.printf("%s has won the fight it took %s number of rounds%n",secondCharacter.getName(),secondCharacterRound);
        }
    }


    public static void main(String[] args) {

        Driver driver = new Driver(3,3,3,3);
        driver.buildChest();
        Character character = new Character("John",50,50);
        Character character1 = new Character("Abdul",50,50);
        driver.equipCharacter(character);
        driver.equipCharacter(character1);
        character.calculateTotalAttackAndDefense();
        character1.calculateTotalAttackAndDefense();
        System.out.println(character.toString());
        System.out.println(character1.toString());
        driver.predict(character,character1);

    }



}

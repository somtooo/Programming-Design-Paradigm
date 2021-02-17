package controller;


import wearable.*;
import wearable.footWear.FootWear;
import wearable.footWear.FootWearName;
import wearable.handGear.HandGear;
import wearable.handGear.HandGearName;
import wearable.headGear.HeadGear;
import wearable.headGear.HeadGearName;
import wearable.jewelry.Jewelry;
import wearable.jewelry.JewelryName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ControllerInterface {
    private final List<Wearable> chest;
    //    private final int totalAttack;
//    private final int totalDefense;

    public ControllerInterface() {
        chest = new ArrayList<Wearable>();
    }

    public void buildChest(int numberOfHeadGear, int numberOfFootwear, int numberOfHandGear, int numberOfJewelry ) {
        List<Boolean> wearsOut = new ArrayList<Boolean>();
        wearsOut.add(true);
        wearsOut.add(false);
        List<HeadGearName> headGearNames = new ArrayList<HeadGearName>(Arrays.asList(HeadGearName.values()));
        List<String> descriptions = new ArrayList<String>();
        descriptions.add("Flight");
        descriptions.add("Electricity");
        descriptions.add("Electrical Shock");
        descriptions.add("Speed");
        descriptions.add("Heft");
        descriptions.add("Swimming");
        descriptions.add("Drunkeness");
        descriptions.add("Invisibility");
        descriptions.add("Sight");
        Random random = new Random();

        for (int i = 0; i< numberOfHeadGear; i++) {
            int headGearNamesIndex = random.nextInt((headGearNames.size()-1) + 1) ;
            int descriptionsIndex = random.nextInt((descriptions.size()-1) + 1);
            int wearsOutIndex = random.nextInt((1 + 1));
            Wearable headGearWearable = new HeadGear(headGearNames.get(headGearNamesIndex),descriptions.get(descriptionsIndex),
                    wearsOut.get(wearsOutIndex),random.nextInt((10-(-2)) + 1) + (-2));
            chest.add(headGearWearable);
        }
        List<FootWearName> footWearNames = new ArrayList<FootWearName>(Arrays.asList(FootWearName.values()));
        for (int i = 0; i< numberOfFootwear; i++) {
            int footWearNamesIndex = random.nextInt((footWearNames.size()-1) + 1) ;
            int descriptionsIndex = random.nextInt((descriptions.size()-1) + 1);
            int wearsOutIndex = random.nextInt((1 + 1));

            Wearable footWearWearable = new FootWear(footWearNames.get(footWearNamesIndex),descriptions.get(descriptionsIndex),
                    wearsOut.get(wearsOutIndex),random.nextInt((10-(-2)) + 1) + (-2));
            chest.add(footWearWearable);
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




    }

    public List<Wearable> getChest() {
        return new ArrayList<Wearable>(chest);
    }





}

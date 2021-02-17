import controller.ControllerInterface;
import wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        ControllerInterface c = new ControllerInterface();
        c.buildChest(3,3,3,3);
        List<Wearable> wears= new ArrayList<Wearable>();
        wears = c.getChest();
        wears.remove(2);
        System.out.println(c.getChest().size());
    }



}

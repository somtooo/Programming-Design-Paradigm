package wearable;

public  abstract  class AbstractAttackDefense extends AbstractWearable implements AttackWearable, DefenceWearable {

    private final int attackPower;
    private final int defensePower;
    /**
     * Initializes the head gear with the required parameters.
     *
     * @param description the description of the head gear.
     * @param wearsOut    if the item wears out after use or not.
     * @param attackPower
     * @param defensePower
     * @throws IllegalArgumentException if description is empty.
     */
    protected AbstractAttackDefense(String description, Boolean wearsOut, int attackPower, int defensePower) throws IllegalArgumentException {
        super(description, wearsOut);
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }


    @Override
    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public int getDefensePower() {
        return defensePower;
    }
}

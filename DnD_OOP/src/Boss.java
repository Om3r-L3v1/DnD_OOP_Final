public class Boss extends Monster implements HeroicUnit {
    private int abilityFreq;
    private int combatTicks;

    public Boss(int x, int y, char tile, String name, int healthPool, int healthAmount, int attack, int defence,
                int expValue, int visionRange, int abilityFreq, int combatTicks) {
        super(x, y, tile, name, healthPool, healthAmount, attack, defence, expValue, visionRange,level);
        this.abilityFreq = abilityFreq;
        this.combatTicks = 0;
    }

    @Override
    public void castAbility() {

    }
}

public class Boss extends Monster implements HeroicUnit {
    private int abilityFreq;
    private int combatTicks;
    private String abilityName;

    public Boss(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence,
                int expValue, int visionRange, int abilityFreq) {
        super(x, y, tile, name, currentLevel, healthPool, healthAmount, attack, defence, expValue, visionRange);
        this.abilityFreq = abilityFreq;
        this.combatTicks = 0;
    }

    @Override
    public void castAbility() {

    }
}

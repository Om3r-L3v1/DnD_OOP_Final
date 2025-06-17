public class Boss extends Monster {
    private Integer abilityFreq;
    private Integer combatTicks;

    public Boss(Integer x, Integer y, char tile, String name, Integer healthPool, Integer healthAmount, Integer attack, Integer defence,
                Integer expValue, Integer visionRange, Integer abilityFreq, Level level) {
        super(x, y, tile, name, healthPool, healthAmount, attack, defence, expValue, visionRange,level);
        this.abilityFreq = abilityFreq;
        this.combatTicks = 0;
    }
}

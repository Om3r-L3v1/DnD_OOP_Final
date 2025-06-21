public class Rouge extends Player {

    private final String RogueAbility = "Fan of Knives";
    private final int AbilityRange = 2;
    private final int EnergyMax = 100;
    private int cost;
    private int currentEnergy;

    public Rouge(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int cost) {
        super(x, y, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.cost = cost;
        this.currentEnergy = EnergyMax;
        this.abilityName = RogueAbility;
    }

    @Override
    protected boolean canCast() {
        if (currentEnergy < cost) {
            return false;
        }
        return true;
    }

    @Override
    public void castAbility() {
        if (canCast()) {
            currentEnergy -= cost;
            for (Enemy e : currentlevel.getEnemies()) {
                if (this.getRange(e) < AbilityRange) {
                    e.defend(this, attack);
                }
            }
        }
    }
    @Override
    public void gameTick() {
        currentEnergy = Math.min(EnergyMax, currentEnergy + 10);
    }

    @Override
    protected void levelUp(){
        super.levelUp();
        currentEnergy = EnergyMax;
        attack += 3 * level;
    }

}


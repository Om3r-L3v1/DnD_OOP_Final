public class Rouge extends Player {
    private static final String ROGUE_ABILITY = "Fan of Knives";
    private static final int ABILITY_RANGE = 2;
    private static final int ENERGY_MAX = 100;
    private static final int ENERGY_REGEN = 10;
    private static final int ATTACK_EXTRA_GAIN = 3;

    private int cost;
    private int currentEnergy;

    public Rouge(int x, int y, String name, Board board, int healthPool, int healthAmount, int attack, int defence, int cost) {
        super(x, y, name, board, healthPool, healthAmount, attack, defence);
        this.cost = cost;
        this.currentEnergy = getEnergyMax();
    }

    private int getEnergyMax(){return ENERGY_MAX;}
    @Override
    protected int getAttackGain(){return ATTACK_GAIN + ATTACK_EXTRA_GAIN;}

    @Override
    protected boolean canCast() {
        if (currentEnergy < cost) {
            cantCastMsg(String.format("but there was not enough energy: %d/%d.",currentEnergy, cost));
            return false;
        }
        return true;
    }
    public String description(){
        return super.description()+String.format("Energy: %d/%d\t",currentEnergy,getEnergyMax());
    }
    @Override
    public String getAbilityName(){
        return ROGUE_ABILITY;
    }

    @Override
    public void cast(){
        onCastMsg(null);
        currentEnergy -= cost;
        for (Enemy e : board.getEnemies()) {
            if (this.getRange(e) < ABILITY_RANGE) {
                e.defend(this, attack, ABILITY_CALLBACK);
            }
        }
    }

    @Override
    public void gameTick() {
        currentEnergy = Math.min(ENERGY_MAX, currentEnergy + ENERGY_REGEN);
    }

    @Override
    protected void levelUp(){
        super.levelUp();
        currentEnergy = getEnergyMax();
    }

    @Override
    public void onCastMsg(String targetName) {
        callBack.send(String.format("%s cast %s.",getName(),this.getAbilityName()));
    }
    @Override
    public void cantCastMsg(String reason) {
        callBack.send(String.format("%s tried to cast %s, but %s", getName(), getAbilityName(), reason));
    }
}


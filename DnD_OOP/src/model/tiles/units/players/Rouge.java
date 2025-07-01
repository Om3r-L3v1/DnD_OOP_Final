package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

public class Rouge extends Player {
    private static final String ROGUE_ABILITY = "Fan of Knives";
    private static final int ABILITY_RANGE = 2;
    private static final int ENERGY_MAX = 100;
    private static final int ENERGY_REGEN = 10;
    private static final int ATTACK_EXTRA_GAIN = 3;

    private int cost;
    private int currentEnergy;

    public Rouge(String name,int healthPool, int attack, int defence, int cost, Color color) {
        super(name, healthPool, attack, defence,color);
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
    @Override
    public String description(){
        return super.description()+String.format("Energy: %d/%d\tEnergy Cost: %d",currentEnergy,getEnergyMax(), cost);
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
                e.defend(this, attack, abilityCallback);
            }
        }
    }

    @Override
    public void gameTick() {
        if(currentEnergy < ENERGY_MAX){
            currentEnergy = Math.min(ENERGY_MAX, currentEnergy + ENERGY_REGEN);
            chargeEnergyMsg(ENERGY_REGEN);
        }
    }

    @Override
    protected void levelUp(){
        super.levelUp();
        currentEnergy = getEnergyMax();
        fullEnergyMsg();
    }

    @Override
    public void onCastMsg(String targetName) {
        displayCallBack.send(String.format("%s cast %s for %d energy.",getName(),this.getAbilityName(), cost));
    }
    @Override
    protected void cantCastMsg(String reason) {
        displayCallBack.send(String.format("%s tried to cast %s, but %s", getName(), getAbilityName(), reason));
    }
    private void chargeEnergyMsg(int amount){
        displayCallBack.send(String.format("%s regained %d energy.", getName(), amount));
        if(currentEnergy == ENERGY_MAX)
            fullEnergyMsg();
    }
    private void fullEnergyMsg(){
        displayCallBack.send(String.format("%s has full energy!.", getName()));
    }
}


package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hunter extends Player {
    private static final String HUNTER_ABILITY = "Shoot";
    private static final int ATTACK_EXTRA_GAIN = 2;
    private static final int DEFENCE_EXTRA_GAIN = 1;
    private static final int RESTOCK_TICKS = 10;
    private static final int ARROWS_RESTOCK = 1;
    private static final int ARROWS_LEVELUP = 10;

    private int range;
    private int arrowsCount;
    private int ticksCount;

    public Hunter(String name, int healthPool, int attack, int defence,
    int range,Color color) {
        super(name, healthPool, attack, defence,color);
        this.range = range;
        this.arrowsCount = 10 * level;
        this.ticksCount = 0;
    }

    @Override
    protected int getAttackGain(){return ATTACK_GAIN + ATTACK_EXTRA_GAIN;}
    @Override
    protected int getDefenceGain(){return DEFENCE_GAIN + DEFENCE_EXTRA_GAIN;}
    @Override
    public String getAbilityName(){
        return HUNTER_ABILITY;
    }

    @Override
    protected void cast(){
        arrowsCount--;
        Random rnd = new Random();
        List<Enemy> closest = new LinkedList<>();
        int closestRange = range;
        for(Enemy e : board.getEnemies()) {
            int enemyRange = getRange(e);
            if(enemyRange <= range) {
                if(enemyRange == closestRange)
                    closest.add(e);
                else if (enemyRange < closestRange) {
                    closest = new LinkedList<>();
                    closestRange = enemyRange;
                    closest.add(e);
                }
            }
        }
        Enemy target = closest.get(rnd.nextInt(closest.size()));
        onCastMsg(target.getName());
        target.defend(this, attack, abilityCallback);
    }

    @Override
    public String description(){
        return super.description()+String.format("Arrows: %d\tRange: %d\t",arrowsCount,range);
    }
    @Override
    protected boolean canCast(){
        if(arrowsCount == 0){
            cantCastMsg("there are no arrows in the quiver.");
            return false;
        }
        else if(getEnemiesInRange(range, true).isEmpty()){
            cantCastMsg("there were no enemies in range.");
            return false;
        }
        return true;
    }

    @Override
    public void gameTick() {
        if (ticksCount == RESTOCK_TICKS) {
            ticksCount = 0;
            restock(ARROWS_RESTOCK * level);
        }
        else ticksCount++;
    }

    @Override
    protected void levelUp(){
        super.levelUp();
        restock(ARROWS_LEVELUP * level);
    }
    private void restock(int amount){
        arrowsCount += amount;
        restockMsg(amount);
    }
    @Override
    public void onCastMsg(String targetName) {
        displayCallBack.send(String.format("%s fired an arrow at %s.",getName(),targetName));
    }
    @Override
    protected void cantCastMsg(String reason) {
        displayCallBack.send(String.format("%s tried to shoot an arrow, but %s",getName(),reason));
    }
    private void restockMsg(int amount){
        String pluralS = "s";
        if(amount == 1)
            pluralS = "";
        displayCallBack.send(String.format("%s got %d more arrow%s.", getName(), amount,pluralS));
    }
}

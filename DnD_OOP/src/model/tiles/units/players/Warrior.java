package model.tiles.units.players;

import model.tiles.units.enemies.Enemy;

import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    private static final String WARRIOR_ABILITY = "Avenger's Shield";
    private static final int ABILITY_RANGE = 3;
    private static final int HEALTH_POOL_EXTRA_GAIN = 5;
    private static final int ATTACK_EXTRA_GAIN = 2;
    private static final int DEFENCE_EXTRA_GAIN = 1;
    private static final int DEFENCE_HEAL_FACTOR = 10;
    private static final double HEALTH_POOL_DAMAGE_FACTOR = 0.1;

    private int abilityCooldown;
    private int remainingCooldown;

    public Warrior(String name, int healthPool, int attack, int defence, int abilityCooldown, Color color) {
        super(name, healthPool, attack, defence,color);
        this.remainingCooldown = 0;
        this.abilityCooldown = abilityCooldown;
    }

    @Override
    protected int getHealthPoolGain(){return HEALTH_POOL_GAIN + HEALTH_POOL_EXTRA_GAIN;}
    @Override
    protected int getAttackGain(){return ATTACK_GAIN + ATTACK_EXTRA_GAIN;}
    @Override
    protected int getDefenceGain(){return DEFENCE_GAIN + DEFENCE_EXTRA_GAIN;}

    @Override
    public String getAbilityName(){
        return WARRIOR_ABILITY;
    }

    @Override
    protected void cast(){
        onCastMsg(null);
        remainingCooldown = abilityCooldown+1;
        heal(DEFENCE_HEAL_FACTOR * defence);
        List<Enemy> inRangeEnemies = getEnemiesInRange(ABILITY_RANGE, false);
        if(!inRangeEnemies.isEmpty()){
            Random rnd = new Random();
            int index = rnd.nextInt(inRangeEnemies.size());
            Enemy enemy = inRangeEnemies.get(index);
            enemy.defend(this, (int)Math.ceil(healthPool * HEALTH_POOL_DAMAGE_FACTOR), abilityCallback);
        }
    }

    @Override
    public String description(){
        return super.description()+String.format("CoolDown: %d/%d\t",remainingCooldown,abilityCooldown );
    }
    @Override
    public void gameTick(){
        remainingCooldown=Math.max(0,remainingCooldown-1);
    }
    @Override
    protected boolean canCast(){
        if(remainingCooldown > 0){
            cantCastMsg(String.format("there is a cooldown: %d.", remainingCooldown));
            return false;
        }
        return true;
    }


    @Override
    protected void levelUp(){
        super.levelUp();
        remainingCooldown = 0;
    }

    @Override
    public void onCastMsg(String targetName) {
        displayCallBack.send(String.format("%s used %s, healing %d.",getName(),this.getAbilityName(),DEFENCE_HEAL_FACTOR*defence));
    }
    @Override
    protected void cantCastMsg(String reason) {
        displayCallBack.send(String.format("%s tried to cast %s, but %s", getName(), getAbilityName(), reason));
    }

}

package model.tiles.units.players;

import model.tiles.Tile;
import model.tiles.units.*;
import model.tiles.units.enemies.*;
import utils.callbacks.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Player extends Unit implements HeroicUnit {
    public static final char PLAYER_CHAR = '@';
    public static final char PLAYER_DEAD_CHAR = 'X';
    protected static final int LEVEL_UP_EXP = 50;
    protected static final int HEALTH_POOL_GAIN = 10;
    protected static final int ATTACK_GAIN = 4;
    protected static final int DEFENCE_GAIN = 1;

    protected int experience;
    protected int level;

    public Player(String name, int healthPool, int attack, int defence) {
        super(PLAYER_CHAR, name, healthPool, attack, defence);
        this.experience = 0;
        this.level = 1;
    }

    public int getLevel(){return level;}
    protected int getLevelUpExp(){return LEVEL_UP_EXP;}
    protected int getHealthPoolGain(){return HEALTH_POOL_GAIN;}
    protected int getAttackGain(){return ATTACK_GAIN;}
    protected int getDefenceGain(){return DEFENCE_GAIN;}

    @Override
    public void castAbility(){
        if(canCast()){
            cast();
        }
    }

    protected abstract void cast();

    protected abstract boolean canCast();

    @Override
    public boolean canMoveOn(Player p){
        return false;
    }

    @Override
    public boolean canMoveOn(Monster m){
        m.attack(this);
        return false; // model.tiles.units.players.Player will turn into X on death. will never allow move.
    }
    @Override
    protected void takeDamage(int damageTaken, Unit dealer){
        super.takeDamage(damageTaken, dealer);
        if(isDead()){
            tile = PLAYER_DEAD_CHAR;
        }
    }
    @Override
    protected boolean canMoveTo(Tile target){
        return target.canMoveOn(this);
    }
    @Override
    public void defend(Player p, int damage, DamageCallBack dcb){}
    @Override
    public void defend(Enemy m, int damage, DamageCallBack dcb){
        Random rnd = new Random();
        int defence = rnd.nextInt(this.defence+1);
        defenceRollMsg(defence);
        int actualDamage = Math.max(damage - defence, 0);
        dcb.damage(m.getName(), getName(), actualDamage);
        if(actualDamage > 0)
            takeDamage(actualDamage, m);
    }
    @Override
    public void attack(Enemy m){
        onCombatMsg(m);
        Random rnd = new Random();
        int damage = rnd.nextInt(attack+1);
        attackRollMsg(damage);
        m.defend(this, damage, combatCallback);
    }
    @Override
    public void attack(Player p){

    }
    public abstract void gameTick();

    public void gainExperience(int expValue){
        experience += expValue;
        while(experience >= getLevelUpExp() * level){
            levelUp();
        }
    }
    public String description(){
        return super.description()+String.format("Level: %d\tExperience: %d\t",level,experience);
    }

    protected void levelUp(){
        experience -= getLevelUpExp() * level;
        level++;
        healthPool += getHealthPoolGain() * level;
        healthAmount = healthPool;
        attack += getAttackGain() * level;
        defence += getDefenceGain() * level;
        onLevelUpMsg();
    }
    protected String levelUpString(){
        return String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense"
                ,getName(), level, getHealthPoolGain()*level, getAttackGain()*level, getDefenceGain()*level);
    }

    protected List<Enemy> getEnemiesInRange(int range, boolean inclusive){
        List<Enemy> inRangeEnemies = new LinkedList<>();
        for(Enemy e : board.getEnemies()){
            int enemyRange = getRange(e);
            if(enemyRange < range || (inclusive && enemyRange == range)){
                inRangeEnemies.add(e);
            }
        }
        return inRangeEnemies;
    }

    @Override
    protected void onDeathMsg(Unit killer){
        displayCallBack.send(String.format("%s as killed by %s.", getName(), killer.getName()));
    }
    protected void onLevelUpMsg(){
        displayCallBack.send(levelUpString());
    }

    protected abstract void cantCastMsg(String reason);
}

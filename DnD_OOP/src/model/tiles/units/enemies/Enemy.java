package model.tiles.units.enemies;

import model.tiles.units.Unit;
import model.tiles.units.players.Player;
import utils.callbacks.DamageCallBack;

import java.util.Random;

public abstract class Enemy extends Unit {
    int expValue;

    public Enemy(char tile, String name, int healthPool, int attack, int defence, int expValue, Color color) {
        super(tile, name, healthPool, attack, defence,color);
        this.expValue = expValue;
    }

    @Override
    public void defend(Player p, int damage, DamageCallBack dcb) {
        Random rnd = new Random();
        int defence = rnd.nextInt(this.defence + 1);
        defenceRollMsg(defence);
        int actualDamage = Math.max(damage - defence, 0);
        dcb.damage(p.getName(), getName(), actualDamage);
        if (actualDamage > 0) {
            takeDamage(actualDamage, p);
            if (isDead()) {
                p.gainExperience(expValue);
            }
        }
    }

    @Override
    public void attack(Player p) {
        onCombatMsg(p);
        Random rnd = new Random();
        int damage = rnd.nextInt(this.attack + 1);
        attackRollMsg(damage);
        p.defend(this, damage, combatCallback);
    }

    @Override
    public void defend(Enemy m, int damage, DamageCallBack dcb) {

    }

    @Override
    public void attack(Enemy m) {

    }

    public abstract void takeTurn();

    @Override
    public boolean canMoveOn(Player p) {
        p.attack(this);
        return isDead();
    }

    @Override
    public boolean canMoveOn(Monster m) {
        return false;
    }

    @Override
    public void takeDamage(int damageTaken, Unit dealer) {
        super.takeDamage(damageTaken, dealer);
        if (isDead()) {
            board.removeEnemy(this);
        }
    }

    @Override
    public String description() {
        return super.description() + String.format("Experience Value: %d\t", expValue);
    }

    @Override
    protected void onDeathMsg(Unit killer){
        displayCallBack.send(String.format("%s died. %s gained %d experience.",
                getName(), killer.getName(), expValue));
    }
}
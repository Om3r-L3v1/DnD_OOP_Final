package model.tiles.units;

import model.level.Board;
import model.tiles.Tile;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import utils.callbacks.DamageCallBack;
import utils.callbacks.MessageCallBack;

abstract public class Unit extends Tile {
    protected String name;
    protected Board board;
    protected int healthPool;
    protected int healthAmount;
    protected int attack;
    protected int defence;
    protected MessageCallBack displayCallBack;
    protected DamageCallBack abilityCallback;
    protected DamageCallBack combatCallback;

    public Unit(char tile, String name, int healthPool, int attack, int defence, Color color){
        super(tile,color);
        this.name = name;
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
        this.attack = attack;
        this.defence = defence;
    }

    public Unit init (int x,int y,Board board,MessageCallBack display, DamageCallBack combat, DamageCallBack ability){
        init(x,y);
        this.board = board;
        this.abilityCallback = ability;
        this.displayCallBack = display;
        this.combatCallback = combat;
        return this;
    }

    public void moveUp() {
        if(y != 0){
            Tile target = board.getTile(x, y-1);
            if(canMoveTo(target)){
                board.swapPlaces(x,y, x,y-1);
            }
        }
    }

    public void moveDown() {
        if(y != board.height()){
            Tile target = board.getTile(x, y+1);
            if(canMoveTo(target)){
                board.swapPlaces(x,y, x,y+1);
            }
        }
    }

    public void moveLeft() {
        if(x != 0){
            Tile target = board.getTile(x-1, y);
            if(canMoveTo(target)){
                board.swapPlaces(x,y, x-1,y);
            }
        }
    }

    public void moveRight() {
        if(x != board.width()){
            Tile target = board.getTile(x+1, y);
            if(canMoveTo(target)){
                board.swapPlaces(x,y,x+1,y);
            }
        }
    }

    public String getName() {
        return color.wrap(name);
    }

    protected void takeDamage(int damageTaken, Unit dealer) {
        this.healthAmount = Math.max(0, healthAmount - damageTaken);
        if(isDead())
            onDeathMsg(dealer);
    }

    protected void heal(int healAmount){
        this.healthAmount = Math.min(healthPool, healthAmount + healAmount);
    }

    public boolean isDead(){return healthAmount == 0;}

    public String description(){
        String result = String.format("%s \tHealth: %d/%d\tAttack: %d\tDefence: %d\t",
                getName(), healthAmount, healthPool, attack, defence);
        return result;
    }

    public abstract void defend(Player p, int damage, DamageCallBack dcb);
    public abstract void defend(Enemy m, int damage, DamageCallBack dcb);
    public abstract void attack(Player p);
    public abstract void attack(Enemy m);

    protected abstract boolean canMoveTo(Tile target);

    protected abstract void onDeathMsg(Unit killer);
    public void descriptionMsg(){
        displayCallBack.send(description());
    }
    protected void onCombatMsg(Unit target){
        displayCallBack.send(String.format("%s engaged in combat with %s.", getName(), target.getName()));
        descriptionMsg();
        target.descriptionMsg();
    }
    protected void attackRollMsg(int attack){
        displayCallBack.send(String.format("%s rolled %d attack points.", getName(), attack));
    }
    protected void defenceRollMsg(int defence){
        displayCallBack.send(String.format("%s rolled %d defence points.", getName(), defence));
    }
}

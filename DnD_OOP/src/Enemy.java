import java.util.Random;

public abstract class Enemy extends Unit {
    int expValue;

    public Enemy(int x, int y, char tile, String name, Board board, int healthPool, int healthAmount, int attack, int defence, int expValue) {
        super(x,y,tile,name,board,healthPool,healthAmount,attack,defence);
        this.expValue = expValue;
    }
    @Override
    public void defend(Player p, int damage){
        Random rnd = new Random();
        int defence = rnd.nextInt(this.defence+1);
        if(defence<damage){
            takeDamage(damage - defence);
            if(isDead()){
                p.gainExperience(expValue);
            }
        }
        //damage of (damage - defence) caused
        //health remaining
    }

    @Override
    public void attack(Player p){
        //player name is attacking monster name
        //you role the attack you got x
        //the defender roled y
        Random rnd = new Random();
        int damage = rnd.nextInt(this.attack+1);
        p.defend(this, damage);
    }
    @Override
    public void defend(Enemy m, int damage){

    }
    @Override
    public void attack(Enemy m){

    }

    public abstract void takeTurn();

    @Override
    public boolean canMoveOn(Player p){
        p.attack(this);
        return healthAmount == 0;
    }

    @Override
    public boolean canMoveOn(Monster m){
        return false;
    }

    @Override
    public void takeDamage(int damageTaken){
        super.takeDamage(damageTaken);
        if(isDead()){
            board.removeEnemy(this);
        }
    }
}
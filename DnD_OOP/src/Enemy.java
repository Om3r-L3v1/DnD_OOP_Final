import java.util.Random;

public abstract class Enemy extends Unit {
    int expValue;

    public Enemy(int x, int y, char tile, String name, Board board, int healthPool, int healthAmount, int attack, int defence, int expValue) {
        super(x, y, tile, name, board, healthPool, healthAmount, attack, defence);
        this.expValue = expValue;
    }

    @Override
    public void defend(Player p, int damage, DamageCallBack dcb) {
        Random rnd = new Random();
        int defence = rnd.nextInt(this.defence + 1);
        p.defenceRollMsg(defence);
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
        p.defend(this, damage, COMBAT_CALLBACK);
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
        return healthAmount == 0;
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
        callBack.send(String.format("%s died. %s gained %d experience",
                getName(), killer.getName(), expValue));
    }
}
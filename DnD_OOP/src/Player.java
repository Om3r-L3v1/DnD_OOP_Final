import java.util.Random;

public abstract class Player extends Unit implements HeroicUnit {
    public static final char PLAYER_CHAR = '@';
    protected int experience;
    protected int level;
    protected String abilityName;

    public Player(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence) {
        super(x, y, PLAYER_CHAR, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.experience = 0;
        this.level = 1;
    }

    @Override
    public void castAbility() {
        
    }

    @Override
    public boolean canMoveOn(Player p){
        return false;
    }

    @Override
    public boolean canMoveOn(Monster m){
        this.defend(m);
        return healthAmount == 0;
    }

    @Override
    public boolean canMoveTo(Tile target){
        return target.canMoveOn(this);
    }
    @Override
    public void defend(Player p){}
    @Override
    public void defend(Enemy m){

        //monster name is attacking player name
        //you role the attack you got x
        //the defender rolled y
        Random rnd = new Random();
        int damage = rnd.nextInt(m.attack+1);
        int defence = rnd.nextInt(this.defence+1);
        if(defence<damage)
            setHealthAmount(healthAmount - (damage - defence));
        //damage of (damage - defence) caused
        //health remaining

    }

}

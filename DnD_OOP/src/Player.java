public abstract class Player extends Unit implements HeroicUnit {
    public static final char PLAYER_CHAR = '@';
    protected int experience;
    protected int level;

    public Player(int x, int y, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int experience, int level) {
        super(x, y, PLAYER_CHAR, name, currentLevel, healthPool, healthAmount, attack, defence);
        this.experience = experience;
        this.level = level;
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
}

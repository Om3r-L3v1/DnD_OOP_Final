public abstract class Enemy extends Unit {
    int expValue;

    public Enemy(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int expValue) {
        super(x,y,tile,name,currentLevel,healthPool,healthAmount,attack,defence);
        this.expValue = expValue;
    }

    public void takeTurn(){

    }

    @Override
    public boolean canMoveOn(Player p){
        this.defend(p);
        return healthAmount == 0;
    }

    @Override
    public boolean canMoveOn(Monster m){
        return false;
    }
}
public class Enemy extends Unit {
    int expValue;

    public Enemy(int x, int y, char tile, String name, int healthPool, int healthAmount, int attack, int defence, int expValue) {
        super(x,y,tile,name,healthPool,healthAmount,attack,defence,level);
        this.expValue = expValue;
    }
}
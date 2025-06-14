public class Enemy extends Unit {
    Integer expValue;

    public Enemy(Integer x, Integer y, char tile, String name, Integer healthPool, Integer healthAmount, Integer attack, Integer defence, Integer expValue) {
        super(x,y,tile,name,healthPool,healthAmount,attack,defence);
        this.expValue = expValue;
    }
}
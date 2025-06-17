public class Trap extends Enemy {
    private int visibalityTime;
    private int invisibalityTime;
    private int ticksCount;
    boolean visible;

    public Trap(int x, int y, char tile, String name, int healthPool, int healthAmount, int attack, int defence, int expValue,
                   int visibalityTime, int invisibalityTime, int ticksCount, boolean visible) {
        super(x,y,tile,name,healthPool,healthAmount,attack,defence,expValue);
        this.visibalityTime = visibalityTime;
        this.invisibalityTime = invisibalityTime;
        this.ticksCount = ticksCount;
        this.visible = visible;
    }
}

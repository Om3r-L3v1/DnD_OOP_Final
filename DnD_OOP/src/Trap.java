public class Trap extends Enemy {
    private int visibalityTime;
    private int invisibalityTime;
    private int ticksCount;
    boolean visible;
    final int visionRange = 2;
    private Level level;

    public Trap(int x, int y, char tile, String name, int healthPool, int healthAmount, int attack, int defence, int expValue,
                   int visibalityTime, int invisibalityTime, int ticksCount, boolean visible) {
        super(x,y,tile,name,healthPool,healthAmount,attack,defence,expValue,level);
        this.visibalityTime = visibalityTime;
        this.invisibalityTime = invisibalityTime;
        this.visible = true;
        this.ticksCount = 0;
        this.level = level;
    }
}

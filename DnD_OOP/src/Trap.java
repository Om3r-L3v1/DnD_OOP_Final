public class Trap extends Enemy {
    private int visibalityTime;
    private int invisibalityTime;
    private int ticksCount;
    boolean visible;
    final int visionRange = 2;

    public Trap(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int expValue,
                   int visibalityTime, int invisibalityTime, int ticksCount, boolean visible) {
        super(x,y,tile,name,currentLevel,healthPool,healthAmount,attack,defence,expValue);
        this.visibalityTime = visibalityTime;
        this.invisibalityTime = invisibalityTime;
        this.visible = true;
        this.ticksCount = 0;
    }
}

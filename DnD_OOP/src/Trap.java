public class Trap extends Enemy {
    private Integer visibalityTime;
    private Integer invisibalityTime;
    private Integer ticksCount;
    boolean visible;
    final int visionRange = 2;
    private Level level;

    public Trap(Integer x, Integer y, char tile, String name, Integer healthPool, Integer healthAmount, Integer attack, Integer defence, Integer expValue,
                   Integer visibalityTime, Integer invisibalityTime,Level level) {
        super(x,y,tile,name,healthPool,healthAmount,attack,defence,expValue,level);
        this.visibalityTime = visibalityTime;
        this.invisibalityTime = invisibalityTime;
        this.visible = true;
        this.ticksCount = 0;
        this.level = level;
    }
}

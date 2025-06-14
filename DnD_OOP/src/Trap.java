public class Trap extends Enemy {
    private Integer visibalityTime;
    private Integer invisibalityTime;
    private Integer ticksCount;
    boolean visible;

    public Trap(Integer x, Integer y, char tile, String name, Integer healthPool, Integer healthAmount, Integer attack, Integer defence, Integer expValue,
                   Integer visibalityTime, Integer invisibalityTime, Integer ticksCount, boolean visible) {
        super(x,y,tile,name,healthPool,healthAmount,attack,defence,expValue);
        this.visibalityTime = visibalityTime;
        this.invisibalityTime = invisibalityTime;
        this.ticksCount = ticksCount;
        this.visible = visible;
    }
}

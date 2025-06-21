public class Trap extends Enemy {
    private final int ATTACK_RANGE = 2;

    private int visibalityTime;
    private int invisibalityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int expValue,
                   int visibalityTime, int invisibalityTime) {
        super(x,y,tile,name,currentLevel,healthPool,healthAmount,attack,defence,expValue);
        this.visibalityTime = visibalityTime;
        this.invisibalityTime = invisibalityTime;
        this.visible = true;
        this.ticksCount = 0;
    }

    @Override
    public boolean canMoveTo(Tile target){
        return false;
    }

    @Override
    public void takeTurn() {
        visible = ticksCount < visibalityTime;
        if(ticksCount == (visibalityTime + invisibalityTime))
            ticksCount = 0;
        else
            ticksCount++;
        Player p = currentlevel.getPlayer();
        if(getRange(p) < ATTACK_RANGE){
            attack(p);
        }
    }
}

public class Trap extends Enemy {
    private final int ATTACK_RANGE = 2;

    private int visibalityTime;
    private int invisibalityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char tile, String name, int healthPool, int healthAmount, int attack, int defence, int expValue,
                   int visibalityTime, int invisibalityTime) {
        super(tile,name,healthPool,healthAmount,attack,defence,expValue);
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
        Player p = board.getPlayer();
        if(getRange(p) < ATTACK_RANGE){
            attack(p);
        }
    }

    @Override
    public String toString(){
        return visible ? super.toString() : Empty.EMPTY_CHAR + "";
    }
}

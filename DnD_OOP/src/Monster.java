import java.util.Random;

public class Monster extends Enemy {
    protected int visionRange;
    public Monster(int x, int y, char tile, String name, Level currentLevel, int healthPool, int healthAmount, int attack, int defence, int expValue, int visionRange) {
        super(x,y,tile,name,currentLevel,healthPool,healthAmount,attack,defence,expValue);
        this.visionRange = visionRange;
    }

    @Override
    public boolean canMoveTo(Tile target){
        return target.canMoveOn(this);
    }

    @Override
    public void takeTurn() {
        Player p = currentlevel.getPlayer();
        if(getRange(p) < visionRange){
            moveChase();
        }
        else{
            moveRandom();
        }
    }
    protected void moveChase(){
        Player p = currentlevel.getPlayer();
        int dx = getX() - p.getX();
        int dy = getY() - p.getY();
        if(Math.abs(dx) > Math.abs(dy)){
            if(dx > 0)
                moveLeft();
            else
                moveRight();
        }
        else{
            if(dy > 0)
                moveUp();
            else
                moveDown();
        }
    }
    protected void moveRandom(){
        Random rnd = new Random();
        int direction = rnd.nextInt(5);
        switch (direction){
            case 0:
                moveLeft();
                break;
            case 1:
                moveRight();
                break;
            case 2:
                moveUp();
                break;
            case 3:
                moveDown();
                break;
            default:
                break;
        }
    }
}

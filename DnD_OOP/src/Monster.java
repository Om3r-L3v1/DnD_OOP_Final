import java.util.Random;

public class Monster extends Enemy {
    protected int visionRange;

    public Monster(char tile, String name, int healthPool, int attack, int defence, int expValue, int visionRange) {
        super(tile,name,healthPool,attack,defence,expValue);
        this.visionRange = visionRange;
    }

    @Override
    public boolean canMoveTo(Tile target){
        return target.canMoveOn(this);
    }

    @Override
    public void takeTurn() {
        Player p = board.getPlayer();
        if(getRange(p) < visionRange){
            moveChase();
        }
        else{
            moveRandom();
        }
    }
    public String description(){
        return super.description()+"Vision Range: "+visionRange;
    }
    protected void moveChase(){
        Player p = board.getPlayer();
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

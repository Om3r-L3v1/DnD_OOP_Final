abstract public class Tile {
    protected int x;
    protected int y;
    protected char tile;

    public Tile(char tile, int x, int y){
        this.x = x;
        this.y = y;
        this.tile = tile;

    }

    public int GetRange(Tile t){
        return (int)Math.sqrt(Math.pow(t.x-x,2)+Math.pow(t.y-y,2));
    }

    public int getX(){return x;}

    public void setX(int x){this.x = x;}

    public int getY(){return y;}

    public void setY(int y){this.y = y;}

    public abstract boolean canMoveOn(Player p);
    public abstract boolean canMoveOn(Monster m);
}

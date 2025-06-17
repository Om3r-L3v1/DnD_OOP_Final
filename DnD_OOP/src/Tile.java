abstract public class Tile {
    private int x;
    private int y;
    private char tile;
    public Tile(char tile, int x, int y){
        this.x = x;
        this.y = y;
        this.tile = tile;

    }
    private int GetRange(Tile t){
        return (int)Math.sqrt(Math.pow(t.x-x,2)+Math.pow(t.y-y,2));
    }
}

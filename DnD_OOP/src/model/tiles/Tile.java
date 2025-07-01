package model.tiles;

import model.tiles.units.enemies.Monster;
import model.tiles.units.players.Player;

abstract public class Tile {
    protected int x;
    protected int y;
    protected char tile;
    public Tile(char tile) {
        this.tile  = tile;
    }
    public Tile init(int x,int y){
        this.x = x;
        this.y = y;
        return this;
    }
  
    protected int getRange(Tile t){
        return (int)Math.sqrt(Math.pow(t.x-x,2)+Math.pow(t.y-y,2));
    }

    public int getX(){return x;}

    public void setX(int x){this.x = x;}

    public int getY(){return y;}

    public void setY(int y){this.y = y;}

    public char getTile(){return tile;}

    public abstract boolean canMoveOn(Player p);
    public abstract boolean canMoveOn(Monster m);

    @Override
    public String toString() {
        return String.valueOf(tile);
    }
}

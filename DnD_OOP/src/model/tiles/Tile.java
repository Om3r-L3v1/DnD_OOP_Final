package model.tiles;

import model.tiles.units.enemies.Monster;
import model.tiles.units.players.Player;

abstract public class Tile {
    protected int x;
    protected int y;
    protected char tile;
    protected Color color;
    public enum Color {
        RESET ("\u001B[0m"),
        RED   ("\u001B[31m"),
        GREEN ("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE  ("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN  ("\u001B[36m"),
        WHITE ("\u001B[37m"),
        GOLD  ("\u001B[38;5;220m"),
        PINK  ("\u001B[38;5;205m"),
        ORANGE      ("\u001B[38;5;208m"),
        LIME        ("\u001B[38;5;118m"),
        DARK_RED     ("\u001B[38;5;88m"),
        BLOOD_PURPLE ("\u001B[38;5;90m"),
        POISON_GREEN ("\u001B[38;5;70m"),
        ASH_GREY     ("\u001B[38;5;245m"),
        VOID_BLUE    ("\u001B[38;5;18m"),
        VIOLET      ("\u001B[38;5;177m"),
        BROWN       ("\u001B[38;5;130m"),
        LIGHT_GREY  ("\u001B[38;5;250m"),
        TEAL        ("\u001B[38;5;30m"),
        ROSE        ("\u001B[38;5;211m"),
        LILAC        ("\u001B[38;5;183m"),
        ROYAL_PURPLE ("\u001B[38;5;129m");



        private final String code;
        Color(String code) { this.code = code; }
        public String wrap(String s) { return code + s + RESET.code; }
    }
    public Tile(char tile) {
        this(tile, null);
    }
    /** Explicit colour */
    public Tile(char tile, Color color) {
        this.tile  = tile;
        this.color = color;
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
        String glyph = String.valueOf(tile);
        return (color == null ? glyph         // no colour chosen ➜ plain text
                : color.wrap(glyph));
    }
}

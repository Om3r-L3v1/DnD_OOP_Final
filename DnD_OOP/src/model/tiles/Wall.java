package model.tiles;

import model.tiles.units.enemies.Monster;
import model.tiles.units.players.Player;

public class Wall extends Tile {
    public static final char WALL_CHAR = '#';

    public Wall(){
        super(WALL_CHAR);
    }

    @Override
    public boolean canMoveOn(Player p) {
        return false;
    }

    @Override
    public boolean canMoveOn(Monster m) {
        return false;
    }
}

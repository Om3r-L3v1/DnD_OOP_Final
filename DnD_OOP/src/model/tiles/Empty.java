package model.tiles;

import model.tiles.units.enemies.Monster;
import model.tiles.units.players.Player;

public class Empty extends Tile {
    public static final char EMPTY_CHAR = '.';

    public Empty(){
        super(EMPTY_CHAR);
    }

    @Override
    public boolean canMoveOn(Player p) {
        return true;
    }

    @Override
    public boolean canMoveOn(Monster m) {
        return true;
    }
}

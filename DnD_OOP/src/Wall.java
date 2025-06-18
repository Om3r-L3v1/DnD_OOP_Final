public class Wall extends Tile {
    public static final char WALL_CHAR = '#';

    public Wall(int x, int y){
        super(WALL_CHAR,x,y);
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

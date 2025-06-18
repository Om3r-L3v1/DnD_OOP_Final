public class Empty extends Tile{
    public static final char EMPTY_CHAR = '.';

    public Empty(int x, int y){
        super(EMPTY_CHAR,x,y);
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

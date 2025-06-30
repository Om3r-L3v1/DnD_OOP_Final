import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Board {
    private Player player;
    private Tile[][] tiles;
    private List<Enemy> enemies;

    public Board(Player player){
        this.player = player;
    }
    public void init(Tile[][] tiles,List<Enemy> enemies){
        this.tiles = tiles;
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies(){return enemies;}
    public boolean EnemiesDead(){return enemies.isEmpty();}
    public Player getPlayer(){return player;}
    public int height(){return tiles[0].length;}
    public int width(){return tiles.length;}

    public Tile getTile(int x, int y){return tiles[x][y];}
    public void swapPlaces(Tile t1, Tile t2){
        int x1 = t1.getX();
        int y1 = t1.getY();

        t1.setX(t2.getX());
        t1.setY(t2.getY());
        t2.setX(x1);
        t2.setY(y1);

        tiles[t1.getX()][t1.getY()] = t1;
        tiles[t2.getX()][t2.getY()] = t2;
    }

    public void removeEnemy(Enemy e){
        enemies.remove(e);
        tiles[e.getX()][e.getY()] = new Empty().init(e.getX(),e.getY());
    }
    public String toString(){
        StringBuilder toString = new StringBuilder();
        for(Tile[] line:tiles){
            for(Tile tile:line){
                toString.append(tile.toString());
            }
            toString.append("\n");
        }
        return toString.toString();
    }

}

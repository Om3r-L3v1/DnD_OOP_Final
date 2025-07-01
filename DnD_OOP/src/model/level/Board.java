package model.level;

import model.tiles.*;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;

import java.util.List;

public class Board {
    private Player player;
    private Tile[][] tiles;
    private List<Enemy> enemies;

    public Board(Player player){
        this.player = player;

    }
    public void init(Tile[][] tiles, List<Enemy> enemies){
        this.tiles = tiles;
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies(){return enemies;}
    public boolean EnemiesDead(){return enemies.isEmpty();}
    public Player getPlayer(){return player;}
    public int height(){return tiles.length;}
    public int width(){return tiles[0].length;}

    public Tile getTile(int x, int y){return tiles[y][x];}
    public void swapPlaces(int x1, int y1, int x2, int y2){
        Tile t1 = getTile(x1,y1);
        Tile t2 = getTile(x2,y2);

        t1.setX(x2);
        t1.setY(y2);
        t2.setX(x1);
        t2.setY(y1);

        tiles[y2][x2] = t1;
        tiles[y1][x1] = t2;
    }

    public void removeEnemy(Enemy e){
        enemies.remove(e);
        tiles[e.getY()][e.getX()] = new Empty().init(e.getX(),e.getY());
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Board {
    private Player player;
    private Tile[][] tiles;
    private List<Enemy> enemies;

    public Board(Player player, File levelFile){
        this.player = player;
        char[][] characters = fileToChars(levelFile);
        for(int x = 0; x < characters.length; x++){
            for(int y = 0; y < characters[x].length; y++){
                tiles[x][y] = charToTile(characters[x][y], x, y);
            }
        }
    }


    private char[][] fileToChars(File file) {
        if (file.isFile()) {
            try {
                List<String> lines = Files.readAllLines(file.toPath());

                char[][] matrix = new char[lines.size()][];
                for (int i = 0; i < lines.size(); i++) {
                    matrix[i] = lines.get(i).toCharArray();
                }
                return matrix;
            } catch (IOException e) {
                System.err.println("Error reading file: " + file.getName());
                e.printStackTrace();
            }
        }
        System.out.println("file is not file");
        return null;
    }
    private Tile charToTile(char c, int x, int y) {
        switch (c) {
            case 's':
                Monster lannisterSoldier = new Monster(x, y, c, "Lannister Soldier", this, 80, 80, 8, 3, 25, 3);
                enemies.add(lannisterSoldier);
                return lannisterSoldier;
            case 'k':
                Monster lannisterKnight = new Monster(x, y, c, "Lannister Knight", this, 200, 200, 14, 8, 50, 4);
                enemies.add(lannisterKnight);
                return lannisterKnight;
            case 'q':
                Monster queenGuard = new Monster(x, y, c, "Queen's Guard", this, 400, 400, 20, 15, 100, 5);
                enemies.add(queenGuard);
                return queenGuard;
            case 'z':
                Monster wright = new Monster(x, y, c, "Wright", this, 600, 600, 30, 15, 100, 3);
                enemies.add(wright);
                return wright;
            case 'b':
                Monster bearWright = new Monster(x, y, c, "Bear-Wright", this, 1000, 1000, 75, 30, 250, 4);
                enemies.add(bearWright);
                return bearWright;
            case 'g':
                Monster giantWright = new Monster(x, y, c, "Giant-Wright", this, 1500, 1500, 100, 40, 500, 5);
                enemies.add(giantWright);
                return giantWright;
            case 'w':
                Monster whiteWalker = new Monster(x, y, c, "White Walker", this, 2000, 2000, 150, 50, 1000, 6);
                enemies.add(whiteWalker);
                return whiteWalker;
            case 'M':
                Boss theMountain = new Boss(x, y, c, "The Mountain", this, 1000, 1000, 60, 25, 500, 6, 5);
                enemies.add(theMountain);
                return theMountain;
            case 'C':
                Boss cersei = new Boss(x, y, c, "Queen Cersei", this, 100, 100, 10, 10, 1000, 1, 8);
                enemies.add(cersei);
                return cersei;

            case 'K':
                Boss nightsKing = new Boss(x, y, c, "Night's King", this, 5000, 5000, 300, 150, 5000, 8, 3);
                enemies.add(nightsKing);
                return nightsKing;
            case 'B':
                Trap bonusTrap = new Trap(x, y, c, "Bonus Trap", this, 1, 1, 1, 1, 250, 1, 5);
                enemies.add(bonusTrap);
                return bonusTrap;
            case 'Q':
                Trap queensTrap = new Trap(x, y, c, "Queen's Trap", this, 250, 250, 50, 10, 100, 3, 7);
                enemies.add(queensTrap);
                return queensTrap;
            case 'D':
                Trap deathTrap = new Trap(x, y, c, "Death Trap", this, 500, 500, 100, 20, 250, 1, 10);
                enemies.add(deathTrap);
                return deathTrap;
            case Empty.EMPTY_CHAR:
                Empty empty = new Empty(x, y);
                return empty;
            case Wall.WALL_CHAR:
                Wall wall = new Wall(x, y);
                return wall;
            case Player.PLAYER_CHAR:
                player.setX(x);
                player.setY(y);
                return player;
            default:
                System.out.println("oops wrong char");
                throw new IllegalArgumentException();
        }
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
        tiles[e.getX()][e.getY()] = new Empty(e.getX(),e.getY());
    }
}

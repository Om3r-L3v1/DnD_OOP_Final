import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Level {
    List<Enemy> enemies;
    Tile[][] board;
    Player player;
    private char[][] FileToChars(File file) {
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
    private Tile CharToTile(char c, int x, int y){
        switch(c){
            case 's':

                Monster lannisterSoldier = new Monster(x, y, c, "Lannister Soldier", 80, 80, 8, 3, 25, 3, this);
                enemies.add(lannisterSoldier);
                return lannisterSoldier;
            case 'k':
                Monster lannisterKnight = new Monster(x, y, c, "Lannister Knight", 200, 200, 14, 8, 50, 4, this);
                enemies.add(lannisterKnight);
                return lannisterKnight;
            case 'q':
                Monster queenGuard = new Monster(x, y, c, "Queen's Guard", 400, 400, 20, 15, 100, 5, this);
                enemies.add(queenGuard);
                return queenGuard;
            case 'z':
                Monster wright = new Monster(x, y, c, "Wright", 600, 600, 30, 15, 100, 3, this);
                enemies.add(wright);
                return wright;
            case 'b':
                Monster bearWright = new Monster(x, y, c, "Bear-Wright", 1000, 1000, 75, 30, 250, 4, this);
                enemies.add(bearWright);
                return bearWright;
            case 'g':
                Monster giantWright = new Monster(x, y, c, "Giant-Wright", 1500, 1500, 100, 40, 500, 5, this);
                enemies.add(giantWright);
                return giantWright;
            case 'w':
                Monster whiteWalker = new Monster(x, y, c, "White Walker", 2000, 2000, 150, 50, 1000, 6, this);
                enemies.add(whiteWalker);
                return whiteWalker;
            case 'M':
                Boss theMountain = new Boss(x, y, c, "The Mountain", 1000, 1000, 60, 25, 500, 6, 5,this);
                enemies.add(theMountain);
                return theMountain;
            case 'C':
                Boss cersei = new Boss(x, y, c, "Queen Cersei", 100, 100, 10, 10, 1000, 1, 8,this);
                enemies.add(cersei);
                return cersei;

            case 'K':
                Boss nightsKing = new Boss(x, y, c, "Night's King", 5000, 5000, 300, 150, 5000, 8, 3,this);
                enemies.add(nightsKing);
                return nightsKing;
            case "B":
                Trap bonusTrap = new Trap(x,y,c,"Bonus Trap",1,1,1,1,250,1,5,this);
                enemies.add(bonusTrap);
                return bonusTrap;
            case "Q":
                Trap queensTrap = new Trap(x,y,c,"Queen's Trap",250,250,50,10,100,3,7,this);
                enemies.add(queensTrap);
                return queensTrap;
            case "D":
                Trap deathTrap = new Trap(x,y,c,"Death Trap",500,500,100,20,250,1,10,this);
                enemies.add(deathTrap);
                return deathTrap;
            case ".":
                Empty empty = new Empty(x,y,c);
                return empty;
            case "#":
                Wall wall = new Wall(x,y,c);
                return wall;
            case "@":
                player.setX(x);
                player.setY(y);
            default:
                System.out.println("oops wrong char");
                throw new IllegalArgumentException();
        }
    }
}

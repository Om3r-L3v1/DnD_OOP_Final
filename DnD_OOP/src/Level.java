import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Level {
    private Player player;
    private Tile[][] tiles;
    private List<Enemy> enemies;
    private boolean levelOver;
    private Scanner scanner;

    public Level(Player player, File levelFile){
        //Decode file into tiles and enemies
    }

    public boolean play(){
        levelOver = false;
        while(!levelOver){
            playerTurn();
            enemyTurn();
        }
        if(player.getHealthAmount() == 0)
            return false;
        else
            return true;
    }

    private void playerTurn(){
        //print player info

        boolean validInput;
        String input;
        do{
            validInput = true;
            input = scanner.next();
            switch (input.toLowerCase()){
                case "w":
                    player.moveUp();
                    break;
                case "s":
                    player.moveDown();
                    break;
                case "a":
                    player.moveLeft();
                    break;
                case "d":
                    player.moveRight();
                    break;
                case "e":
                    player.castAbility();
                    break;
                case "q":
                    break;
                default:
                    //print message
                    validInput = false;
                    break;
            }
        }while(!validInput);
        if(enemies.isEmpty())
            levelOver = true;
    }

    private void enemyTurn(){
        for(Enemy e : enemies){
            e.takeTurn();
            if(player.getHealthAmount() == 0){
                levelOver = true;
                return;
            }
        }
    }
}

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Level {
    private Player player;
    private Board board;
    private boolean levelOver;
    private Scanner scanner;

    public Level(Player player, File levelFile){
        this.player = player;
        scanner = new Scanner(System.in);
        board = new Board(player, levelFile);
    }

    public boolean play(){
        levelOver = false;
        while(!levelOver){
            playerTurn();
            enemyTurn();
        }
        if(player.isDead())
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
        player.gameTick();
        if(board.EnemiesDead())
            levelOver = true;
    }

    private void enemyTurn(){
        for(Enemy e : board.getEnemies()){
            e.takeTurn();
            if(player.isDead()){
                levelOver = true;
                return;
            }
        }
    }

    public Board getBoard(){
        return board;
    }
    public List<Enemy> getEnemies(){return board.getEnemies();}
    public Player getPlayer(){return player;}
}

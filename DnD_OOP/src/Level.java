import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Level {
    private static final MessageCallBack DISPLAY_CALLBACK = CLI::display;

    private Player player;
    private Board board;
    private boolean levelOver;
    private Scanner scanner;
    private MessageCallBack callBack;

    public Level(Player player, File levelFile){
        this.player = player;
        scanner = new Scanner(System.in);
        board = new Board(player, levelFile);
        callBack = DISPLAY_CALLBACK;
    }

    public boolean play(){
        levelOver = false;
        while(!levelOver){
            playerTurn();
            enemyTurn();
        }
        if(player.isDead()){
            lossMsg();
            return false;
        }
        else{
            winMsg();
            return true;
        }
    }

    private void playerTurn(){
        boardMsg();
        player.descriptionMsg();

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
    private void boardMsg(){
        callBack.send(board.toString());
    }
    private void lossMsg(){
        callBack.send("You lost.");
        boardMsg();
        player.descMsg();
    }
    private void winMsg(){
        callBack.send("Level complete!");
    }
}

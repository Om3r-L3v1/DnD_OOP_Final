package model.level;

import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import utils.callbacks.*;

import java.util.Map;

public class Level {
    private Player player;
    private Board board;
    private boolean levelOver;
    private MessageCallBack displayCallBack;
    private InputCallBack inputCallBack;
    private Map<String, String> validActions;

    public Level(Player player, Board board, MessageCallBack displayCallBack, InputCallBack inputCallBack){
        this.player = player;
        this.board = board;
        this.displayCallBack = displayCallBack;
        this.inputCallBack = inputCallBack;
        validActions = Map.ofEntries(
                Map.entry("w", "Move up"),
                Map.entry("s", "Move down"),
                Map.entry("a", "Move left"),
                Map.entry("d", "Move right"),
                Map.entry("e", "Cast \"" + player.getAbilityName() + "\""),
                Map.entry("q", "Do nothing")
        );
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

        String action = getInput();
        switch (action) {
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
                throw new RuntimeException();
        }
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
    private void boardMsg(){
        displayCallBack.send(board.toString());
    }
    private void lossMsg(){
        displayCallBack.send("You lost.");
        boardMsg();
        player.descriptionMsg();
    }
    private void winMsg(){
        displayCallBack.send("model.level.Level complete!");
    }
    private String getInput(){
        return inputCallBack.getInput(validActions);
    }
}

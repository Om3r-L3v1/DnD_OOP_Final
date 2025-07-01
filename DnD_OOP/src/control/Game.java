package control;

import control.initializers.*;
import model.level.Level;
import model.tiles.units.players.Player;
import view.CLI;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private Player player;
    private LevelInitializer levelInitializer;
    private File[] levelFiles;
    private TileFactory factory;
    private List<String> playerDescriptions;

    public Game(File folder){
        factory = new TileFactory();
        levelFiles = folder.listFiles();
        List<Player> dummyPlayers = factory.getDummyPlayers();
        playerDescriptions = dummyPlayers.stream().map(p -> p.description()).toList();
    }

    public void play(){
        int playerID = CLI.playerInput(playerDescriptions);
        player = factory.producePlayer(playerID);
        levelInitializer = new LevelInitializer(player);
        boolean isAlive = true;
        for (int i = 0; i < levelFiles.length && isAlive; i++) {
            Level l = levelInitializer.initializeLevel(levelFiles[i]);
            isAlive = l.play();
        }
        CLI.gameResult(isAlive);
    }
}

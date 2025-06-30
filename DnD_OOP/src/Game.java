import java.io.File;
import java.util.List;

public class Game {
    private Player player;
    private LevelInitializer levelInitializer;
    private File[] levelFiles;
    private TileFactory factory;
    private List<Player> dummyPlayers;

    public Game(File folder){
        factory = new TileFactory();
        levelFiles = folder.listFiles();
        dummyPlayers = factory.getDummyPlayers();
    }

    public void play(){
        int playerID = CLI.playerInput(dummyPlayers);
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

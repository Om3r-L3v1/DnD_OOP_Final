import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Player p = new Warrior("Jon Snow",300,300, 1000, 4,3);
        File folder = new File("levels_dir");
        File[] files = folder.listFiles();
        LevelInitializer levelInit = new LevelInitializer(p);
        for(File f : files){
            Level l = levelInit.initializeLevel(f);
            if(!l.play())
                break;
        }
    }
}
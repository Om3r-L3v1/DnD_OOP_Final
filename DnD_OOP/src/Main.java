import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Player p = new Warrior(0,0,"Jon Snow",null,300, 300, 30, 4,3);
        File folder = new File("C:\\Users\\sarit\\Desktop\\Year A\\Semester B\\OOP\\Assignment 3\\levels_dir");
        File[] files = folder.listFiles();
        for(File f : files){
            Level l = new Level(p, f);
            if(!l.play())
                break;
        }
    }
}
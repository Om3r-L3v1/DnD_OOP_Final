import control.Game;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File folder = new File("../levels_dir");
        Game game = new Game(folder);
        game.play();
    }
}
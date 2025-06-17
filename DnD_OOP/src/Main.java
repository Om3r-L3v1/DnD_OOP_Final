import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File folder = new File("levels_dir");
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    try {
                        List<String> lines = Files.readAllLines(file.toPath());

                        char[][] matrix = new char[lines.size()][];
                        for (int i = 0; i < lines.size(); i++) {
                            matrix[i] = lines.get(i).toCharArray();
                        }
                        for (int i = 0; i < matrix.length; i++) {
                            String sum = "";
                            for (int j = 0; j < matrix[i].length; j++) {
                                sum += matrix[i][j];
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

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
    private Tile CharToTile(){

    }
}
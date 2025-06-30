import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    public static void gameResult(boolean winner){
        if(winner)
            System.out.println("You won!");
        else
            System.out.println("Game over.");
    }

    public static void display(String message){
        System.out.println(message);
    }

    public static void abilityDamage(String attackerName, String defenderName, int damage){
        System.out.println(String.format("%s hit %s for %d ability damage.",
                attackerName, defenderName, damage));
    }

    public static void combatDamage(String attackerName, String defenderName, int damage){
        System.out.println(String.format("%s dealt %d damage to %s.",
                attackerName, damage, defenderName));
    }

    public static String actionInput(Map<String, String> inputMeanings){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Select action:");
            for (String validInput : inputMeanings.keySet()) {
                System.out.println(validInput + " - " + inputMeanings.get(validInput));
            }
            String input = scanner.next();
            if(inputMeanings.containsKey(input)){
                return input;
            }
            else{
                System.out.println("Invalid action. Try again:");
            }
        }
    }

    public static int playerInput(List<Player> players){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Select a player:");
            int i = 1;
            for (Player p : players) {
                System.out.println(i + ". " + p.getName() + "\t" + p.description());
                i++;
            }
            String input = scanner.next();
            try{
                int playerID = Integer.parseInt(input);
                players.get(playerID - 1);
                return playerID;
            }
            catch (Exception e){
                System.out.println("Invalid player id. Try again:");
            }
        }
    }
}

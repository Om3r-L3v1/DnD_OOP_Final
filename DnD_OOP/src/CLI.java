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
}

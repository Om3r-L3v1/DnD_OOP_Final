public class CLI {
    public static void gameResult(boolean winner){
        if(winner)
            System.out.println("You won!");
        else
            System.out.println("Game over!");
    }

    public static void display(String message){
        System.out.println(message);
    }
}

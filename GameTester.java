//Ryan Insley rinsle2
import java.util.Scanner;

public class GameTester {

    public static void main(String[] args) {
        //Variables needed
        Game g;
        String game;
        //Print Name and netid
        System.out.println("Ryan Insley\nrinsle2\nrinsley\n\n\n\n");
        //Ask user for game name
        Scanner sc = new Scanner(System.in);
        System.out.print("Game name is: ");
        game = sc.next();
        //Create game
        g = new Game(game);
        //Add all the places and directions
        g.play();
    }
}

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
        Scanner sc = new Scanner("MystiCity 3.1.gdf");
        System.out.print("Game name is: ");
        String s = sc.nextLine();
        game = s.substring(8, s.indexOf("//"));
        sc.nextLine();
        //Create game
        g = new Game(sc);
        //Add all the places and directions
        g.play();
    }
}

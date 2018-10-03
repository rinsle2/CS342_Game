//Ryan Insley rinsle2
import java.util.Scanner;

public class GameTester {

    public static void main(String[] args) {
        //Variables needed
        Game g;
        String game;
        String filename = args[1];
        //Print Name and netid
        System.out.println("Ryan Insley\nrinsle2\nrinsley\n\n\n\n");
        //Grab the game file
        Scanner sc = new Scanner(filename);
        String s = sc.next();
        System.out.print("Filetype is: " + s);
        s = sc.next();
        System.out.print("Version is: " + s);
        game = sc.next();
        System.out.println("Welcome to " + game);
        sc.nextLine();
        //Create game
        g = new Game(sc);
        //close the file
        sc.close();
        //Start the game
        g.play();
    }
}

//Ryan Insley rinsle2
import java.util.Scanner;

public class GameTester {

    public static void main(String[] args) {
        //Variables needed
        Game g;
        String game;
        String filename;
        int numChar = 1;
        if (args.length >=1) {
            filename = args[0];
            if (args.length == 2) {
                numChar = Integer.parseInt(args[1]);
            }
        }
        else{
            Scanner scanner = new Scanner(System.in);
            filename = scanner.nextLine();
            scanner.close();
        }
        //Print Name and netid
        System.out.println("Ryan Insley\nrinsle2\nrinsley\n\n\n\n");
        //Grab the game file
        Scanner sc = new Scanner(filename);
        //Create game
        g = new Game(sc);
        //close the file
        sc.close();
        //Start the game
        g.play();
    }
}

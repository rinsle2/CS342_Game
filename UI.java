import java.util.Scanner;
import java.util.StringTokenizer;

public class UI implements DecisionMaker {
    public Move getMove(Character c, Place p) {
        Scanner in = new Scanner(System.in);
        StringTokenizer tokens;
        Move m;
        //Show the place and character
        c.display();
        //Ask for user input
        System.out.println("What do you want to do?(type help for a list of commands)");
        String checked = in.nextLine();
        while (checked.length() == 0) {
            System.out.println("Please enter a valid String.");
            System.out.println("What do you want to do?(type help for a list of commands)");
            checked = in.nextLine();
        }
        tokens = new StringTokenizer(checked);
        if(checked.startsWith("get")) {
            return new Move(tokens.nextToken(), tokens.nextToken());
        }
        if(checked.startsWith("drop")) {
            return new Move(tokens.nextToken(), tokens.nextToken());
        }
        if(checked.startsWith("go")) {
            return new Move(tokens.nextToken(), tokens.nextToken());
        }
        if(checked.startsWith("inve")) {
            return new Move("inventory", "");
        }
        else {
            //How did you get here
            return null;
        }
    }
}

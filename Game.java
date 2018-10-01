//Ryan Insley rinsle2
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.*;

public class Game {
    //Variables
    private String gameName;
    private ArrayList<Place> places;
    private Place curPlace;
    //Constructor
    public Game(Scanner fin) {
        places = new ArrayList<>();
        while(fin.hasNextLine()) {
            String cur = fin.nextLine();
            if(cur.length()==0 || cur.startsWith("//") || cur.startsWith("/*")) {
                continue;
            }
            if (fin.nextLine().contains("PLACES")) {
                int index = fin.nextInt();
                for (int i = 0; i < index; i++) {
                    places.add(new Place(fin));
                }
            }
        }
    }


    public void play() {
        //Welcome the user
        System.out.println("Welcome to " + this.gameName);
        //Start in the Entrance Hall.
        curPlace = places.get(0);
        //Set up the input reader
        Scanner sc = new Scanner(System.in);
        //Game starts now
        curPlace.display();
        String dir = sc.nextLine();
        while(!curPlace.identification().equals("Exit") || dir.equalsIgnoreCase("QUIT") || dir.equalsIgnoreCase("EXIT"))
        {
            dir.trim();
            Place next = curPlace.followDirection(dir);
            if(!curPlace.equals(next)) {
                curPlace = next;
            }
            else {
                System.out.println("You have not found the key for this lock.");
            }
            curPlace.display();
            dir = sc.nextLine();
        }
        sc.close();
        System.out.println("Thank you for playing");
        return;
    }
}

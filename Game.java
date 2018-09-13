//Ryan Insley rinsle2
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    //Variables
    private String gameName;
    private ArrayList<Place> places;
    private Place curPlace;
    //Constructor
    public Game(Scanner fin) {
        
    }
    public Game(String name) {
        this.gameName = name;
        this.places = new ArrayList<>();
    }

    public void addPlace(Place p)
    {
        this.places.add(p);
    }

    public void play() {
        mapSetup();
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

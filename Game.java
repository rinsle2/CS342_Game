//Ryan Insley rinsle2
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    //Variables
    private String gameName;
    private ArrayList<Place> places;
    private Place curPlace;
    //Constructor
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
    //The below is disgusting and needs refactoring.
    private void mapSetup(){

        Place exit = new Place(7, "Exit", "You have retreated. Game over");
        Place notImplemented = new Place(0, "Void", "Nothing here but chickens");
        //add the places into the vector
        this.addPlace(new Place(1, "Entrance Hall",              "The entrance to the castle.  You can retreat west, there are doors East and North, and a stairway leading Down. "));
        this.addPlace(new Place(2, "Ogre's Lair",                  "The Ogre is Sleeping, but he will wake sometime soon, better hurry! \n(While frantically looking around, you see a door to the South and a door to the East.)"));
        this.addPlace(new Place(3, "Pool of Enchantment",   "You are fairly certain that this is a magical pool, but what it does is not clear.\n(There is a oily ramp leading to the floor Down below, and a door to the North)"));
        this.addPlace(new Place(4, "Treasure Storeroom",    "Oh boy treasure! Too bad though, your pockets are full.  You see a door to the South and a door to the North"));
        this.addPlace(new Place(5, "Potion Storeroom",        "There are potions on many, high shelves, none of them are labelled.  There are ingredients in jars, but some of them have been shattered\nThe room looks like a muted rainbow.  There's a stairway leading Up and a door leading East."));
        this.addPlace(new Place(6, "Potions Lab",                 "Bubbling cauldrons and cutting boards are scattered among the multiple desks, There are shards of the cutting boards on the floor.\nThere are doors to the East and West. "));
        this.addPlace(exit);
        this.addPlace(notImplemented);
        //Add a direction to the place(s)
        //Entrance Hall
        this.places.get(0).addDirection(new Direction(1, this.places.get(0), exit, "W"));
        this.places.get(0).addDirection(new Direction(2, this.places.get(0), places.get(1), "N"));
        this.places.get(0).addDirection(new Direction(3, this.places.get(0), places.get(2), "E"));
        this.places.get(0).addDirection(new Direction(4, this.places.get(0), places.get(4), "D"));
        //Ogre's Lair
        this.places.get(1).addDirection(new Direction(5, this.places.get(1), this.places.get(0), "S"));
        this.places.get(1).addDirection(new Direction(6, this.places.get(1), this.places.get(3), "E"));
        //Enchantment Pool
        this.places.get(2).addDirection(new Direction(7, this.places.get(2), this.places.get(0), "W"));
        this.places.get(2).addDirection(new Direction(8, this.places.get(2), this.places.get(3), "LN"));
        this.places.get(2).addDirection(new Direction(9, this.places.get(2), this.places.get(5), "D"));
        //Treasure Storeroom
        this.places.get(3).addDirection(new Direction(10, this.places.get(3), this.places.get(2), "S"));
        this.places.get(3).addDirection(new Direction(11, this.places.get(3), this.places.get(1), "N"));
        //Potions Storeroom
        this.places.get(4).addDirection(new Direction(12, this.places.get(4), this.places.get(0), "U"));
        this.places.get(4).addDirection(new Direction(13, this.places.get(4), this.places.get(5), "LE"));
        //Potions Lab
        this.places.get(5).addDirection(new Direction(14, this.places.get(5), this.places.get(4), "W"));
        this.places.get(5).addDirection(new Direction(15, this.places.get(5), notImplemented, "LE"));
    }
}

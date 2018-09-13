//Ryan Insley, rinsle2
//Place class, holds all information and manipulation of the places in the game.
import java.util.ArrayList;

public class Place {
    //Variables
    private int placeID;
    private String name;
    private String description;
    private ArrayList<Direction> directions;
    //Constructor
    public Place(int ID, String n, String d){
        this.placeID = ID;
        this.description = d;
        this.name = n;
        this.directions = new ArrayList<>();
    }
    //Manipulation
    public String identification() {
        return this.name;
    }
    public void display() {
        
        System.out.print("You are in the ");
        this.name();
        this.description();
        System.out.println();
        System.out.println();
        System.out.print("Give me a direction to go please: ");
    }
    public String getName() {
        return this.name;
    }
    public void name() {
        System.out.println(this.name);
    }
    public void description() {
        System.out.println(this.description);
    }
    //Add a Direction to the vector of directions
    public void addDirection(Direction d){
        this.directions.add(d);
    }
    public Place followDirection(String dir) {
        for(Direction d : this.directions)
        {
            if(d.match(dir))
                return d.follow();
        }
        return this;
    }
    public void print() {
        System.out.println("Place ID:" + this.placeID);
        System.out.println("Place Description:" + this.description);
        System.out.println("Place Name:" + this.name);
        for(Direction d : this.directions)
        System.out.println("Direction ID: " + d.getdirID() + "\nDirection: " + d.getDir());
    }
}

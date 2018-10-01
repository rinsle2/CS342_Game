//Ryan Insley, rinsle2
//Place class, holds all information and manipulation of the places in the game.
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Place {
    //Variables
    private Integer placeID;
    private String name;
    private String description;
    static private TreeMap<Integer, Place> places = new TreeMap<Integer, Place>();
    private ArrayList<Direction> directions;
    private ArrayList<Artifact> artifacts;
    //Constructor
    public Place(Scanner sc) {
        if(sc.nextLine().length()==0 || sc.nextLine().startsWith("//") || sc.nextLine().startsWith("/*")) {
            return;
        }
        String description = "";
        Integer id;
        String pName;
        id = sc.nextInt();
        pName = skip(sc.nextLine());
        int index = sc.nextInt();
        for(int i =0;i<index;i++) {
            description += sc.nextLine();
        }
        this.placeID = id;
        this.name= pName;
        this.description = description;
        this.directions = new ArrayList<>();
    }

    private String skip(String s) {
        s = s.substring(0, s.indexOf("//"));
        return s;
    }
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

    static public Place getPlaceFromId(Integer id) {
        return Place.places.get(id);

    }
    public Place followDirection(String dir) {
        for(Direction d : this.directions)
        {
            if(d.match(dir))
                return d.followDirection();
        }
        return this;
    }


    public void print() {
        System.out.println("Place ID:" + this.placeID);
        System.out.println("Place Description:" + this.description);
        System.out.println("Place Name:" + this.name);
        for(Direction d : this.directions)
        System.out.println("Direction ID: " + d.getDirID() + "\nDirection: " + d.getDir());
    }
}

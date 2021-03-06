//Ryan Insley, rinsle2
//Place class, holds all information and manipulation of the places in the game.
import java.util.*;

public class Place {
    //Variables
    private Integer placeID;
    private String name;
    private String description;
    static public TreeMap<Integer, Place> places = new TreeMap<>();
    private ArrayList<Direction> directions;
    private ArrayList<Artifact> artifacts;
    //Constructor
    public Place(KeyboardScanner sc, float version) {
        String description = "";
        Integer id;
        String pName;
        Scanner s = sc.scanner();  //Replace with whatever getScanner function you have
        id = s.nextInt();
        pName = skip(s.nextLine());
        int index = s.nextInt();
        for(int i =0;i<index;i++) {
            description += s.nextLine();
        }
        this.placeID = id;
        this.name= pName;
        this.description = description;
        this.directions = new ArrayList<>();
        this.artifacts = new ArrayList<>();
        this.addPlacetoMap();
    }
    //Add artifact to the Place
    public void addArtifact(Artifact a) {
        this.artifacts.add(a);
    }
    //Add place to the collection
    private void addPlacetoMap() {
        Place.places.put(placeID, this);
    }
    //Skip comments
    private String skip(String s) {
        s = s.substring(0, s.indexOf("//"));
        return s;
    }
    //Manipulation
    public void display() {
        
        System.out.print("You are in the ");
        this.name();
        this.description();
        System.out.println("Artifacts in this room are: ");
        for(Artifact art : artifacts) {
            art.display();
        }
        System.out.println();
        System.out.print("Give me a direction to go please: ");
    }

    public Artifact getArtifactByName(String string) {
        for(Artifact a : artifacts) {
            if(a.name().equalsIgnoreCase(string)) {
                return a;
            }
        }
        return null;

    }

    public String getName() {
        return this.name;
    }
    private void name() {
        System.out.println(this.name);
    }
    private void description() {
        System.out.println(this.description);
    }
    //Add a Direction to the vector of directions
    public void addDirection(Direction d){
        this.directions.add(d);
    }
    //Used for init in Character and Artifact, returns a random place
    static public Place getRandomPlace() {

        int high = Place.places.lastKey();
        Random rand = new Random();
        return Place.getPlaceFromId(rand.nextInt(high)+1);
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
    public ArrayList<Direction> hallways() {
        return directions;
    }
    //Remove artifact from place
    public void removeArtifact(Artifact a) {
        artifacts.remove(a);
    }
    //Use Key in place
    public void useKey(Artifact a) {
        //Check each direction
        for(Direction d : directions) {
            //Check the pattern for keyPattern or Skeleton key
            if(a.getKeyPattern() == d.getLockPattern()  || a.getKeyPattern() < 0) {
                //Unlock the direction
                d.unlock();
                return;
            }
        }
        //No direction to unlock
        System.out.println("Nothing to unlock.");
    }
    //Debug Code, print everything for one or all places
    public void print() {
        System.out.println("Place ID:" + this.placeID);
        System.out.println("Place Description:" + this.description);
        System.out.println("Place Name:" + this.name);
        for(Direction d : this.directions)
        System.out.println("Direction ID: " + d.getDirID() + "\nDirection: " + d.getDir());
        for(Artifact a : this.artifacts)
            a.display();
    }
    public void printAll() {
        for(Map.Entry<Integer, Place> p : Place.places.entrySet()) {
            p.getValue().print();
        }
    }
}

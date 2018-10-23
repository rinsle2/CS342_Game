//Ryan Insley rinsle2
/*
* Game class
*
* Holds everything necessary for the game (some stuff isn't as specified, but it works out better that way)
*/
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    //Variables
    private ArrayList<Character> characters;

    private float version;
    //Constructor
    public Game(Scanner fin) {
        characters = new ArrayList<>();
        String s = fin.next();
        System.out.print("Filetype is: " + s);
        version = fin.nextFloat();
        System.out.print("Version is: " + version);
        String game = fin.next();
        System.out.println("Welcome to " + game);
        fin.nextLine();
        while(fin.hasNextLine()) {
            String cur = fin.nextLine();
            if(cur.length()==0 || cur.startsWith("//") || cur.startsWith("/*")) {
                continue;
            }
            initItem(fin, "PLACES", cur);
            //Initialize Directions
            initItem(fin, "DIRECTIONS", cur);
            //Initialize Artifacts
            initItem(fin, "ARTIFACTS", cur);
            if (version >= 4.0) {
                initItem(fin, "CHARACTERS", cur);
            }
        }
    }
    //Function for adding all of the items(less copying of code)
    private void initItem(Scanner sc, String whatToLookFor, String current) {
        //If the string contains what you need, enter the loop
        if (current.contains(whatToLookFor)) {
            int index = sc.nextInt();
            for (int i = 0; i < index; i++) {
                //Check for empty lines/Full line comments
                if(current.length()==0 || current.startsWith("//") || current.startsWith("/*")) {
                    continue;
                }
                //Check what item you need and initialize accordingly
                if(whatToLookFor.equalsIgnoreCase("places")) {
                    Place p = new Place(sc, version);
                }
                else if (whatToLookFor.equalsIgnoreCase("directions")){
                    for(Map.Entry<Integer, Place> p : Place.places.entrySet()) {
                        p.getValue().addDirection(new Direction(sc, version));
                    }
                }
                else if(whatToLookFor.equalsIgnoreCase("characters")) {
                    if(sc.next().equalsIgnoreCase("player")) {
                        Character c = new Player(sc, version);
                        characters.add(c);
                    }
                    else {
                        Character c = new NPC(sc, version);
                        characters.add(c);
                    }
                }
                else {
                    Artifact a = new Artifact(sc, version);
                    a.getLocation().addArtifact(a);
                }
            }
        }
    }
    //Play the game
    public void play() {
        //Set up the input reader
        Scanner sc = new Scanner(System.in);
        //Game starts now

    }
}

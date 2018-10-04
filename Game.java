//Ryan Insley rinsle2
/*
* Game class
*
* Holds everything necessary for the game (some stuff isn't as specified, but it works out better that way)
*/
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    //Variables
    private ArrayList<Place> places;
    private ArrayList<Artifact> inventory;
    private Place curPlace;
    //Constructor
    public Game(Scanner fin) {
        places = new ArrayList<>();
        inventory = new ArrayList<>();
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
        }
    }
    //Function for adding all of the items(less copying fo code)
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
                    places.add(new Place(sc));
                }
                else if (whatToLookFor.equalsIgnoreCase("directions")) {
                    for(Place p : places) {
                        p.addDirection(new Direction(sc));
                    }
                }
                else {
                    Artifact a = new Artifact(sc);
                    a.getLocation().addArtifact(a);
                }
            }
        }
    }
    //Play the game
    public void play() {
        //Start in the Entrance Hall.
        curPlace = places.get(0);
        //Set up the input reader
        Scanner sc = new Scanner(System.in);
        //Game starts now
        curPlace.display();
        String dir = sc.nextLine();
        while(!curPlace.identification().equals("Exit") || dir.equalsIgnoreCase("QUIT") || dir.equalsIgnoreCase("EXIT"))
        {
            //check the string
            boolean realString = dir.length() > 0;
            boolean check = checkInput(dir);
            //when legit string, and no other chars in string
            if(!check && realString) {
                move(dir);
            }
            dir = sc.nextLine();
        }
        sc.close();
        System.out.println("Thank you for playing");
    }
    //Input checker
    private boolean checkInput(String str) {
        //Input to check for
        String get = "get";
        String drop = "drop";
        String use = "use";
        String inv = " inve";
        str = str.toLowerCase();
        //Check if it's that string
        if(str.startsWith(get)) {
            str = str.substring(4);
            Artifact a = curPlace.getArtifactByName(str);
            if(a != null) {
                get(a);
                return true;
            }
            System.out.println("No such item exists in this room.");
            return true;
        }
        //Using an item
        else if(str.startsWith(use)) {
            str = str.substring(4);
            Artifact a = getArtifactByName(str);
            if(a != null){
                use(a);
                return true;
            }
            System.out.println("You don't have that item");
            return true;
        }
        //checking the inventory
        else if(str.startsWith(inv)) {
            int vals = 0;
            int mobil = 0;
            for(Artifact a : inventory) {
                a.display();
                vals += a.value();
                mobil += a.size();
            }
            System.out.println("Total Value: " + vals + "\nTotal Weight: " + mobil);
            return true;
        }
        //drop an item
        else if(str.startsWith(drop)) {
            str = str.substring(5);
            drop(str);
            return true;
        }
        //Moving places
        else if(str.startsWith("go")) {
            str = str.substring(3);
            move(str);
            return true;
        }
        else {
            return false;
        }
    }
    //Move places
    private void move(String str) {
        //find the next place
        Place next = curPlace.followDirection(str);
        //move places(if you can)
        if(!curPlace.equals(next)) {
            curPlace = next;
        }
        else {
            System.out.println("You have not found the key for this lock.");
        }
        curPlace.display();

    }
    //Get an item from a place
    private void get(Artifact a) {
        if(a.size() > 0){
           curPlace.removeArtifact(a);
           inventory.add(a);
           return;
        }
        System.out.println("It's bolted down, your hands hurt a bit from trying to lift it.");
    }
    //Use an item
    private void use(Artifact a) {
        curPlace.useArtifact(a);
    }

    private void drop(String str) {
        for(Artifact a : inventory) {
            if(a.name().equalsIgnoreCase(str)) {
                inventory.remove(a);
                return;
            }
        }
        System.out.println("You don't have that item.");
    }
    private Artifact getArtifactByName(String string) {
        for(Artifact a : inventory) {
            if(a.name().equalsIgnoreCase(string)) {
                return a;
            }
        }
        return null;
    }
}

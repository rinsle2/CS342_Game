//Ryan Insley rinsle2
/*
* Game class
*
* Holds everything necessary for the game (some stuff isn't as specified, but it works out better that way)
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.*;

public class Game {
    //Variables
    private String gameName;
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
        if (sc.nextLine().contains(whatToLookFor)) {
            int index = sc.nextInt();
            for (int i = 0; i < index; i++) {
                if(current.length()==0 || current.startsWith("//") || current.startsWith("/*")) {
                    continue;
                }
                if(whatToLookFor.equalsIgnoreCase("places")) {
                    places.add(new Place(sc));
                }
                else if (whatToLookFor.equalsIgnoreCase("directions")) {
                    for(Place p : places) {
                        p.addDirection(new Direction(sc));
                    }
                }
                else {
                    for(Place p : places) {
                        p.addArtifact(new Artifact(sc));
                    }
                }
            }
        }
    }
    public void play() {
        //Welcome the user
        //Start in the Entrance Hall.
        curPlace = places.get(0);
        //Set up the input reader
        Scanner sc = new Scanner(System.in);
        //Game starts now
        curPlace.display();
        String dir = sc.nextLine();
        while(!curPlace.identification().equals("Exit") || dir.equalsIgnoreCase("QUIT") || dir.equalsIgnoreCase("EXIT"))
        {
            boolean check = checkInput(dir);
            if(!check) {
                move(dir);
            }
            dir = sc.nextLine();
        }
        sc.close();
        System.out.println("Thank you for playing");
        return;
    }
    public boolean checkInput(String str) {
        String get = "get";
        String drop = "drop";
        String use = "use";
        String inv = " inve";
        str.toLowerCase();
        if(str.startsWith(get)) {
            str = str.substring(4);
            Artifact a = curPlace.getArtifactByName(str);
            if(a != null) {
                get(a);
                return true;
            }
            else {
                System.out.println("No such item exists in this room.");
                return true;
            }
        }
        else if(str.startsWith(use)) {
            str = str.substring(4);
            Artifact a = getArtifactByName(str);
            if(a != null){
                use(a);
                return true;
            }
            else {
                System.out.println("You don't have that item");
                return true;
            }
        }
        else if(str.startsWith(inv)) {
            for(Artifact a : inventory) {
                a.display();
            }
            return true;
        }
        else if(str.startsWith(drop)) {
            str = str.substring(5);
            drop(str);
            return true;
        }
        else if(str.startsWith("go")) {
            str = str.substring(3);
            move(str);
            return true;
        }
        else {
            return false;
        }
    }

    public void move(String str) {
        Place next = curPlace.followDirection(str);
        if(!curPlace.equals(next)) {
            curPlace = next;
        }
        else {
            System.out.println("You have not found the key for this lock.");
        }
        curPlace.display();

    }
    public void get(Artifact a) {
        curPlace.removeArtifact(a);
        inventory.add(a);
    }

    public void use(Artifact a) {
        curPlace.useArtifact(a);
    }

    public void drop(String str) {
        for(Artifact a : inventory) {
            if(a.name().equalsIgnoreCase(str)) {
                inventory.remove(a);
                return;
            }
        }
        System.out.println("You don't have that item.");
    }
    public Artifact getArtifactByName(String string) {
        for(Artifact a : inventory) {
            if(a.name().equalsIgnoreCase(string)) {
                return a;
            }
        }
        return null;
    }
}

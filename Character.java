import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Character {
    private int ID;
    private String typeOfChar;
    private String name;
    private DecisionMaker d;
    private String description;
    private ArrayList<Artifact> inventory;
    private static ArrayList<Character> people = new ArrayList<>();
    private Place curPlace;
    public Character (Scanner sc, float version, String type) {
        int place = sc.nextInt();
        if (type.equalsIgnoreCase("player")) {
            d = new UI();
        }
        else {
            d = new AI();
        }
        typeOfChar = type;
        if(place > 0) {
            curPlace = Place.getPlaceFromId(place);
            inventory = new ArrayList<>();
        }
        this.ID = sc.nextInt();
        this.name = sc.next();
        int index = sc.nextInt();
        this.description = "";
        for(int i=0;i<index;i++) {
            this.description += sc.nextLine();
            description += " ";
        }
    }
    public Character(int pID, String pName, String pDescription) {
        this.ID = pID;
        this.name = pName;
        this.description=pDescription;
        Character.people.add(this);
    }
    static public Character getCharacterByID(int ID) {
        for(Character c: Character.people) {
            if(c.ID == ID) {
                return c;
            }
        }
        return null;
    }
    public void addArtifactToInventory(Artifact a) {
        inventory.add(a);
    }
    public void makeMove() {
        Move m = d.getMove(this, this.curPlace);
        if(typeOfChar.equalsIgnoreCase("player")) {
            if(m.type.name().equalsIgnoreCase("get")) {
                Artifact a = curPlace.getArtifactByName(m.argument);
                if(a != null ) {
                    get(a);
                }
            }
            else if(m.type.name().equalsIgnoreCase("use")) {
                Artifact a = getArtifactByName(m.argument);
                if(a != null) {
                    a.use(this, this.curPlace);
                }
            }
            else if(m.type.name().equalsIgnoreCase("inventory")) {
                showInventory();
            }
            else if(m.type.name().equalsIgnoreCase("drop")) {
                drop(m.argument);
            }
            else if(m.type.name().equalsIgnoreCase("look")) {
                this.curPlace.display();
            }
            else if(m.type.name().equalsIgnoreCase("go ")) {
                move(this, this.curPlace, m.argument);
            }
        }
    }
    //Move places
    private void move(Character c, Place p, String str) {
        //find the next place
        Place next = curPlace.followDirection(str);
        //move places(if you can)
        if(!curPlace.equals(next)) {
            curPlace = next;
        }
        else {
            System.out.println("You have not found the key for this lock.");
        }
    }
    //Get an item from a place
    private void get(Artifact a) {
        if(a.size() > 0){
            curPlace.removeArtifact(a);
            addArtifactToInventory(a);
            return;
        }
        System.out.println("It's bolted down, your hands hurt a bit from trying to lift it.");
    }
    //Drop item
    private void drop(String str) {
        for(Artifact a : inventory) {
            if(a.name().equalsIgnoreCase(str)) {
                inventory.remove(a);
                curPlace.addArtifact(a);
                return;
            }
        }
        System.out.println("You don't have that item.");
    }
    //Get the Type of Charatcer
    public Place grabPlace() { return this.curPlace;}
    //inspect an item
    private Artifact getArtifactByName(String string) {
        for(Artifact a : inventory) {
            if(a.name().equalsIgnoreCase(string)) {
                return a;
            }
        }
        return null;
    }
    public void display() {
        System.out.println(name);
        System.out.println(description);
        curPlace.display();
    }
    public void showInventory() {
        for(Artifact a : inventory) {
            a.display();
        }
    }

}


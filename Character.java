import java.util.ArrayList;
import java.util.Scanner;

public class Character {
    private int ID;
    private String name;
    private String description;
    private ArrayList<Artifact> inventory;
    private static ArrayList<Character> people = new ArrayList<>();
    private Place curPlace;
    public Character (Scanner sc, float version) {
        int place = sc.nextInt();
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
    //Drop item
    private void drop(String str) {
        for(Artifact a : inventory) {
            if(a.name().equalsIgnoreCase(str)) {
                inventory.remove(a);
                return;
            }
        }
        System.out.println("You don't have that item.");
    }
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


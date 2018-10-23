import java.util.Scanner;

public class Artifact {
    private Place location;
    private Character placed;
    private String name;

    private String description;
    private int artifactID;
    private int keyPattern;
    private int val;
    private int mobility;
    public Artifact (Scanner scanner, float version) {
        int trueLocation = scanner.nextInt();
        if(trueLocation > 0) {
            this.location = Place.getPlaceFromId(trueLocation);
        }
        else if(trueLocation < 0) {
            placed = Character.getCharacterByID(trueLocation = trueLocation *-1);
        }
        else{
            location = Place.getRandomPlace();
        }
        this.artifactID = scanner.nextInt();
        this.val = scanner.nextInt();
        this.mobility = scanner.nextInt();
        this.keyPattern = scanner.nextInt();
        this.name = skip(scanner.next());
        int index = scanner.nextInt();
        String d = "";
        for(int i = 0;i<index;i++) {
            d += scanner.nextLine();
        }
        this.description = d;
    }
    //Skip the comments at the end
    private String skip(String s) {
        s = s.substring(0, s.indexOf("//"));
        return s;
    }
    //Get the value
    public int value() {
        return this.val;
    }
    //Get the size
    public int size() {
        return this.mobility;
    }
    //Get the name
    public String name() {
        return this.name;
    }
    //Get the description
    private String description() {
        return this.description;
    }
    public Place getLocation() { return this.location;}
    //Display the artifact items
    public void display() {
        System.out.println(this.name());
        System.out.println(this.description());
    }
    public int getKeyPattern() { return this.keyPattern; }
    /*print everything*/
    public void print() {
        System.out.println("Artifact: " + this.name + "\nDescription: " +this.description+ "\nValue: " +this.val+"\nMobility: " + this.mobility + "\nID: " + this.artifactID +"\nLocation: "+ this.location);
    }
}

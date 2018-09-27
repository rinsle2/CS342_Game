import java.util.Scanner;

public class Artifact {
    private String name;
    private String description;
    private int val;
    private int mobility;
    Artifact (Scanner scanner) {

    }
    public int value() {
        return this.val;
    }
    public int size() {
        return this.mobility;
    }
    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public void use() {

    }
    public void print() {
        System.out.println("Artifact: " + this.name + "\nDescription: " +this.description+ "\nValue: " +this.val+"\nMobility: " + this.mobility);
    }
}

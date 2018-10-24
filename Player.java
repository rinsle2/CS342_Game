import java.util.Scanner;

public class Player extends Character {

    public UI ui;
    public Player(Scanner in, float version, String type) {
        super(in, version, type);
    }
    public Player(int ID, String name, String description) {
        super(ID, name, description);
    }

}

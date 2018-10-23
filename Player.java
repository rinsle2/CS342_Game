import java.util.Scanner;

public class Player extends Character {

    public UI ui;
    public Player(Scanner in, float version) {
        super(in, version);
    }
    public Player(int ID, String name, String description) {
        super(ID, name, description);
    }

}

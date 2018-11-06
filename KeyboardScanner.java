import java.util.Scanner;

public class KeyboardScanner {
    private Scanner in;
    KeyboardScanner() {
        in = new Scanner(System.in);
    }
    public Scanner scanner() {
        return in;
    }
}

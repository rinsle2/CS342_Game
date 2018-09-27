//Ryan Insley rinsle2
import java.util.Scanner;
public class Direction {
    //Variables
    private int dirID;
    private int lockPattern;
    private Place begin;
    private Place end;
    private DirType direction;
    private boolean locked;
    private enum DirType {
        //Every Single Option
        NONE("",""),
        N("North", "N"),
        NE("Northeast", "NE"),
        NNE("North-Northeast", "NNE"),
        NW("Northwest", "NW"),
        NNW("North-Northwest","NNW"),
        E("East","E"),
        ENE("East-Northeast","ENE"),
        ESE("East-Southeast","ESE"),
        W("West","W"),
        WNW("West-Northwest","WNW"),
        WSW("West-Southwest","WSW"),
        S("South","S"),
        SE("Southeast","SE"),
        SSE("South-Southeast","SSE"),
        SW("Southwest","SW"),
        SSW("South-Southwest","SSW");

        private String text;
        private String abbr;
        DirType (String a, String b) {
            text = a;
            abbr = b;
        }
        public String toString() {
            return this.text;
        }

        public boolean match(String s) {
            return (s.equalsIgnoreCase(abbr) || s.equalsIgnoreCase(text));
        }
    }
    //Scanner Constructor
    public Direction(Scanner sc) {

    }
    //Default Contructor
    public Direction(int ID, Place from, Place to, int lP, String dir){
        dirID = ID;
        begin = from;
        end = to;
        direction = DirType.valueOf(dir);
        lockPattern = lP;
        locked = false;
    }
    //getters
    public int getDirID() {
        return this.dirID;
    }
    public String getDir() {
        return this.direction.toString();
    }
    //Match the Direction with the user input
    public boolean match(String dir){
        return this.direction.match(dir);
    }
    //lock the Direction
    public void lock()
    {
        this.locked=true;
    }
    public void unlock()
    {
        this.locked = false;
    }
    //check if locked
    public boolean isLocked() {
        return this.locked;
    }
    //follow the direction(shows next room if unlocked, same room otherwise)
    public Place followDirection() {
        if (!this.isLocked())
        {
            return this.end;
        }
        return this.begin;
    }
    /*Beta code: Print out the direction*/
    public void print() {
        System.out.println("Direction information:\n"  + this.dirID + "\n" + this.direction.toString() + "\n" + this.begin.getName() + "\n" + this.end.getName() + "\n" + this.locked);
    }
}

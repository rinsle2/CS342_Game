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
        NNE("North byNortheast", "NNE"),
        NW("Northwest", "NW"),
        NNW("North by Northwest","NNW"),
        E("East","E"),
        ENE("East by Northeast","ENE"),
        ESE("East by Southeast","ESE"),
        W("West","W"),
        WNW("West by Northwest","WNW"),
        WSW("West by Southwest","WSW"),
        S("South","S"),
        SE("Southeast","SE"),
        SSE("South by Southeast","SSE"),
        SW("Southwest","SW"),
        SSW("South by Southwest","SSW");

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
        this.dirID = sc.nextInt();
        this.begin = Place.getPlaceFromId(sc.nextInt());
        this.direction = DirType.valueOf(sc.next());
        this.end = Place.getPlaceFromId(sc.nextInt());
        this.lockPattern = sc.nextInt();
        //Check the lock Pattern
        if(lockPattern!= 0) {
            this.lock();
        }
        else {
            this.unlock();
        }
    }
    //getters(you will never see these)
    public int getDirID() {
        return this.dirID;
    }
    public String getDir() {
        return this.direction.text;
    }
    //Match the Direction with the user input
    public boolean match(String dir){
        return (this.direction.text.equalsIgnoreCase(dir) || this.direction.abbr.equalsIgnoreCase(dir));
    }
    //lock or unlock the Direction
    private void lock()
    {
        this.locked=true;
    }
    public void unlock()
    {
        this.locked = false;
    }
    //get the keyPattern

    public int getLockPattern() {
        return lockPattern;
    }

    //check if locked
    private boolean isLocked() {
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

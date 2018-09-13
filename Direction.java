//Ryan Insley rinsle2
public class Direction {
    //Variables
    private int dirID;
    private Place begin;
    private Place end;
    private String direction;
    private boolean locked;
    //Scanner Constructor
    public Direction(Scanner sc) {

    }
    //Default Contructor
    public Direction(int ID, Place from, Place to, String dir){
        dirID = ID;
        begin = from;
        end = to;
        direction = dir;
        if(direction.substring(0, 1).equals("L"))
            locked = true;
        else
            locked = false;
    }
    //getters
    public int getdirID() {
        return this.dirID;
    }
    public String getDir() {
        return this.direction;
    }
    //Match the Direction with the user input
    public boolean match(String dir){
        return this.direction.equalsIgnoreCase(dir);
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
        System.out.println("Direction information:\n"  + this.dirID + "\n" + this.direction + "\n" + this.begin.getName() + "\n" + this.end.getName() + "\n" + this.locked);
    }
}

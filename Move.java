public class Move {
    public MoveType type;
    public String argument;
    public enum MoveType {
        GET,
        USE,
        INVENTORY,
        LOOK,
        DROP;
    }
    Move(String a, String b){
        type = MoveType.valueOf(a);
        argument = b;
    }

}

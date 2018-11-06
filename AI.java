import java.util.Random;
//Ai uses the decision maker interfact
public class AI implements DecisionMaker{
    public Move getMove(Character c, Place p) {
        //Used for getting a direction
        Random rand = new Random();
        //Get a direction into a Move object
        Move m = new Move("go", p.hallways().get(rand.nextInt(p.hallways().size())).getDir());
        //return the move object
        return m;
    }
}

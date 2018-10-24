import java.util.Random;

public class AI implements DecisionMaker{
    public Move getMove(Character c, Place p) {
        Random rand = new Random();
        Move m = new Move("go", p.hallways().get(rand.nextInt(p.hallways().size())).getDir());
        return m;
    }
}

package gamedata.wrappers;

/**
 * 
 * @author Rica
 *
 */
public class MoveCountRuleData {
    private int myNumMoves;
    
    public MoveCountRuleData(int numMoves) {
        myNumMoves = numMoves;
        System.out.println("MoveCountRuleData: Constructor called");
    }
    
    public int getMoves() {
        return myNumMoves;
    }

}

package gameengine.player;

import gamedata.gamecomponents.Grid;
import java.util.Set;


/**
 * Defines a simple AI for playing turn-based strategy games
 * 
 * @author Jesse
 *
 */
public class SimpleAIPlayer extends Player {

    // player needs to know what pieces belong to it.
    // get a list of pieces that have available moves.
    private Set myPieces;

    public SimpleAIPlayer (int id) {
        super(id);
    }

    /**
     * 
     */
    public void play () {

    }

    @Override
    public void startTurn () {
        // TODO Auto-generated method stub
        
    }

}

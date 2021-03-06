package gamedata.events.globalaction;

import authoring_environment.GUIGrid;

/**
 * GlobalActions are a special type of action in which the Game operates on a target 
 * (a Piece, a Patch, a Player, the Game itself, etc.)
 * 
 * These are differentiated from Piece-to-Piece actions in that they have no particular 
 * actor. In addition, the doBehavior() method takes in no inputs, and can be run isolated. 
 * This simplifies the act of iterating through the list of GlobalAction objects contained in 
 * an Event to run in sequence. Instead, the GlobalAction's target is set in the constructor.
 * 
 * @author Mike Zhu
 *
 */
public abstract class GlobalAction {
	
	protected final String myDescription;
	
	protected GlobalAction(String s){
		myDescription = s;
	}
	
	public abstract void doBehavior(GUIGrid grid);
	
	@Override
	public String toString(){
		return myDescription;
	}

}

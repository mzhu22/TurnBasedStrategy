package gamedata.gamecomponents;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import gamedata.goals.*;
import gamedata.rules.*;
import gameengine.player.Player;

/**
 * Rules define how a player's turn ends Goals define whether or not the level
 * has been won
 *
 */
public class Level extends Observable {

	private Grid myGrid;
	/**
	 * Goals defining how to win the level
	 */
	private List<Goal> myGoals;
	/**
	 * Rules defining how the players turn ends
	 */
	private List<Rule> myRules;

	public Level() {
		this(null, null, null);
	}

	public Level(Grid gr, List<Goal> goals, List<Rule> rules) {
		myGrid = gr;
		myGoals = goals;
		myRules = rules;
	}

	/**
	 * Check if the level has been won after every move the player makes Returns
	 * true if the level has been won, false if it has not.
	 * 
	 * @return
	 */
	public boolean levelCompleted() {
		setChanged();
		notifyObservers();
		for (Goal g : myGoals) {
			if (g.checkGameState(this) == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check rules to see if a player's turn is over. Returns true if the turn
	 * is over, false if the turn continues.
	 * 
	 * @return
	 */
	public boolean checkTurnEnd(int numTurnsPlayed) {
		for (Rule r : myRules) {
			if (r.conditionsMet(numTurnsPlayed)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the grid contained in this level.
	 * 
	 * @return
	 */
	public Grid getGrid() {
		return myGrid;
	}

	public void addObserverTo(Observer o) {
		addObserver(o);
	}
}
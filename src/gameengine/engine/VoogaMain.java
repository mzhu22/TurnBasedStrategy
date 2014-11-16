package gameengine.engine;

import java.util.ArrayList;
import java.util.List;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Grid;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.SquareGrid;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gameengine.player.Player;

/**
 * Main method used to test Game/Player/Level/Rule/Grid etc. interaction on the back end
 * 
 * @author annamiyajima
 *
 */
public class VoogaMain {
    public static void main(String [] args){
        System.out.println("main is running");
        List<Player> myPlayers = new ArrayList<Player>();
        Player myPlayer1 = new Player();
        Player myPlayer2 = new Player();
        myPlayers.add(myPlayer1);
        myPlayers.add(myPlayer2);
        
        Grid grid1 = new SquareGrid();
        Rule rule1 = new MoveCountRule(3);
        List<Level> myLevels = new ArrayList<Level>();
        List<Rule> myRules = new ArrayList<Rule>();
        myRules.add(rule1);
        Level level1 = new Level(grid1, myRules);
        Level level2 = new Level(grid1, myRules);
        myLevels.add(level1);
        myLevels.add(level2);
        
        Game myGame = new Game(myPlayers, myLevels);
        myGame.play();
    }
}

package tests;

import gamedata.action.Action;
import gamedata.action.ActionConclusion;
import gamedata.action.ConcreteAction;
import gamedata.action.StatsSingleMultiplier;
import gamedata.action.StatsTotalLogic;
import gamedata.action.conclusions.ReceiverToInventoryConclusion;
import gamedata.events.Event;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.Inventory;
import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import gamedata.goals.Goal;
import gamedata.goals.PlayerPiecesRemovedGoal;
import gamedata.rules.MoveCountRule;
import gamedata.rules.Rule;
import gamedata.stats.Stats;
import gameengine.movement.Movement;
import gameengine.player.HumanPlayer;
import gameengine.player.Player;
import gameengine.player.SimpleAIPlayer;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import authoring_environment.GUIGrid;
import authoring_environment.SuperGrid;

/**
 * JSON Write Tester
 * 
 * @author Rica Zhang, Anna Miyajima
 *
 */
public class JSONBobTester {
	private static String DEFAULT_DUVALL = "/resources/images/rcd.png";
	private static String DEFAULT_BUNNY = "/resources/images/bbybunny.jpeg";
	private static String DEFAULT_RICA = "/resources/images/Rica.png";
	private static String DEFAULT_LAND = "/resources/images/Land.jpeg";
	private static int RANDOMIZE = 0;
	private static int DUVALL = 1;
	private static int BUNNY = 2;
	private static int RICA = 3;

	public JSONBobTester() {

	}

	// TODO: remove, used for testing purposes
	public GUIGrid createGUIGrid() {
		GUIGrid test = new GUIGrid(2, 2, 5, "square");
		return test;
	}

	// TODO: remove, used for testing purposes
	public SuperGrid createSuperGrid() {
		SuperGrid grid = new SuperGrid(2, 2, 5, "square");
		return grid;
	}

	/**
	 * Create a new game to test
	 * 
	 * @return a new default game
	 */
	public Game createNewGame() {
		System.out.println("Bob Tester: Create new game");
		List<Player> myPlayers = new ArrayList<Player>();
		Player myPlayer1 = new HumanPlayer(1);
		Player myPlayer2 = new SimpleAIPlayer(2);
		myPlayers.add(myPlayer1);
		myPlayers.add(myPlayer2);

		GUIGrid gridLevel1 = createNewGrid();
		GUIGrid gridLevel2 = createNewGrid();

		List<Event> myEvents = new ArrayList<Event>();
		
		List<Level> myLevels = new ArrayList<Level>();
		Level level1 = new Level(gridLevel1, myEvents, "Level 1", false);
		Level level2 = new Level(gridLevel2, myEvents, "Level 2", true);
		myLevels.add(level1);
		myLevels.add(level2);

		Game myGame = new Game(2, myLevels, myLevels.get(0));
		myGame.addPlayers(myPlayers);
		System.out.println(myGame);
		return myGame;
	}

	public GUIGrid createNewGrid() {
		GUIGrid grid1 = new GUIGrid(5, 5, 75, "Square Grid");

		Piece randomTemplate = createNewPiece(grid1, new Point2D.Double(0, 0), 0);
		Piece duvallTemplate = createNewPiece(grid1, new Point2D.Double(0, 0), 1);
		Piece bunnyTemplate = createNewPiece(grid1, new Point2D.Double(0, 0), 2);
		Piece ricaTemplate = createNewPiece(grid1, new Point2D.Double(0, 0), 3);
		Patch templPatch = createNewPatch(new Point2D.Double(0, 0));

		for (int x = 0; x < grid1.getNumCols(); x++) {
			for (int y = 0; y < grid1.getNumRows(); y++) {
			    Piece actual;
			    if (x == y) {
			        actual = new Piece(ricaTemplate, new Point2D.Double(x, y));
			    }
			    else {
			        actual = new Piece(randomTemplate, new Point2D.Double(x, y));
			    }
			    if (x == 0) {
			        actual.setPlayerID(2);
			    }
			    else {
			        actual.setPlayerID(1);
			    }
			    grid1.addPieceAtLoc(actual, new Point2D.Double(x, y));
			    grid1.addPatchAtLoc(templPatch, new Point2D.Double(x, y));
			}
		}

		System.out.println("Grid created: " + grid1.toString());
		return grid1;
	}

	public Piece createNewPiece(GUIGrid g, Point2D p, int type) {
		Point2D p1 = new Point2D.Double(1, 1);
		Point2D p4 = new Point2D.Double(1, 0);
		Point2D p5 = new Point2D.Double(-1, 0);

		Point2D p2 = new Point2D.Double(2, 2);
		Point2D p3 = new Point2D.Double(3, 3);

		List<Point2D> pl1 = new ArrayList<Point2D>();
		pl1.add(new Point2D.Double(-1, 0));
		pl1.add(new Point2D.Double(1, 0));
		pl1.add(new Point2D.Double(0, 1));
		pl1.add(new Point2D.Double(0, -1));

		List<Point2D> pl2 = new ArrayList<Point2D>();
		pl2.add(p4);

		List<Point2D> pl3 = new ArrayList<Point2D>();
		pl3.add(p2);
		pl3.add(p3);

		List<Point2D> pl4 = new ArrayList<Point2D>();

		Movement move = new Movement(g, pl1);

		List<Action> actions = new ArrayList<Action>();
		actions.add(createNewAction(pl2, pl4));
		actions.add(move);

		Stats s = new Stats();
		s.add("health", 20);
		Inventory i = new Inventory();

		Random r = new Random();

		int randomInt = r.nextInt(50);

		Piece piece = null;
		if (type == 0) {
		        if (randomInt % 2 == 1) {
	                        piece = new Piece("ID", "Duvall", DEFAULT_DUVALL, actions, s, p, 1,
	                                        i);
	                } else {
	                        piece = new Piece("ID", "Bunny", DEFAULT_BUNNY, actions, s, p, 1, i);
	                }
		}
		else if (type == 1) {
	              piece = new Piece("Duvall_ID", "Duvall", DEFAULT_DUVALL, actions, s, p, 1, i);
		}
		else if (type == 2) {
		    piece = new Piece("Bunny_ID", "Bunny", DEFAULT_BUNNY, actions, s, p, 1, i);
		}
		else {
		    piece = new Piece("Rica_ID", "Rica", DEFAULT_RICA, actions, s, p, 1, i);
		}
		return piece;
	}

	public Patch createNewPatch(Point2D p) {
		Patch patch = new Patch("ID", "land", DEFAULT_LAND, p);
		return patch;
	}

	public Movement createNewMovement(GUIGrid g, List<Point2D> pl2) {
		Movement m1 = new Movement(g, pl2);
		return m1;
	}

	public Action createNewAction(List<Point2D> pl1, List<Point2D> pl2) {
		StatsSingleMultiplier ssm1 = new StatsSingleMultiplier(0, "actor", "health");
		List<StatsSingleMultiplier> ssmList = new ArrayList<StatsSingleMultiplier>();
		ssmList.add(ssm1);

		List<StatsTotalLogic> stlList = new ArrayList<StatsTotalLogic>();
		StatsTotalLogic s1 = new StatsTotalLogic("actor", "health", ssmList);
		stlList.add(s1);

		ActionConclusion ac = new ReceiverToInventoryConclusion();

		Action a1 = new ConcreteAction("kill", pl1, pl2, stlList, ac);
		return a1;
	}
}

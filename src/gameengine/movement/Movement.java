package gameengine.movement;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import authoring_environment.GUIGrid;

/**
 * Defines the movement of a piece. Responsible for maintaining the behavior
 * properties of a piece and executing allowed movements
 * 
 * @author Jesse, Rica
 *
 */
public class Movement implements Action {

	/**
	 * Point2Ds referring to relative positions of movement
	 */
	private List<Point2D.Double> myMoves;

	/**
	 * Point2Ds referring to currently calculated absolute positions of movement
	 */
	private List<Point2D.Double> myAbsoluteMoves;

	/**
	 * List of Point2Ds referring to relative paths of movement
	 */
	private List<List<Point2D.Double>> myPaths;

	/**
	 * Grid the Piece is on and movement will be execute on
	 */
	private transient GUIGrid myGrid;

	/**
	 * Orientator resonsible for calculating orientations
	 */
	private transient Orientator myOrientator;

	/**
	 * Orientation of the piece (depending on last movement made)
	 */
	private double myOrientation;

	/**
	 * Name of the action for display in the Player
	 */
	private String myName;

	/**
	 * Constructor
	 * 
	 * @param g
	 *            The GUIGrid that the piece containing this movement exists on
	 * @param endPoints
	 *            Point2Ds representing all possible relative locations of
	 *            movement
	 */
	@SafeVarargs
	public Movement(List<Point2D.Double>... endPoints) {
		myOrientation = 0;
		myOrientator = new Orientator();
		myName = "Move";

		boolean first = true;
		myMoves = new LinkedList<Point2D.Double>();
		myPaths = new ArrayList<List<Point2D.Double>>();
		for (List<Point2D.Double> p : endPoints) {
			if (first) {
				myMoves = p;
				first = false;
			} else {
				myPaths.add(p);
			}
		}
	}

	/**
	 * Return absolute possible x,y coordinates of movement based on current
	 * location x,y
	 * 
	 * @param x
	 *            Current x coordinate
	 * @param y
	 *            Current y coordinate
	 * @return List of Point2D corresponding to absolute locations of movement
	 */
	public List<Point2D.Double> getPossibleLocs(int x, int y) {
		myAbsoluteMoves = new ArrayList<Point2D.Double>();
		for (Point2D.Double a : myMoves) {
			myAbsoluteMoves.add(new Point2D.Double(a.getX() + x, a.getY() + y));
		}
		return myAbsoluteMoves;
	}

	/**
	 * Checks to see if an absolute location (x,y) is a valid location for
	 * movement and that the destination is empty (no pieces overlapping)
	 * 
	 * @param x
	 *            Absolute coordinate x
	 * @param y
	 *            Absolute coordinate y
	 * @return
	 */
	public boolean isValidLocation(int x, int y) {
		for (Point2D p : myAbsoluteMoves) {
			if ((p.getX() == x && p.getY() == y)
					&& (myGrid.getPiece(new Point2D.Double(x, y)) == null)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks the collisions in the currently defined path. Checks with piece
	 * and patch collisions.
	 * 
	 * @return true or false depending on whether a collision is detected on a
	 *         path
	 */
	private boolean checkPathCollision(GUIGrid myGrid, Point2D endPoint) {
		List<Point2D.Double> path;
		boolean b = true;
		// TODO: Implement Path Collision Once Constraints are Implemented
		// Needs to find path with correct endpoint. Then check collision at
		// each point2D of the path

		/*
		 * for (Point2D p : myPaths) { if (myGrid.getPiece(p) != null) { b =
		 * false; } if (myGrid.getPatch(p) != null) { // check if these pieces
		 * can collide with terrain return false; } }
		 */
		return b;
	}

	@Override
	public List<Point2D.Double> getAbsoluteActionRange(Point2D pieceLocation) {
		return this.getPossibleLocs((int) pieceLocation.getX(),
				(int) pieceLocation.getY());
	}

	@Override
	public List<Point2D.Double> getEffectRange() {
		return new ArrayList<Point2D.Double>();
	}

	/**
	 * Contains the logic to execute the behavior of moving the piece
	 */
	@Override
	public void doBehavior(GUIGrid grid, Piece actor, Piece... receivers) {
		myGrid = grid;
		Piece p = receivers[0];
		Point2D.Double point = p.getLoc();
		if (isValidLocation((int) point.getX(), (int) point.getY())) {
			// TODO: Implement Orientation Calculation Here
			
		/*	Path path = new Path();
			double oldX = grid.findTile(point).calculatePixelLocation(grid.getTileHeight(), actor.getLoc()).getX();
			double oldY = grid.findTile(point).calculatePixelLocation(grid.getTileHeight(), actor.getLoc()).getY();
			double newX = grid.findTile(point).calculatePixelLocation(grid.getTileHeight(), point).getX();
			double newY = grid.findTile(point).calculatePixelLocation(grid.getTileHeight(), point).getY();
			
	
			double oldX = actor.getLoc().getX()*grid.getTileHeight()-(grid.getTileHeight()/2);
			double oldY = actor.getLoc().getY()*grid.getTileHeight()+(grid.getTileHeight()/2);
			double newX = point.getX()*grid.getTileHeight()-(grid.getTileHeight()/2);
			double newY = point.getY()*grid.getTileHeight()+(grid.getTileHeight()/2);
			
			System.out.println("MOVING FROM X:"+oldX + " Y:"+oldY);
			System.out.println("MOVING TO X:" + newY + " Y" + newY);
			path.getElements().add(new MoveTo(oldX,oldY));
			path.getElements().add(new LineTo(newX,newY));
			
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(4000));
			pathTransition.setPath(path);
			pathTransition.setNode(grid.findTile(actor.getLoc()).getPieceImage());
			pathTransition.setOrientation(PathTransition.OrientationType.NONE);
			pathTransition.setCycleCount(1);
			pathTransition.setAutoReverse(true);
			pathTransition.play();*/
			
			actor.setLoc(point);
			grid.repopulateGrid();
		}
	}

	@Override
	public String toString() {
		String str = "MOVEMENT: moves - ";
		for (Point2D.Double pt : myMoves) {
			str += "(" + pt.getX() + "," + pt.getY() + ")";
		}
		str += " paths - ";
		for (List<Point2D.Double> ptlst : myPaths) {
			str += "[";
			for (Point2D.Double pt : ptlst) {
				str += "(" + pt.getX() + "," + pt.getY() + ")";
			}
			str += "]";
		}
		return str;
	}

	@Override
	public List<Point2D.Double> getActionRange() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Getter method for the list of relative moves. This method is needed by
	 * the PieceTypeEditor, so do not delete it!
	 * 
	 * @return : List of locations a piece can move to relative to its current
	 *         location.
	 */
	public List<Point2D.Double> getRelativeMoves() {
		return myMoves;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public List<Double> getAbsoluteEffectRange(Point2D pieceLocation) {
		return new ArrayList<Point2D.Double>();
	}
}

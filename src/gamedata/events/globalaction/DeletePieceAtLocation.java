package gamedata.events.globalaction;

import java.awt.geom.Point2D;
import java.util.List;

import authoring_environment.GUIGrid;
import gamedata.gamecomponents.Game;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Piece;

/**
 * Deletes a piece at a specified point
 * @author Mike Zhu
 *
 */
public class DeletePieceAtLocation extends GlobalAction {	
    
    private Point2D.Double myLoc;
	
    /**
     * Make sure you construct this referring to the piece that you want to delete rather than 
     * creating a new piece because the grid will try to look for that piece to delete when the
     * method is called
     * @param game
     * @param name of type of piece to delete
     */
    public DeletePieceAtLocation(Point2D.Double point) {
        super(String.format("Delete Piece at position %f %f", point.getX(), point.getY()));
        myLoc = point;
    }

    @Override
    public void doBehavior(GUIGrid myGrid) {
    	myGrid.removePieceAtCoordinate(myLoc);
    }
}

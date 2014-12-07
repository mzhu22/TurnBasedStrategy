package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import authoring.data.PatchData;
import authoring.data.PatchTypeData;
import authoring.data.PieceData;
import authoring.data.PieceTypeData;

//TODO: REMOVE THE DUPLICATED CODE. SO MUCH.
/**
 * Authoring, engine, and player may all use this grid!!
 * 
 * @author Jennie Ju
 *
 */

public class GUIGrid extends SuperGrid implements Observer {

	private PieceData myPieceData;
	private PatchData myPatchData;

	public GUIGrid() {
		super();
	}

	public GUIGrid(int cols, int rows, double tileSize, String shape) {
		super(cols, rows, tileSize, shape);
		myPieceData = new PieceData();
		myPatchData = new PatchData();
	}

	public GUIGrid(int cols, int rows, double tileSize, String shape,
			PieceData pieceData, PatchData patchData) {
		super(cols, rows, tileSize, shape);
		myPieceData = pieceData;
		myPatchData = patchData;
	}

	public GUIGrid(int cols, int rows, double tileSize, String shape,
			GUIGrid copyGrid) {
		super(cols, rows, tileSize, shape);
		myPieceData = copyGrid.myPieceData;
		myPatchData = copyGrid.myPatchData;
		// removeRunOffPieces(cols, rows, myPieceData);
		// removeRunOffPieces(cols, rows, myPieceData);
	}

	/**
	 * Returns number of rows
	 * 
	 * @return int number of rows
	 */
	public int getNumRows() {
		return super.myHeight;
	}

	/**
	 * Returns number of columns
	 * 
	 * @return int number of columns
	 */
	public int getNumCols() {
		return super.myWidth;
	}

	public void addPiece(Piece pieceType, Point2D loc) {
		Piece clone = new Piece(pieceType, loc);
		myPieceData.add(clone);
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.addPieceImage(clone.getImageView());

		System.out.println(myPieceData.getData().size());
	}

	public void addPatch(Patch patchType, Point2D loc) {
		Patch clone = new Patch(patchType, loc);
		myPatchData.add(clone);
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.addPatchImage(clone.getImageView());

		System.out.println(myPatchData.getData().size());
	}
	
	/**
	 * Removes a piece at the given coordinates.
	 * NOTE: Point2D coordinates given as
	 * X = column number, Y = row number 
	 * @param coor - Point2D containing coordinates of
	 * the piece to remove given as [Col, Row]
	 */
	public void removePieceAtCoordinate(Point2D coor) {
		Piece toRemove = getPiece(coor);
		removePiece(toRemove);
	}

	/**
	 * Removes a piece at the given coordinates.
	 * NOTE: Point2D coordinates given as
	 * X = column number, Y = row number 
	 * @param coor - Point2D containing coordinates of
	 * the piece to remove given as [Col, Row]
	 */
	public void removePatchAtCoordinate (Point2D coor) {
		Patch toRemove = getPatch(coor);
		removePatch(toRemove);
	}

	private void replacePieceType (Piece pieceType) {
		List<Point2D> pointsToReplace = myPieceData.replace(pieceType);
		for (Point2D loc : pointsToReplace) {
			SuperTile tile = super.findClickedTile(loc);
			tile.addPatchImage(pieceType.getImageView());
		}
	}


	private void replacePatchType (Patch patchType) {
		List<Point2D> pointsToReplace = myPatchData.replace(patchType);
		// System.out.println(pointsToReplace.toString());
		for (Point2D loc : pointsToReplace) {
			SuperTile tile = super.findClickedTile(loc);
			tile.addPatchImage(patchType.getImageView());
		}
	}
	
	private void removePiece (PieceTypeData typeData) {
		List<Point2D> pointsToRemove = myPieceData.removeUnknown(typeData.getIdSet());
		for (Point2D loc : pointsToRemove) {
			SuperTile tile = super.findClickedTile(loc);
			tile.removePieceImage();
		}
	}

	private void removePatch(PatchTypeData typeData) {
		List<Point2D> pointsToRemove = myPatchData.removeUnknown(typeData
				.getIdSet());
		for (Point2D loc : pointsToRemove) {
			SuperTile tile = super.findClickedTile(loc);
			tile.removePatchImage();
		}
	}

	/**
	 * Returns the piece at loc
	 * 
	 * @param loc
	 * @return
	 */
	public Piece getPiece(Point2D loc) {
		for (Piece p : myPieceData.getData()) {
			if ((p.getLoc().getX() == loc.getX())
					& (p.getLoc().getY() == loc.getY())) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Returns the patch at loc
	 * 
	 * @param loc
	 * @return
	 */
	public Patch getPatch(Point2D loc) {
		for (Patch p : myPatchData.getData()) {
			if ((p.getLoc().getX() == loc.getX())
					& (p.getLoc().getY() == loc.getY())) {
				return p;
			}
		}
		return null;
	}

	public void removePiece(Piece p) {
		SuperTile currentTile = findClickedTile(p.getLoc());
		myPieceData.remove(p);
		currentTile.clearPieceImage();
	}
	
	public void removePatch (Patch p) {
		myPatchData.remove(p);
		SuperTile currentTile = findClickedTile(p.getLoc());
		currentTile.clearPieceImage();
	}

	/**
	 * Removes a piece at the given coordinates.
	 * NOTE: Point2D coordinates given as
	 * X = column number, Y = row number 
	 * @param coor - Point2D containing coordinates of
	 * the piece to remove given as [Col, Row]
	 */
	public void removePiece(Point2D coor) {
		
	}
	
	/**
	 * Removes a patch at the given coordinates.
	 * NOTE: Point2D coordinates given as
	 * X = column number, Y = row number 
	 * @param coor - Point2D containing coordinates of
	 * the patch to remove given as [Col, Row]
	 */
	public void removePatch(Point2D coor) {
		
	}
	
	/**
	 * Gets Pieces that have been tagged for removal
	 * 
	 * @return
	 */
	public List<Piece> getRemovedPieces() {
		List<Piece> l = new ArrayList<Piece>();
		for (Piece p : myPieceData.getData()) {

			// TODO: FOR TESTING ONLY
			if (p.getStats().getValue("health") <= 0) {
				p.markForRemoval();
			}

			if (p.shouldRemove()) {
				l.add(p);
			}
		}
		return l;
	}

	/**
	 * Returns all pieces that belong to a given player
	 * 
	 * @param playerId
	 *            - ID of player
	 * @return List of pieces belonging to the player
	 */
	public List<Piece> getPlayerPieces(int playerId) {
		List<Piece> l = new ArrayList<Piece>();
		for (Piece p : myPieceData.getData()) {
			if (p.getPlayerID() == playerId) {
				l.add(p);
			}
		}
		return l;
	}
	
	public void repopulateGrid() {
		this.initGridTiles(this.myShape);
		for (Patch p : myPatchData.getData()) {
			this.addPatch(p, p.getLoc());
		}
		for (Piece p : myPieceData.getData()) {
			this.addPiece(p, p.getLoc());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof PieceTypeData) {
			PieceTypeData typeData = (PieceTypeData) o;
			if (arg == null) {
				// TODO : FIX!
				removePiece(typeData);
			}
			if (arg instanceof Piece) {
				Piece pieceType = (Piece) arg;
				replacePieceType(pieceType);
			}
		}
		if (o instanceof PatchTypeData) {
			PatchTypeData typeData = (PatchTypeData) o;
			if (arg == null) {
				// TODO : FIX!
				removePatch(typeData);
			}
			if (arg instanceof Patch) {
				Patch patchType = (Patch) arg;
				replacePatchType(patchType);
			}
		}
	}

	public void addPieceToTile(Piece pieceType, Point2D loc) {
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.addPieceImage(pieceType.getImageView());

	}

	public void addPatchToTile(Patch patchType, Point2D loc) {
		SuperTile myTile = myGrid.get((int) loc.getY()).get((int) loc.getX());
		myTile.addPatchImage(patchType.getImageView());
	}

	// TODO: separate the two types of mouse events (drag and click)
	public void paneSetOnMouseEvent(EventHandler<MouseEvent> handler) {
		myPane.setOnMouseClicked(handler);
		myPane.setOnMouseDragged(handler);
	}
}

package authoring_environment;

import gamedata.gamecomponents.Patch;
import gamedata.gamecomponents.Piece;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import authoring.data.PatchData;
import authoring.data.PieceData;


/**
 * Authoring, engine, and player may all use this grid!!
 * 
 * @author Jennie Ju
 *
 */
public class GUIGrid extends SuperGrid {

    private PieceData myPieceData;
    private PatchData myPatchData;

    public GUIGrid () {
        super();
    }

    public GUIGrid (int cols, int rows, int tileSize, String shape) {
        super(cols, rows, tileSize, shape);
        myPieceData = new PieceData();
        myPatchData = new PatchData();
    }

    /**
     * Returns number of rows
     * 
     * @return int number of rows
     */
    public int getNumRows () {
        return super.myRows;
    }

    /**
     * Returns number of columns
     * 
     * @return int number of columns
     */
    public int getNumCols () {
        return super.myCols;
    }

    // TODO: set image within tile at this location
    public void addPiece (Piece pieceType, Point2D loc) {
        Piece clone = new Piece(pieceType, loc);
        myPieceData.add(clone);
        SuperTile myTile = myGrid.get((int) loc.getX()).get((int) loc.getY());
        myTile.addPieceImage(clone.getImageView());
    }

    // TODO: set image within tile at this location
    public void addPatch (Patch patchType, Point2D loc) {
        Patch clone = new Patch(patchType, loc);
        myPatchData.add(clone);
        SuperTile myTile = myGrid.get((int) loc.getX()).get((int) loc.getY());
        myTile.addPatchImage(clone.getImageLocation());
    }

    public void removePiece (Piece p) {
        // remove piece at this location from data and tiles
    }

    public void removePatch (Patch p) {
        // remove patch at this location from data and tiles
    }

    public void removePiece (Point2D loc) {
        // remove piece at this location from data and tiles
    }

    public void removePatch (Point2D loc) {
        // remove patch at this location from data and tiles
    }

    public void removePieceType (Piece pieceType) {
        // remove all instances of this type of piece
    }

    public void removePatchType (Patch patchType) {
        // remove all instances of this type of patch
    }

    public Piece getPiece (Piece p) {
        return null;
    }

    public Patch getPatch (Patch p) {
        return null;
    }

    public Piece getPiece (Point2D loc) {
        return null;
    }

    public Patch getPatch (Point2D loc) {
        return null;
    }

    public List<Piece> getPiece (int x, int y) {
        return null;
    }

    public List<Patch> getPatch (int x, int y) {
        return null;
    }

    public PieceData getPieces () {
        return myPieceData;
    }

    public PatchData getPatches () {
        return myPatchData;
    }

}

package authoring.concretefeatures;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import authoring.data.PatchInstanceData;
import authoring.data.PieceInstanceData;
import authoring_environment.GridView;
import authoring_environment.JennieGrid;
import authoring_environment.SandyGrid;
import authoring_environment.SandyGridView;
import authoring_environment.ShapeGrid;
import authoring_environment.SuperGrid;
import authoring_environment.WorkspaceView;

/**
 * 
 * @author Rica
 *
 */
public class LevelEditor {
    private static final int DEFAULT_TILE_SIZE = 100;
	private static final int MIN_TILE_SIZE = 40;
    private static final int GRID_VIEW_HEIGHT = 400;
    private static final int GRID_VIEW_WIDTH = 800;
    
    private WorkspaceView myWorkspaceView;
    private String levelName;
    
    private String myGridType;
    private int myRows;
    private int myCol;
    private SuperGrid shapeGrid;
    private int tileSize;
    
    private PieceInstanceData pieceData = new PieceInstanceData();
    private PatchInstanceData patchData = new PatchInstanceData();
    
    /**
     * Upon construction, makes a grid of the specified type and adds it to the Workspace Tab
     * @param wsView
     * @param name
     * @param gridType
     * @param row
     * @param col
     */
    public LevelEditor(WorkspaceView wsView, String gridType, String name, int row, int col) {
        myWorkspaceView = wsView;
        myGridType = gridType;
        levelName = name;
        myRows = row;
        myCol = col;
        tileSize = getPrefTileSize(myRows,myCol);
        
        addWorkspaceTab(shapeGrid, levelName,gridType);
    }
    

    private void addWorkspaceTab(SuperGrid superGrid, String name,String shape) {
        BorderPane bPane = new BorderPane();
 
        GridView gridView = new GridView(GRID_VIEW_WIDTH, GRID_VIEW_HEIGHT,
        											DEFAULT_TILE_SIZE,shape);
        bPane.setRight(gridView);
        
   
        
        Tab tab = new Tab();
        tab.setContent(bPane);
                        
        myWorkspaceView.addGrid(superGrid);
        myWorkspaceView.addNextTab(tab, name);
    }
    
    private int getPrefTileSize (int gridWidthNumber, int gridHeightNumber) {
        int calculatedTileSize = Math.max(GRID_VIEW_WIDTH
                                          / gridWidthNumber, GRID_VIEW_HEIGHT/ gridHeightNumber);
        int tileSize = (calculatedTileSize < MIN_TILE_SIZE) ? MIN_TILE_SIZE
                                                           : calculatedTileSize;
        return tileSize;
    }
}

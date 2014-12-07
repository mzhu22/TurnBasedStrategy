package authoring_environment;

import java.awt.geom.Point2D;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class RangeGridView extends ScrollPane{
	private int myViewWidth;
	private int myViewHeight;
	private int myTileSize;
	private String myShape;
	private SuperGrid myGrid;
	
	public RangeGridView(int viewWidth, int viewHeight, int tileSize,
			String shape, List<Point2D> range) {
		myViewWidth=viewWidth;
		myViewHeight=viewHeight;
		myTileSize=tileSize;
		myShape=shape;
		this.setPrefSize(viewWidth, viewHeight);
		this.setMaxSize(viewWidth, viewHeight);
		this.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		if ((range==null) || (range.size()==0)){
			myGrid = new RangeGrid(viewWidth/tileSize,viewHeight/tileSize,
										tileSize,shape,range);
		}else{
			Point2D minGridSize=cacluateGridSize(range);
			myTileSize=getPrefTileSize((int)minGridSize.getX(),(int)minGridSize.getY(),
										myTileSize);
			myGrid = new RangeGrid((int)minGridSize.getX(),(int)minGridSize.getY(),
					tileSize,shape,range);
		}
			myGrid.displayPane(this);
	}

	public void update(int width, int height,List<Point2D> range){
		this.setContent(null);
		myTileSize=getPrefTileSize(width,height,myTileSize);
		myGrid=new RangeGrid(width,height,
				myTileSize,myShape,range);
		myGrid.displayPane(this);
	}

    private int getPrefTileSize (int col,int row,int minTileSize) {
        int calculatedTileSize = Math.max(myViewWidth
                                          / col, myViewHeight / row);

        int tileSize = (calculatedTileSize < minTileSize) ? minTileSize
                                                           : calculatedTileSize;
        return tileSize;
    }
    
    private Point2D cacluateGridSize (List<Point2D> range) {
        double maxX = 0;
        double maxY = 0;
        for (Point2D point : range) {
            if (Math.abs(point.getX()) > maxX) {
                maxX = Math.abs(point.getX())*2+1;
            }
            if (Math.abs(point.getY()) > maxY) {
                maxY = Math.abs(point.getY())*2+1;
            }
        }
        return new Point2D.Double(maxX, maxY);
    }

}
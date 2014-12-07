package authoring_environment;

import java.awt.geom.Point2D;
import java.util.List;

import javafx.scene.control.ScrollPane;

public class RangeGridView extends ScrollPane{
	private static final int MIN_TILESIZE = 50;
	private int myViewWidth;
	private int myViewHeight;
	private double myTileSize;
	private String myShape;
	private RangeGrid myGrid;
	
	public RangeGridView(int viewWidth, int viewHeight, double tileSize,
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
			myGrid = new RangeGrid((int)Math.round(myViewWidth/myTileSize),
					(int)Math.round(myViewHeight/myTileSize),
					myTileSize,shape,range);
		}else{
			Point2D minGridSize=cacluateGridSize(range);

			myTileSize=getPrefTileSize((int)minGridSize.getX(),(int)minGridSize.getY(),
										myTileSize);
			myGrid = new RangeGrid((int)minGridSize.getX(),(int)minGridSize.getY(),
					myTileSize,shape,range);
		}
			myGrid.displayPane(this);
	}

	public void update(int width, int height,List<Point2D> range){
		this.setContent(null);
		Point2D minGridSize=cacluateGridSize(range);
		int minX=(int) minGridSize.getX();
		int minY=(int) minGridSize.getY();
		int prefWidth=Math.max(width, minX);
		int prefHeight=Math.max(height, minY);
		myTileSize=getPrefTileSize(prefWidth,prefHeight,MIN_TILESIZE);
		myGrid=new RangeGrid(prefWidth,prefHeight,
				myTileSize,myShape,range);
		myGrid.displayPane(this);
	}

    private double getPrefTileSize (int col,int row,double minTileSize) {
        int calculatedTileSize = Math.max(myViewWidth
                                          / col, myViewHeight / row);
        double tileSize = (calculatedTileSize < minTileSize) ? minTileSize
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

	public List<Point2D> returnSelectedList() {
		return  myGrid.rangeSelectedList();
	}
	
	public RangeGrid getGrid(){
		return myGrid;
	}

}

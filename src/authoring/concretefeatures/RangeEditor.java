package authoring.concretefeatures;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.Grid;
import authoring_environment.GridView;

/**
 * Popup GUI element that allows the user to specify the size of the grid, selects the
 * tiles and returns the list of relative coordination of selected tiles.
 * @author huangmengen
 *
 */
public class RangeEditor extends PopupWindow{
	private static final String CUSTOM = "Custom";

	private static final String RADIUS = "Radius";

	private static final String COLUMN_ROW_CROSS = "Column & Row Cross";

	private static final String ROW = "Row";

	private static final String COLUMN = "Column";
	
	private static final String NAME = "Range Editor";

	private static final int MIN_TILE_SIZE=30;
	private static final int RANGE_EDITOR_HEIGHT = 800;
	private static final int RANGE_EDITOR_WIDTH = 600;
	private int myGridLength=RANGE_EDITOR_WIDTH-100;
	private int myTileSize=40;
	
	private int myGridWidthNumber;
	private int myGridHeightNumber;
	private RangeGrid mySampleGridView;
	private List<Point2D> selectedList;
	
	
	public RangeEditor(){
		setHeight(RANGE_EDITOR_HEIGHT);
		setWidth(RANGE_EDITOR_WIDTH);
		setTitle(NAME);
		selectedList=new ArrayList<Point2D>();
		initialize();
		mySampleGridView=new RangeGrid(myGridLength, 
								myGridLength,
								myTileSize);
	}
	@Override
	protected void initialize() {
		VBox box= new VBox();
		
		VBox selection=new VBox();
		selection.setMinHeight(50);
		HBox sizeChooser=new HBox();
		sizeChooser.setMinHeight(50);


		//Range Selections
		Label targetLabel = new Label("Select Range");
		ChoiceBox<String> targetChoice = new ChoiceBox<>();
		targetChoice.getItems().addAll(COLUMN,ROW,COLUMN_ROW_CROSS,
										RADIUS,CUSTOM);
		targetChoice.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				switch(newValue){
				case COLUMN:
					selectedList=mySampleGridView.getCenterColumn();
//				case INIT_TYPE_PROPORTION:
//					initProportionalCells(parser);
//					break;
//				case INIT_TYPE_SPECIFIED:
//					initSpecificCells(parser, gridWidth, gridHeight);
//					break;
				default:
//					selectedList=mySampleGridView.getCenterColumn();
					}
			}
			
		});
		selection.getChildren().addAll(targetLabel,targetChoice);
		
		//Select Button
		Button select=new Button("Select");

		//generate default grid
//		GridView grid=new GridView(myGridWidth, myGridWidth, myTileSize);

		//Choose the Size
		VBox horizontal=new VBox();
		VBox times=new VBox();
		VBox vertical=new VBox();
		Label HRadiusLabel = new Label("Horizontal Radius     ");
		Label multiply= new Label("     X     ");
		Label VRadiusLabel = new Label("Vertical Radius");
		TextField HRadius = new TextField();
		TextField VRadius = new TextField();
		HRadius.setMaxWidth(120);
		VRadius.setMaxWidth(120);
		VRadius.setLayoutX(100);
		
		horizontal.getChildren().addAll(HRadiusLabel,HRadius);
		times.getChildren().add(multiply);
		vertical.getChildren().addAll(VRadiusLabel,VRadius);

		Button enter=new Button("Enter");
		enter.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				myGridWidthNumber=Integer.parseInt(HRadius.getText())*2+1;
				myGridHeightNumber=Integer.parseInt(VRadius.getText())*2+1;
				int calculatedTileSize=Math.max(myGridLength/myGridWidthNumber, 
												myGridLength/myGridHeightNumber);
				
				myTileSize=(calculatedTileSize<MIN_TILE_SIZE)? MIN_TILE_SIZE:calculatedTileSize;
				
				box.getChildren().clear();
				mySampleGridView=new RangeGrid(myGridLength,myGridLength,myTileSize);
				mySampleGridView.update(myGridWidthNumber, myGridHeightNumber,myTileSize);
				box.getChildren().addAll(selection,sizeChooser,enter,mySampleGridView,select);
			}	
		});
		
		select.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
//				mySampleGridView.getSelectedList();
				
				//Get Selected Tiles
				//Calculate appropriate respective locations
				//Add range to action
			}
		});
		
		enter.setLayoutX(500);
		sizeChooser.getChildren().addAll(horizontal,times,vertical);
		
	
		box.getChildren().addAll(selection,sizeChooser,enter,select);
		setScene(new Scene(box));
		
	}
	

}

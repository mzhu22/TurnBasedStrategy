package authoring.eventeditor;

import gamedata.events.GlobalAction;
import gamedata.events.globalaction.CreatePiece;
import gamedata.events.globalaction.DeletePiece;
import gamedata.events.globalaction.LevelChange;
import gamedata.events.globalaction.EndTurn;
import gamedata.gamecomponents.IChangeGameState;
import gamedata.gamecomponents.IHasStats;
import gamedata.gamecomponents.Piece;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import authoring.data.EventsDataWrapper;
import utilities.ClassGrabber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NewActionController implements Initializable{

	//TODO: How to pass in the list of all Conditions?

	@FXML
    private TextField myYField;
    @FXML
    private HBox myUnitActionsBox;
    @FXML
    private ChoiceBox<String> myRefTypeBox;
    @FXML
    private ChoiceBox<String> actionChoiceBox;
    @FXML
    private TextField myNextLevelField;
    @FXML
    private TextField myXField;
    @FXML
    private Button myDoneButton;
    @FXML
    private TextField myNameField;
    @FXML
    private ChoiceBox<IHasStats> myRefNameBox;
	
	private List<Class> actionList;

	private Consumer<GlobalAction> myDoneLambda;
	private EventsDataWrapper myData;
	private IChangeGameState myState;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		//TODO: Remove print stack traces. Add in error windows
		actionList = new ArrayList<>();
		try {
			actionList = Arrays.asList(ClassGrabber.getClasses("gamedata.events.globalaction"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> displayList = new ArrayList<>();
		for(Class<?> c: actionList){
			displayList.add(c.toString());
		}
		displayList = trimClassList(displayList);

		actionChoiceBox.getItems().addAll(displayList);

		actionChoiceBox.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedType) -> showActionEditorPane());
	}
	
	//TODO: Refactor this to be less gross. SO MUCH REPEATED CODE
	@FXML
	private void handleDoneButton(){
		int idx = actionChoiceBox.getSelectionModel().getSelectedIndex();
		Class<?> c = actionList.get(idx);

		if(c.equals(CreatePiece.class)){
			Piece piece = (Piece) myRefNameBox.getSelectionModel().getSelectedItem();
			double x = Double.parseDouble(myXField.getText());
			double y = Double.parseDouble(myYField.getText());
			Point2D.Double point = new Point2D.Double(x,y);
			GlobalAction action = new CreatePiece(myNameField.getText(), piece, point);
			myDoneLambda.accept(action);
		}
		else if (c.equals(DeletePiece.class)){
			
		}
		else if (c.equals(LevelChange.class)){
			GlobalAction action = new LevelChange(myNameField.getText(), myState, myNextLevelField.getText());
			myDoneLambda.accept(action);
		}
		else if (c.equals(EndTurn.class)){
			GlobalAction action = new EndTurn(myNameField.getText(), myState);
			myDoneLambda.accept(action);
		}
	}

	private List<String> trimClassList(List<String> actionList) {
		List<String> displayList = new ArrayList<>();
		for(String s: actionList){
			String trimmed = trimClassPaths(s);
			displayList.add(trimmed);
		}
		return displayList;
	}

	//TODO: determine how to distinguish between different types of actions
	private void showActionEditorPane(){
		myNextLevelField.setVisible(false);
		myUnitActionsBox.setVisible(false);
		
		int idx = actionChoiceBox.getSelectionModel().getSelectedIndex();
		Class<?> c = actionList.get(idx);

		
		/**
		 * If statements to choose which Condition Editor to pull up
		 */
		if(c.equals(CreatePiece.class)){
			myUnitActionsBox.setVisible(true);
		}
		else if (c.equals(DeletePiece.class)){
			myUnitActionsBox.setVisible(true);
		}
		else if (c.equals(LevelChange.class)){
			myNextLevelField.setVisible(true);
		}
		else if (c.equals(EndTurn.class)){

		}
	}

	/**
	 * Removes the classpath prefixes for each Condition name
	 * @param s
	 */
	private String trimClassPaths(String s){
		int idx = 0;
		for(int i=s.length()-1; i>=0; i--){
			if(s.charAt(i)=='.'){
				idx = i;
				break;
			}
		}
		String trimmed = s.substring(idx+1);
		return trimmed;
	}

	public void loadLambda(Consumer<GlobalAction> okLambda) {
		myDoneLambda = okLambda;
	}

	public void loadData(EventsDataWrapper data) {
		myData = data;
		myRefNameBox.getItems().addAll(myData.getPieceTypes());

	}

	public void loadState(IChangeGameState state) {
		myState = state;
	}
}

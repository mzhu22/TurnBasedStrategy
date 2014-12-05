/**
 * Sample Skeleton for "simple.fxml" Controller Class
 * Use copy/paste to copy paste this code into your favorite IDE
 **/

package authoring.eventeditor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

//TODO: RIGHT NOW THIS CLASS ALSO HOLDS A LOT OF DATA. WE SHOULD NOT DO THAT
public class EventEditorController implements Initializable {

	//Lists
	@FXML
	private ListView<String> conditionsListView;
	@FXML
	private ListView<String> actionsListView;
	
	//Buttons
	@FXML
	private Button newCondition;
	@FXML
	private Button delCondition;
	@FXML
	private Button delAction;
	@FXML
	private Button newAction;
	
	//Labels
	@FXML
	private Label conditionName;
	
	private EventEditorMain myMain;
	private final ObservableList<String> conditionsList = FXCollections.observableArrayList();
	private final ObservableList<String> actionsList = FXCollections.observableArrayList();
	

	public EventEditorController(){
		
	}
	
	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		conditionsListView.setItems(conditionsList);
		actionsListView.setItems(actionsList);
		
		conditionsList.addAll("Test 1", "Test 2");
		actionsList.addAll("Action 1", "Action 2");			
		
		conditionsListView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, selectedValue) -> showDetails(selectedValue));
	}
	
	public void setMain(EventEditorMain eem){
		myMain = eem;
	}
	
	@FXML
	private void showDetails(String s){
		conditionName.setText(s);
	}
	
	@FXML
	private void handleNewCondition() throws IOException{
		myMain.showNewConditionWindow();
	}
	
	@FXML
	private void handleDelCondition(){
	    int delIdx = conditionsListView.getSelectionModel().getSelectedIndex();
	    conditionsListView.getItems().remove(delIdx);
	}
	
	@FXML
	private void handleNewAction() throws IOException{
		myMain.showNewActionWindow();
	}
	
	@FXML
	private void handleDelAction(){
		int delIdx = actionsListView.getSelectionModel().getSelectedIndex();
	    actionsListView.getItems().remove(delIdx);
	}
	
	@FXML
	private void handleClose(){
		
	}
}
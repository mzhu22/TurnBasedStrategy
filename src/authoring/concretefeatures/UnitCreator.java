package authoring.concretefeatures;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;
import authoring_environment.LibraryView;

public class UnitCreator extends PopupWindow{
	
	public static final int HEIGHT = 800;
	public static final int WIDTH = 600;
	public static final String NAME = "Unit Creator";
	private LibraryView myLibrary;
	
	public UnitCreator(LibraryView library){
		myLibrary = library;
		setHeight(HEIGHT);
		setWidth(WIDTH);
		setTitle(NAME);
		initialize();
	}
	
	@Override
	protected void initialize(){
		VBox box = new VBox();
		
		HBox names = new HBox();
		HBox images = new HBox();
		
		Label nameLabel = new Label("Name");
		TextField unitName = new TextField();
		names.getChildren().addAll(nameLabel, unitName);
		
		Label loadLabel = new Label("Unit image");
		Button loadImage = new Button("Load image");
		images.getChildren().addAll(loadLabel, loadImage);
		
		VBox modList = new ModulesList();
		
		Button goButton = new Button("Create new unit template");
		goButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent click) {
				myLibrary.addToLibrary(unitName.getText(), 0);
			}
			
		});
		box.getChildren().addAll(names, loadImage, modList, goButton);
	
		setScene(new Scene(box));
	}
}
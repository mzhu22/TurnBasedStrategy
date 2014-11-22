package authoring.concretefeatures.menus;

import gamedata.JSONManager;
import gamedata.gamecomponents.Game;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import authoring_environment.LibraryView;
import java.io.File;

/**
 * Menu selection to save game to JSON or read game from JSON
 * @author Rica
 *
 */
public class JSONBob extends Menu {
    private LibraryView myLibrary;
    private static final String NAME = "JSON";
    private static final String ITEM_1 = "Save game";
    private static final String ITEM_2 = "Load game";
    public static final String SAVE_PREFIX = "Save as file: ";

    /**
     * Constructs menu items
     * @param library
     */
    public JSONBob(LibraryView library) {
        super(NAME);
        myLibrary = library;
        MenuItem JSONCreator = new MenuItem(ITEM_1);
        MenuItem JSONLoader = new MenuItem(ITEM_2);
        setAction(JSONCreator);
        getItems().addAll(JSONCreator, JSONLoader);
    }

    /**
     * When you click on the JSON creator menu item, this sends it to the handler
     * @param jSONCreator
     */
    private void setAction (MenuItem jSONCreator) {
        jSONCreator.setOnAction(event -> handle());
    }

    /**
     * Creates a file chooser and uses that file path to save a JSON file to 
     */
    private void handle () {
        Stage myStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(myStage);
        JSONManager myGameBuilder = new JSONManager();
        JSONBobTester jb = new JSONBobTester();
        myGameBuilder.writeToJSON(jb.createNewGame(), file.getAbsolutePath());
    }
}
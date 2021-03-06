package fxml_main;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * 
 * @author Sandy Lee, Jennie Ju
 * @param <T>
 *
 */
public abstract class GridComponentAbstCtrl<T> {
    protected VBox myVBox;
    protected ScrollPane myPropertiesSPane;
    protected GUIGridReference myGridReference;
    protected Map<T, HBox> myEntryMap;

    protected GridComponentAbstCtrl (VBox vbox, ScrollPane propertiesSPane,
                                     GUIGridReference gridRef) {
        myVBox = vbox;
        myPropertiesSPane = propertiesSPane;
        myGridReference = gridRef;
        myEntryMap = new HashMap<T, HBox>();
        initGlobalControls();
    }

    private void initGlobalControls () {
        HBox btnBox = new HBox();
        Button newBtn = new Button("New");
        Button editBtn = new Button("Edit");
        Button delBtn = new Button("Delete");
        initGlobalNewBtn(newBtn);
        initGlobalEditBtn(editBtn);
        initGlobalDelBtn(delBtn);
        btnBox.getChildren().addAll(newBtn, editBtn, delBtn);
        myVBox.getChildren().addAll(btnBox, new Separator());
    }

    /**
     * Sets the on-click actions for the New component button
     * 
     * @param newBtn - button allowing for creation of new components
     */
    protected abstract void initGlobalNewBtn (Button newBtn);

    /**
     * Sets the on-click actions for the Edit individual components button
     * 
     * @param editBtn - button allowing editing of components on the grid
     */
    protected abstract void initGlobalEditBtn (Button editBtn);

    protected abstract void initGlobalDelBtn (Button delBtn);

    protected void addEntry (T entry) {
    	HBox entryHolderBox = new HBox();
        HBox entryCompleteBox = makeCompleteEntryBox(entry);
        myEntryMap.put(entry, entryHolderBox);
        entryHolderBox.getChildren().add(entryCompleteBox);
        myVBox.getChildren().add(entryHolderBox);
    }
    
    protected HBox makeCompleteEntryBox(T entry) {
    	HBox entryBox = makeEntryBox(entry);
        HBox entryCtrls = initEntryControls(entry);
        HBox completeEntryBox = new HBox();
        completeEntryBox.getChildren().addAll(entryCtrls, entryBox);
        return completeEntryBox;
    }
    
    protected HBox initEntryControls (T entry) {
        HBox btnBox = new HBox();
        Button editBtn = new Button("Edit");
        Button delBtn = new Button("Delete");
        initEntryEditBtn(entry, editBtn);
        initEntryDelBtn(entry, delBtn);
        btnBox.getChildren().addAll(editBtn, delBtn);

        return btnBox;
    }

    protected abstract HBox makeEntryBox (T entry);

    protected abstract void initEntryEditBtn (T entry, Button editBtn);

    protected abstract void initEntryDelBtn (T entry, Button delBtn);

}

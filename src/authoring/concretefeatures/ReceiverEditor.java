package authoring.concretefeatures;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import authoring.abstractfeatures.PopupWindow;


public class ReceiverEditor extends PopupWindow {

    private List<String> myPosReceivers;

    private final int HEIGHT = 400;
    private final int WIDTH = 400;
    private final String NAME = "Action Receivers";
    private String myActor;
    private String myAction;

    private static final String STYLESHEET = "/resources/stylesheets/actioncreator_layout.css";

    public ReceiverEditor (List<String> receivers, String actor, String action) {

        myPosReceivers = receivers;
        myActor = actor;
        myAction = action;

        setHeight(HEIGHT);
        setWidth(WIDTH);
        setTitle(NAME);
        initialize();

    }

    @Override
    protected void initialize () {
        System.out.println(" awhatwhath");
        System.out.println(myActor);
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLESHEET);

        VBox mainVBox = new VBox();
        mainVBox.getStyleClass().add("vbox");
        mainVBox.setId("vbox-main");
        VBox actionNameVBox = new VBox();
        VBox actorVBox = new VBox();
        VBox ReceiverVBox = new VBox();

        Label actionName = new Label("Action:");
        Button action = new Button(myAction);
        actionNameVBox.getChildren().addAll(actionName, action);
        
        Label actorType = new Label("Actor:");
        Button actor = new Button(myActor);

        mainVBox.getChildren().addAll(actionNameVBox);
        root.setContent(mainVBox);
        setScene(scene);

    }

}

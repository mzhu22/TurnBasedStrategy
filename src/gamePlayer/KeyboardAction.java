package gamePlayer;

import gamedata.action.Action;
import gamedata.gamecomponents.Piece;
import gameengine.player.HumanPlayer;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Allows users to use keyboard to "move the cursor" up and down the available actions in the Controls panel.
 * KeyboardAction is strictly used for selecting actions in the Controls.
 * @author Yoonhyung
 *
 */
public class KeyboardAction {
    HumanPlayer myCurrentPlayer;
    Point2D myCurrentLocation;
    GameGridEffect myGameGridEffect;
    List<Action> myActions;
    Action myCurrentAction;
    int myActionIndex;
    Map<KeyCode, Point2D> movementKeyMap;

    public KeyboardAction () {
        myActionIndex = 0;
        myActions = new ArrayList<Action>();

        // using the movement key map to determine which keys=up&down
        // // myCurrentPlayer = do something with p.getPlayerID() to get the HumanPlayer?;
        // // movementKeyMap = myCurrentPlayer.getMovementKeyMap();

        // for testing!!!
        movementKeyMap = new HashMap<KeyCode, Point2D>();
        movementKeyMap.put(KeyCode.A, new Point2D.Double(-1.0, 0.0));
        movementKeyMap.put(KeyCode.D, new Point2D.Double(1.0, 0.0));
        movementKeyMap.put(KeyCode.W, new Point2D.Double(0.0, 1.0));
        movementKeyMap.put(KeyCode.S, new Point2D.Double(0.0, -1.0));
    }

    public void setActionKeyControl (ViewController vc, ScrollPane sp) {
        System.out.println("KeyboardAction ON");

        if (vc.getActivePiece() != null) {
            Piece activePiece = vc.getActivePiece();
            myActions = activePiece.getActions();
        }

        sp.requestFocus();
        sp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Set<KeyCode> movementKeyList = movementKeyMap.keySet();

            @Override
            public void handle (KeyEvent key) {

                // Do action here
                if (key.getCode() == KeyCode.F) {
                    vc.bindAction(myCurrentAction);
                    System.out.println("Selected action with key");
                }

                // Select action here
                for (KeyCode kc : movementKeyList) {
                    if (key.getCode() == kc) {
                        int newActionIndex = myActionIndex - (int) movementKeyMap.get(kc).getY();
                        if ( (newActionIndex>=0) & (newActionIndex<myActions.size())){
                            myActionIndex = newActionIndex;
                        }

                        myCurrentAction = myActions.get(myActionIndex);
                        System.out.println("myActionIndex: " + myActionIndex);

                        // work on highlighting selected action.
                    }
                }
            }
        });
    }
}

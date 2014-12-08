package gamePlayer;

import gamedata.gamecomponents.Level;
import gamedata.gamecomponents.Piece;
import javafx.scene.input.MouseEvent;
import authoring_environment.SuperTile;

/**
 * 
 * Class representing the state of the grid when a particular action has been
 * selected and is ready to be applied on the grid
 * 
 * @author Eric, Yiran, Yoon, Rica
 * 
 *
 */
public class ApplyState implements IGridState {
	// public static final String CURSOR_ATTACK_TEST =
	// "resources/images/Cursor_attack.png";
	// public static final String CURSOR_GLOVE_TEST =
	// "resources/images/pointer-glove.png";

	private ViewController myController;
	private GameGridEffect myGameGridEffect;
	private SuperTile activeTile;

	public ApplyState(ViewController controller) {
		myController = controller;
		myGameGridEffect = controller.getGameGridEffect();
		myController.getGridPane().setOnMouseMoved(
				event -> handleMouseMove(event));
	}

	/**
	 * When the mouse of moved, it will find a new effect range to highlight
	 * relative to the current location
	 * 
	 * @param event
	 */
	private void handleMouseMove(MouseEvent event) {
		if (myController.getGrid().findClickedTile(event.getX(), event.getY()) != null
				&& myGameGridEffect.isHoveringOverActionHighlight(event.getX(),
						event.getY())) {
			activeTile = myController.getGrid().findClickedTile(event.getX(),
					event.getY());
			myController.getGameGridEffect().highlightEffectRange(
					activeTile.getCoordinates());
		}
	}

	@Override
	public void onClick(Piece piece) {
		Piece actor = myController.getActivePiece();
		if (piece == null
				&& myController.getActiveAction().toString().equals("Movement")) {
			piece = new Piece(actor, myController.getCurrentClick());
			piece.setLoc(myController.getCurrentClick());
		}
		// System.out.println("ACTION RUNNING:" +
		// myController.getActiveAction().toString());
		myController.getActiveAction().doBehavior(actor, piece);
		myController.getGrid().repopulateGrid();
		myGameGridEffect.clearAllPieceHighlights();
		myGameGridEffect.clearAllActionHighlights();
		myController.clearActions();
		myController.setGridState(new SelectState(myController));
		myController.setActivePiece(null);
		myController.setActiveAction(null);
		this.endAction();
	}

	private void endAction() {
		Level currentLevel = myController.getGame().getCurrentLevel();
		currentLevel.runGameEvents();
		if (currentLevel.getGameWon()) {
			// GAMEWON
		}
		if (currentLevel.getGameLost()) {
			// GAMELOST
		}
		if (!currentLevel.getTurnOver()) {
			currentLevel.setTurnFalse();
			myController.getGame().nextPlayer();
			myController.setCurrentPlayer(myController.getGame().getCurrentPlayer());
			myController.setPlayerTurnDisplay();
		}
		if(currentLevel.getNextLevelID()==null){
			System.out.println("NEXT LEVEL");
			//myController.getGame().changeLevel(currentLevel.getNextLevelID());
			myController.getGame().changeLevel("Level 2");
			//myController.getGame().getCurrentLevel().getGrid().displayPane(myController.getGridPane());
			myController.initializeGrid();
		}
	}

}

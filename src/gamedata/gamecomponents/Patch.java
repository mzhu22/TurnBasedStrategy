package gamedata.gamecomponents;
 
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public abstract class Patch {

	private int myState;
	private int myID;
	private Point2D myLoc;
	private ImageView myImage;

	/**
	 * Constructor for patch
	 * @param state of patch (this is more like type of patch: ex. fire, water, etc)
	 * @param id of patch (each patch has its unique ID)
	 * @param imageLocation of patch(form like "images/myImage.jpg")
	 * @param p coordinate of patch
	 */
	public Patch(int state, int id, ImageView image, Point2D p) {
		myState = state;
		myID = id;
		myImage = image;
		myLoc = p;
	}


	/**
	 * Getter for state
	 * @return patch's state
	 */
	public int getMyState() {
		return myState;
	}

	/**
	 * Sets patch's state
	 * @param myState of patch
	 */
	public void setMyState(int myState) {
		this.myState = myState;
	}

	/**
	 * Getter for ID
	 * @return ID of patch
	 */
	public int getMyID() {
		return myID;
	}

	/**
	 * Getter for patch's coordinate location
	 * @return coord of patch
	 */
	public Point2D getLoc() {
		return myLoc;
	}

	/**
	 * Sets patch's ID
	 * @param myID of patch
	 */
	public void setMyID(int myID) {
		this.myID = myID;
	}

	/**
	 * Getter for patch's image
	 * @return image of the patch
	 */
	public ImageView getImageView() {
		return myImage;
	}

	/**
	 * Sets chosen image to patch's location
	 * @param imageLocation image file's location
	 */
	public void setMyImage(ImageView image) {
		this.myImage = image;
	}

}

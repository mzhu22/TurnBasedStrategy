package gamedata.gamecomponents;

import java.awt.geom.Point2D;

/**
 * @author Sandy Lee
 *
 */
public class Patch extends GridComponent {

	/**
	 * Constructor
	 * 
	 * @param typeID
	 *            ID for this type of patch
	 * @param imageLocation
	 *            imageLocation of patch(form like "images/myImage.jpg")
	 * @param p
	 *            coordinate of patch
	 */
	public Patch(String name, String imageLocation, Point2D p) {
		super(name, imageLocation, p);
	}
	
	/**
	 * Deep cloning constructor for a Patch
	 * @param clone - Patch instance to be cloned
	 */
	public Patch(Patch clone, Point2D placeHere) {
		super(clone, placeHere);
	}
}
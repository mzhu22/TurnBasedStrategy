package gameengine.movement;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Point2D;

/**
<<<<<<< HEAD
 * Not using
 * @author Anna, Jesse, Rica
=======
 * Not Currently Implemented.....
>>>>>>> 2e43b5636c69078155264901cb7f1d3d62bff565
 *
 */
public class Path {
	/**
	 * Contains respective positions for possible path.
	 */
	private List<Point2D> myCoords;

	public Path() {

	}

	public Path(List<Point2D> myPath) {
		myCoords = myPath;
	}

	public void addPointsToPath(Point2D... args) {
		for (Point2D p : args) {
			myCoords.add(p);
		}
	}

	public void removePointsFromPath(int index) {
		myCoords.remove(index);
	}

	public List<Point2D> getPath(int x, int y) {
		List<Point2D> p = new ArrayList<Point2D>();
		return p;
	}
}

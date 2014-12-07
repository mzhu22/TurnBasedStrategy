package authoring.data;

import gamedata.gamecomponents.Patch;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Class for storing the patches created by the user
 * Stored in the authoring environment
 * 
 * @author Sandy Lee
 */
public class PatchData implements AuthoringData<Patch> {

	private List<Patch> myPatches;

	/**
	 * Constructor for new PatchData,
	 * initializes empty list of Patch
	 */
	public PatchData () {
		myPatches = new LinkedList<Patch>();
	}

	@Override
	public void add(Patch p) {
		myPatches.add(p);
	}
	
	@Override
	public void replace(Patch origEl, Patch newEl) {
		origEl.setName(newEl.getName());
		origEl.setImageLocation(newEl.getImageLocation());
	}

	@Override
	public void remove(Patch p) {
		myPatches.remove(p);
	}

	@Override
	public List<Patch> getData() {
		return myPatches;
	}

	public void removePatchAtLoc(Point2D location){
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc())){
				myPatches.remove(patch);
				return;
			}
		}
	}

	// Can we rename this?
	public boolean terrainAtLoc(Patch terrain, int x, int y){
		Point2D location = new Point2D.Double(x, y);
		for(Patch patch : myPatches){
			if(location.equals(patch.getLoc()) && terrain.getName().equals(patch.getName())){
				myPatches.remove(patch);
				return true;
			}
		}
		return false;
	}
	
	public List<Point2D> removeUnknown(Set<String> idSet) {
		List<Patch> patchToRemove = new ArrayList<Patch>();
		List<Point2D> pointsToRemove = new ArrayList<Point2D>();
		for (Patch patch : myPatches) {
    		if (!idSet.contains(patch.getID())) {
				patchToRemove.add(patch);
				patch.getImageView().setImage(null);
    			pointsToRemove.add(patch.getLoc());
    		}
		}
		myPatches.remove(patchToRemove);
		return pointsToRemove;
	}
	
	public List<Point2D> replace(Patch patchType) {
    	List<Point2D> pointsToReplace = new ArrayList<Point2D>();
    	myPatches.forEach(patch -> {
    		if (patch.getID().equals(patchType.getID())) {
    			replace(patch, patchType);
    			pointsToReplace.add(patch.getLoc());
    		}
    	});
    	System.out.println(pointsToReplace.toString());
    	return pointsToReplace;
    }
}

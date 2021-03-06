package gamedata.events.conditions;

import java.awt.geom.Point2D;
import java.util.List;

import gamedata.gamecomponents.IHasStats;

public class LocationEquals extends Condition{

	protected String myReference;
	protected Point2D.Double myConstant;
	
	public LocationEquals(String ref1, String stat1, String constant){
		super(String.format("IF Piece is at location %s", constant));
		myReference = ref1;
		
		String[] temp;
		temp = constant.split(",");
		
		double x = Double.parseDouble(temp[0]);
		double y = Double.parseDouble(temp[1]);

		myConstant = new Point2D.Double(x,y);
	}

	@Override
	public boolean evaluate(List<IHasStats> objects) {
		boolean onGrid = false;
		for(IHasStats source: objects){
			if(myReference.equals(source.getClass().toString())){
				if(myConstant.equals(source.getLoc())){
					onGrid = true;
				}
			}	
		}
		return onGrid;
	}

}

package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	
	public MovingTowardsFixedPointBuilder() {
		super("mtfp", "Moving towars fixed point");
	}
	
	ForceLaws createTheInstance(JSONObject data) {
		double g = 9.81;
		Vector2D c;
		if(data.has("c")) {
			c = new Vector2D(data.getJSONArray("c").getDouble(0), data.getJSONArray("c").getDouble(1));
		} else {
			c = new Vector2D();
		}
		if(data.has("g")) {
			g = data.getDouble("g");
		}
		return new MovingTowardsFixedPoint(c, g);
	}
	

}


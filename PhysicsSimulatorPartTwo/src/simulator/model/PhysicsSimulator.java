package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	
	private List<Body> bs = new ArrayList<Body>();
	double time;
	ForceLaws forceLaw;
	double currentTime = 0.0;
	
	public PhysicsSimulator(double time, ForceLaws forceLaw) {
		if(time <= 0) {
			throw new IllegalArgumentException("The value of time is non-positive");
		} 
		if(forceLaw == null) {
			throw new IllegalArgumentException("The value of force law is not valid");
		}
		this.time = time;
		this.forceLaw = forceLaw;
	}
	
	public void advance() {
		for(Body b : bs) {
			b.resetForce();
		}
		
		forceLaw.apply(bs);
		
		for(Body b: bs) {
			b.move(time);
		}
		currentTime += time;
		
	}
	
	public void addBody(Body b) {
		
		boolean found = false;
				if(bs.contains(b)){
				found = true;
			}
		if(found) {
			throw new IllegalArgumentException("There is already a body with this id");
		}
		
		bs.add(b);
		
	}
	
	public JSONObject getState() {
		
		JSONObject jo1 = new JSONObject();

		jo1.put("time", currentTime);
		JSONArray a = new JSONArray();
		
		for(Body b : bs) {
			a.put(b.getState());
		}
		jo1.put("bodies", a);

		
		return jo1;
		

	}
	
	public String toString() {
		return getState().toString();
	}
	
	
	
	

}

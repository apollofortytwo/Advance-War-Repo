package layout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveObject implements Serializable {

	public static List<SaveObject> Units = new ArrayList<SaveObject>();
	public int x;
	public int y;
	public int health;
	public String team;
	public String type;

	// units
	public SaveObject(int x, int y, int health, String team, String type) {
		Units.add(this);
		this.x = x;
		this.y = y;
		this.health = health;
		this.team = team;
		this.type = type;
	}
}

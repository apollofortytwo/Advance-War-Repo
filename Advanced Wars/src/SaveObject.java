
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveObject implements Serializable {

	public static List<SaveObject> units = new ArrayList<SaveObject>();
	public static List<SaveObject> buildings = new ArrayList<SaveObject>();
	public static List<SaveObject> nBuildings = new ArrayList<SaveObject>();
	public int x;
	public int y;
	public int health;
	public String team;
	public String type;
	public int productionTime;
	public String currentlyProduction;
	public boolean captured;
	public boolean attacked;
	public boolean moved;

	/**
	 * @param Unit
	 *            (Unit that will be saved)
	 */
	public SaveObject(Unit unit) {
		units.add(this);
		this.x = unit.getxPosition();
		this.y = unit.getyPosition();
		this.health = unit.info.health;
		this.team = unit.getTeam();
		this.type = unit.getType();
		this.attacked = unit.isAttacked();
		this.moved = unit.isMoved();
	}

	public SaveObject(Building building) {
		buildings.add(this);
		this.x = building.getxPosition();
		this.y = building.getyPosition();
		this.health = building.health;
		this.team = building.getTeam();
		this.productionTime = building.getEndTimeOfProduction();
		this.currentlyProduction = building.getCurrentlyProducing();
		this.captured = building.Captured;
	}

}

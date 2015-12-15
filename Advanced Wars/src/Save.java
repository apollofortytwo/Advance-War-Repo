import java.io.Serializable;
import java.util.List;

public class Save implements Serializable {
	List<SaveObject> units;
	List<SaveObject> nBuildings;
	List<SaveObject> buildings;

	String map;
	String turnText;
	int turnsElapsed;
	double[][] size;

	public void setUnits(List<SaveObject> units) {
		this.units = units;
	}

	public List<SaveObject> getUnits() {
		return units;
	}

	public void setBuildings(List<SaveObject> Buildings) {
		this.buildings = Buildings;
	}

	public List<SaveObject> getBuildings() {
		return buildings;
	}

	public void setNBuildings(List<SaveObject> NBuildings) {
		this.nBuildings = NBuildings;
	}

	public List<SaveObject> getNBuildings() {
		return nBuildings;
	}

}

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

	public List<SaveObject> getBuildings() {
		return buildings;
	}

	public List<SaveObject> getNBuildings() {
		return nBuildings;
	}

	public List<SaveObject> getUnits() {
		return units;
	}

	public void setBuildings(List<SaveObject> Buildings) {
		this.buildings = Buildings;
	}

	public void setNBuildings(List<SaveObject> NBuildings) {
		this.nBuildings = NBuildings;
	}

	public void setUnits(List<SaveObject> units) {
		this.units = units;
	}

}

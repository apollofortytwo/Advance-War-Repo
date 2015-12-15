import java.util.ArrayList;
import java.util.List;

public class AIUnit extends Unit {

	public static List<AIUnit> AIUnits = new ArrayList<AIUnit>();
	
	public AIUnit(int xPosition, int yPosition, String unit, String team) {
		super(xPosition, yPosition, unit, team);
		AIUnits.add(this);
		
		
	}

}

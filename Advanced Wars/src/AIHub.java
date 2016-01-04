import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class AIHub {

	public static List<AIUnit> aiUnits = new ArrayList<AIUnit>();

	public static void analyzeInfluence(){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static final void FindTargets() {
		for (AIUnit x : aiUnits) {
			x.potentialEnemy();
			x.closestEnemy();
		}
		moveUnits();
	}

	private static final void moveUnits() {
		for (int i = 0; i <= aiUnits.size(); i++) {
			// Find the next Best Unit to move
			AIUnit selected = nextToMove();
			// Find Path
			List<Node> path;
			try {
				path = selected.findPath(new Point(selected.pointInRange().x, selected.pointInRange().y));
				// Apply Path
				selected.move(path);
			} catch (Exception e) {

			}

		}
	}

	private static final AIUnit nextToMove() {
		AIUnit nextToMove = null;
		for (AIUnit x : aiUnits) {
			if (!x.isMoved()) {
				if (nextToMove == null) {
					nextToMove = x;
				} else if (x.getTurnsToGetInRangeOfEnemy() < nextToMove.getTurnsToGetInRangeOfEnemy()) {
					nextToMove = x;
				} else if (x.getTurnsToGetInRangeOfEnemy() == nextToMove.getTurnsToGetInRangeOfEnemy()) {
					if (x.info.movement > nextToMove.info.movement) {
						nextToMove = x;
					}
				}
			}
		}
		return nextToMove;
	}

}
class InitateAI{
	
}

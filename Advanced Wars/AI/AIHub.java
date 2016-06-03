import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class AIHub {

	public static final void action() {
		findTargets();
		moveUnits();
		attack();
		Interface.endTurn();
	}

	private static final void attack() {
		for (AIUnit x : aiUnits) {
			x.potentialEnemy();
			x.closestEnemy();
			if (x.inRange()) {
				x.attack();
				if (WinScreen.hasWon()) {
					return;
				}
			}
		}
	}

	private static final void findTargets() {

		for (AIUnit x : aiUnits) {
			x.potentialEnemy();
			x.closestEnemy();
			x.sortEnemy();
		}

	}

	private static final void moveUnits() {

		for (int i = 0; i <= aiUnits.size(); i++) {

			// Find the next Best Unit to move
			AIUnit selected = nextToMove();
			// Find Path
			List<Node> path;
			try {
				path = selected.findPath(new Point(selected.pointInRange().x,
						selected.pointInRange().y));
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
				} else if (x.getTurnsToGetInRangeOfEnemy() < nextToMove
						.getTurnsToGetInRangeOfEnemy()) {
					nextToMove = x;
				} else if (x.getTurnsToGetInRangeOfEnemy() == nextToMove
						.getTurnsToGetInRangeOfEnemy()) {
					if (x.info.movement > nextToMove.info.movement) {
						nextToMove = x;
					}
				}
			}
		}
		return nextToMove;
	}

	public static List<AIUnit> aiUnits = new ArrayList<AIUnit>();

}

class InitateAI {

}

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import layout.TableLayout;

public class AIUnit extends Unit {

	private List<Unit> potentialEnemy = new ArrayList<Unit>();

	int movetoX;

	int movetoY;
	private Unit bestEnemy;
	private int turnsToReachEnemy;
	private int turnsToGetInRangeOfEnemy;

	public AIUnit(int xPosition, int yPosition, String unit, String team) {
		super(xPosition, yPosition, unit, team);

		AIHub.aiUnits.add(this);

	}

	List<Node> findPath(Point cord) {
		NodePath nodePath = new NodePath(this, cord);
		return nodePath.getPath();
	}

	void closestEnemy() {
		this.bestEnemy = this.getPotentialEnemy().get(0);

		this.setTurnsToReachEnemy(getTurnsToReachEnemy(this.getxPosition(), this.getyPosition(),
				bestEnemy.getxPosition(), bestEnemy.getyPosition()));

		this.setTurnsToGetInRangeOfEnemy(getTurnsToGetInRangeOfEnemy(this.getxPosition(), this.getyPosition(),
				bestEnemy.getxPosition(), bestEnemy.getyPosition()));

		for (Unit z : this.getPotentialEnemy()) {
			int testing = getTurnsToReachEnemy(this.getxPosition(), this.getyPosition(), z.getxPosition(),
					z.getyPosition());

			if (testing < this.getTurnsToReachEnemy()) {
				this.setTurnsToReachEnemy(testing);
				this.bestEnemy = z;
			}
		}
		sortEnemy();
	}

	private void sortEnemy() {
		int similarEnemy = 0;
		for (AIUnit z : AIHub.aiUnits) {
			if (z != this) {
				if (z.getBestEnemy() == this.getBestEnemy()) {
					similarEnemy++;
				}
				if (similarEnemy >= 3) {

					if (this.getPotentialEnemy().size() > 2) {
						System.out.println("FIND A NEW UNIT TO TARGET");
						this.getPotentialEnemy().remove(this.getBestEnemy());
						this.closestEnemy();
					}

				}
			}

		}
	}

	void potentialEnemy() {
		this.getPotentialEnemy().clear();

		for (Unit x : Unit.UnitsArray) {
			if (x.getEnemy().equals(this.getTeam())) {
				this.getPotentialEnemy().add(x);
			}
		}
	}

	private boolean hasMovedUnit(int x, int y) {
		for (Unit unit : Unit.UnitsArray) {
			if (unit.getxPosition() == x && unit.getyPosition() == y && unit != this) {
				if (unit.isMoved()) {
					return true;
				}
			}
		}
		return false;
	}

	private void pointsInRange(int x, int y, int move) {
		if (move > 0) {
			try {
				if (!Terrain.terrainArray[x + 1][y].isObstacle && !hasMovedUnit(x + 1, y)) {
					points.add(new Point(x + 1, y));
					pointsInRange(x + 1, y, move - 1);

				}
			} catch (Exception e3) {

			}
			try {
				if (!Terrain.terrainArray[x - 1][y].isObstacle && !hasMovedUnit(x - 1, y)) {
					points.add(new Point(x - 1, y));
					pointsInRange(x - 1, y, move - 1);

				}
			} catch (Exception e2) {

			}
			try {
				if (!Terrain.terrainArray[x][y + 1].isObstacle && !hasMovedUnit(x, y + 1)) {
					points.add(new Point(x, y + 1));
					pointsInRange(x, y + 1, move - 1);

				}
			} catch (Exception e1) {

			}
			try {
				if (!Terrain.terrainArray[x][y - 1].isObstacle && !hasMovedUnit(x, y - 1)) {
					points.add(new Point(x, y - 1));
					pointsInRange(x, y - 1, move - 1);

				}
			} catch (Exception e) {

			}

		} else {
			points.add(new Point(x, y));
		}
	}

	List<Point> points;

	public Point pointInRange() {
		points = new ArrayList<Point>();
		pointsInRange(this.bestEnemy.getxPosition(), this.bestEnemy.getyPosition(), this.info.range);
		Point best = null;

		for (Point point : points) {
			if (!this.hasMovedUnit(point.x, point.y)) {
				if (best == null) {
					best = point;
				} else if (this.getTurnsToReachEnemy(point.x, point.y, this.getBestEnemy().getxPosition(),
						this.bestEnemy.getyPosition()) >= this.getTurnsToReachEnemy(best.x, best.y,
								this.getBestEnemy().getxPosition(), this.bestEnemy.getyPosition())) {

					best = point;
				}

			}

		}

		return best;

	}

	private int getTurnsToReachEnemy(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey);
	}

	private int getTurnsToGetInRangeOfEnemy(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey) - +this.info.attack;
	}

	private void canMoveTo(List<Node> list, int movement) {

		for (Unit x : Unit.UnitsArray) {
			if (x.getxPosition() == this.movetoX && x.getyPosition() == this.movetoY && this != x) {
				if (x instanceof Unit) {
					System.out.println("Unit can't move on cord: " + x.getxPosition() + ", " + x.getyPosition());
					this.movetoX = list.get(movement - 1).getxPosition();
					this.movetoY = list.get(movement - 1).getyPosition();

					canMoveTo(list, movement - 1);
				}
			}
		}
	}

	void move(List<Node> list) {

		Interface.unitPanel.removeAll();
		Interface.unitPanel.setLayout(new TableLayout(Application.size));

		if (this.info.movement >= list.size()) {
			System.out.println("LIST IS SMALLER THAN MOVEMENT");
			this.movetoX = list.get(list.size() - 1).getxPosition();
			this.movetoY = list.get(list.size() - 1).getyPosition();
			canMoveTo(list, list.size() - 1);
		} else {
			this.movetoX = list.get(this.info.movement).getxPosition();
			this.movetoY = list.get(this.info.movement).getyPosition();
			canMoveTo(list, this.info.movement);
		}

		this.setCordinates(this.movetoX, this.movetoY);

		for (Unit x : Unit.UnitsArray) {
			Interface.unitPanel.add(x, x.getxPosition(), x.getyPosition());
		}

		this.setMoved(true);
		Interface.addUnitInfo(this);

		Interface.unitPanel.revalidate();
		Interface.unitPanel.repaint();
	}

	public Unit getBestEnemy() {
		return bestEnemy;
	}

	public void setBestEnemy(Unit bestEnemy) {
		this.bestEnemy = bestEnemy;
	}

	public int getMovetoX() {
		return movetoX;
	}

	public void setMovetoX(int movetoX) {
		this.movetoX = movetoX;
	}

	public int getMovetoY() {
		return movetoY;
	}

	public void setMovetoY(int movetoY) {
		this.movetoY = movetoY;
	}

	public List<Unit> getPotentialEnemy() {
		return potentialEnemy;
	}

	public void setPotentialEnemy(List<Unit> potentialEnemy) {
		this.potentialEnemy = potentialEnemy;
	}

	public int getTurnsToReachEnemy() {
		return turnsToReachEnemy;
	}

	public void setTurnsToReachEnemy(int turnsToReachEnemy) {
		this.turnsToReachEnemy = turnsToReachEnemy;
	}

	public int getTurnsToGetInRangeOfEnemy() {
		return turnsToGetInRangeOfEnemy;
	}

	public void setTurnsToGetInRangeOfEnemy(int turnsToGetInRangeOfEnemy) {
		this.turnsToGetInRangeOfEnemy = turnsToGetInRangeOfEnemy;
	}

}

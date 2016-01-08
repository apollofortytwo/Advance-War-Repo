import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import layout.TableLayout;

public class AIUnit extends Unit {

	private List<Unit> potentialEnemy = new ArrayList<Unit>();

	int movetoX;
	int movetoY;

	private Unit bestEnemy;
	private int turnsToReachEnemy;
	private int turnsToGetInRangeOfEnemy;

	List<Point> points;

	public AIUnit(int xPosition, int yPosition, String unit, String team) {
		super(xPosition, yPosition, unit, team);

		AIHub.aiUnits.add(this);

	}

	void attack() {
		this.bestEnemy.info.health = this.bestEnemy.info.health
				- this.info.attack;
		new Explosion(bestEnemy.getxPosition(), bestEnemy.getyPosition());

		this.bestEnemy.getHealthLabel()
				.updateHealth(this.bestEnemy.info.health);

		if (this.bestEnemy.info.health <= 0) {
			this.bestEnemy.dead();
		}
	}

	private void canMoveTo(List<Node> list, int movement) {

		for (Unit x : Unit.UnitsArray) {
			if (x.getxPosition() == this.movetoX
					&& x.getyPosition() == this.movetoY && this != x) {
				if (x instanceof Unit) {
					System.out.println("Unit can't move on cord: "
							+ x.getxPosition() + ", " + x.getyPosition());
					this.movetoX = list.get(movement - 1).getxPosition();
					this.movetoY = list.get(movement - 1).getyPosition();

					canMoveTo(list, movement - 1);
				}
			}
		}
	}

	void closestEnemy() {
		this.bestEnemy = this.getPotentialEnemy().get(0);

		this.setTurnsToReachEnemy(tilesToEnemy(this.getxPosition(),
				this.getyPosition(), bestEnemy.getxPosition(),
				bestEnemy.getyPosition()));

		this.setTurnsToGetInRangeOfEnemy(tilesToRangeOfEnemy(
				this.getxPosition(), this.getyPosition(),
				bestEnemy.getxPosition(), bestEnemy.getyPosition()));

		for (Unit z : this.getPotentialEnemy()) {
			int testing = tilesToEnemy(this.getxPosition(),
					this.getyPosition(), z.getxPosition(), z.getyPosition());

			if (testing < this.getTurnsToReachEnemy()) {
				this.setTurnsToReachEnemy(testing);
				this.bestEnemy = z;
			}
		}

	}

	List<Node> findPath(Point cord) {
		NodePath nodePath = new NodePath(this, cord);
		return nodePath.getPath();
	}

	public Unit getBestEnemy() {
		return bestEnemy;
	}

	public int getMovetoX() {
		return movetoX;
	}

	public int getMovetoY() {
		return movetoY;
	}

	public List<Unit> getPotentialEnemy() {
		return potentialEnemy;
	}

	public int getTurnsToGetInRangeOfEnemy() {
		return turnsToGetInRangeOfEnemy;
	}

	public int getTurnsToReachEnemy() {
		return turnsToReachEnemy;
	}

	private boolean hasMovedUnit(int x, int y) {
		for (Unit unit : Unit.UnitsArray) {
			if (unit.getxPosition() == x && unit.getyPosition() == y
					&& unit != this) {
				if (unit.isMoved()) {
					return true;
				}
			}
		}
		return false;
	}

	boolean inRange() {
		if (tilesToEnemy(this.getxPosition(), this.getyPosition(),
				this.bestEnemy.getxPosition(), this.bestEnemy.getyPosition()) <= this.info.range) {
			System.out.println("ATTACKING FROM: " + this.getxPosition() + ", "
					+ this.getyPosition());
			return true;

		}
		return false;
	}

	void move(List<Node> list) {

		Interface.unitPanel.removeAll();
		Interface.unitPanel.setLayout(new TableLayout(TablePanel.size));

		if (this.info.movement >= list.size()) {
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

	public Point pointInRange() {
		points = new ArrayList<Point>();
		pointsInRange(this.bestEnemy.getxPosition(),
				this.bestEnemy.getyPosition(), this.info.range);
		Point best = null;

		for (Point point : points) {
			if (!this.hasMovedUnit(point.x, point.y)) {
				if (best == null) {
					best = point;
				} else if (this.tilesToEnemy(point.x, point.y, this
						.getBestEnemy().getxPosition(), this.bestEnemy
						.getyPosition()) >= this.tilesToEnemy(best.x, best.y,
						this.getBestEnemy().getxPosition(),
						this.bestEnemy.getyPosition())) {

					best = point;
				}

			}

		}

		return best;

	}

	private void pointsInRange(int x, int y, int move) {
		if (move > 0) {
			try {
				if (!Terrain.terrainArray[x + 1][y].isObstacle
						&& !hasMovedUnit(x + 1, y)) {
					points.add(new Point(x + 1, y));
					pointsInRange(x + 1, y, move - 1);

				}
			} catch (Exception e3) {

			}
			try {
				if (!Terrain.terrainArray[x - 1][y].isObstacle
						&& !hasMovedUnit(x - 1, y)) {
					points.add(new Point(x - 1, y));
					pointsInRange(x - 1, y, move - 1);

				}
			} catch (Exception e2) {

			}
			try {
				if (!Terrain.terrainArray[x][y + 1].isObstacle
						&& !hasMovedUnit(x, y + 1)) {
					points.add(new Point(x, y + 1));
					pointsInRange(x, y + 1, move - 1);

				}
			} catch (Exception e1) {

			}
			try {
				if (!Terrain.terrainArray[x][y - 1].isObstacle
						&& !hasMovedUnit(x, y - 1)) {
					points.add(new Point(x, y - 1));
					pointsInRange(x, y - 1, move - 1);

				}
			} catch (Exception e) {

			}

		} else {
			points.add(new Point(x, y));
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

	public void setBestEnemy(Unit bestEnemy) {
		this.bestEnemy = bestEnemy;
	}

	public void setMovetoX(int movetoX) {
		this.movetoX = movetoX;
	}

	public void setMovetoY(int movetoY) {
		this.movetoY = movetoY;
	}

	public void setPotentialEnemy(List<Unit> potentialEnemy) {
		this.potentialEnemy = potentialEnemy;
	}

	public void setTurnsToGetInRangeOfEnemy(int turnsToGetInRangeOfEnemy) {
		this.turnsToGetInRangeOfEnemy = turnsToGetInRangeOfEnemy;
	}

	public void setTurnsToReachEnemy(int turnsToReachEnemy) {
		this.turnsToReachEnemy = turnsToReachEnemy;
	}

	void sortEnemy() {
		int similarEnemy = 0;
		for (AIUnit z : AIHub.aiUnits) {
			if (z != this) {
				if (z.getBestEnemy() == this.getBestEnemy()) {
					similarEnemy++;
				}
				if (similarEnemy >= 3) {

					if (this.getPotentialEnemy().size() > 2) {

						this.getPotentialEnemy().remove(this.getBestEnemy());
						this.closestEnemy();
					}

				}
			}

		}
	}

	private int tilesToEnemy(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey);
	}

	private int tilesToRangeOfEnemy(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey) - +this.info.attack;
	}

}

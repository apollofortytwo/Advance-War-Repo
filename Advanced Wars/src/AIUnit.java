import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import layout.TableLayout;

public class AIUnit extends Unit {

	public static List<AIUnit> aiUnits = new ArrayList<AIUnit>();

	private List<Unit> potentialEnemy = new ArrayList<Unit>();
	
	int movetoX;

	int movetoY;
	private Unit bestEnemy;
	private int turnsToReachEnemy;

	public AIUnit(int xPosition, int yPosition, String unit, String team) {
		super(xPosition, yPosition, unit, team);
		Unit.UnitsArray.remove(this);
		
		aiUnits.add(this);

	}

	public static void moveAIs() {
		System.out.println("MOVE AI");

		for (AIUnit x : aiUnits) {
			x.potentialEnemy();
			x.closestEnemy();
		}
		for (AIUnit x : aiUnits) {
			NodePath path = new NodePath(x.getxPosition(), x.getyPosition(), x.getBestEnemy().getxPosition(), x.getBestEnemy().getyPosition());
			x.movetoX = path.path.get(x.info.movement).getxPosition();
			x.movetoY = path.path.get(x.info.movement).getyPosition();
			x.move();
		}

	}

	private static void sortEnemy(AIUnit x) {
		int similarEnemy = 0;
		for (AIUnit z : AIUnit.aiUnits) {
			if (z != x) {
				if (z.bestEnemy == x.bestEnemy) {
					similarEnemy++;
				}
				if (similarEnemy >= 3) {
					if (x.potentialEnemy.size() > 2) {
						x.potentialEnemy.remove(x.bestEnemy);
						x.closestEnemy();
					}

				}
			}

		}
	}

	private void closestEnemy() {
		this.bestEnemy = this.potentialEnemy.get(0);

		this.turnsToReachEnemy = getTurnsToReachEnemy(this.getxPosition(),
				this.getyPosition(), bestEnemy.getxPosition(),
				bestEnemy.getyPosition());

		for (Unit z : this.potentialEnemy) {
			int testing = getTurnsToReachEnemy(this.getxPosition(),
					this.getyPosition(), z.getxPosition(), z.getyPosition());

			if (testing < this.turnsToReachEnemy) {
				this.turnsToReachEnemy = testing;
				this.bestEnemy = z;
			}
		}
		AIUnit.sortEnemy(this);
	}

	private void potentialEnemy() {
		this.potentialEnemy.clear();

		for (Unit x : Unit.UnitsArray) {
			if (x.getEnemy().equals(this.getTeam())) {
				this.potentialEnemy.add(x);
			}
		}
	}

	private int getTurnsToReachEnemy(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey);
	}

	private void move() {
		Interface.unitPanel.removeAll();
		Interface.unitPanel.setLayout(new TableLayout(Application.size));
		this.setCordinates(this.movetoX, this.movetoY);
		System.out.println("MOVED TO: " + this.movetoX +", "+ this.movetoY);

		for (Unit x : Unit.UnitsArray) {
			Interface.unitPanel.add(x, x.getxPosition(), x.getyPosition());
		}
		for (Unit x : AIUnit.aiUnits) {
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

}

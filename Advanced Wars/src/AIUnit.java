import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import layout.TableLayout;

public class AIUnit extends Unit {

	public static List<AIUnit> aiUnits = new ArrayList<AIUnit>();

	private List<Unit> potentialEnemy = new ArrayList<Unit>();
	private List<AIPath> listOfPaths = new ArrayList<AIPath>();
	
	private int movetoX, movetoY;
	private Unit bestEnemy;
	private int turnsToReachEnemy;

	public AIUnit(int xPosition, int yPosition, String unit, String team) {
		super(xPosition, yPosition, unit, team);
		aiUnits.add(this);

	}

	public static void moveAIs() {
		System.out.println("MOVE AI");

		for (AIUnit x : aiUnits) {
			x.potentialEnemy();
			x.closestEnemy();
		}
		for (AIUnit x : aiUnits) {
			PathFinderAI.move(x, x.getxPosition(), x.getyPosition(),
					((int) Application.HEIGHT * Application.WIDTH));
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

	public void potentialMove(int x, int y) {
		for (Unit z : Unit.UnitsArray) {
			if (z.getxPosition() == x && z.getyPosition() == y) {
				return;
			}
		}
		for (AIUnit z : AIUnit.aiUnits) {
			if (z.movetoX == x && z.movetoY == y) {
				return;
			}
		}
		for (Building z : Building.buildingsArray) {
			if (z.getxPosition() == x && z.getyPosition() == y) {
				return;
			}
		}
		for (NeutralBuilding z : NeutralBuilding.nbuildingsArray) {
			if (z.getXPosition() == x && z.getYPosition() == y) {
				return;
			}
			
		}
		
		
		
		
		
		
		
		/*
		int testing = getTurnsToReachEnemy(x, y, this.bestEnemy.getxPosition(),
				this.bestEnemy.getyPosition());
		if (testing <= this.turnsToReachEnemy) {
			this.turnsToReachEnemy = testing;
			this.movetoX = x;
			this.movetoY = y;
		} else if (testing == this.turnsToReachEnemy) {
			
		}
		*/
	}

	private int getTurnsToReachEnemy(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey);
	}

	private void move() {
		Interface.unitPanel.removeAll();
		Interface.unitPanel.setLayout(new TableLayout(Application.size));
		this.setCordinates(this.movetoX, this.movetoY);

		for (Unit x : Unit.UnitsArray) {
			Interface.unitPanel.add(x, x.getxPosition(), x.getyPosition());
		}

		this.setMoved(true);
		Interface.addUnitInfo(this);

		Interface.unitPanel.revalidate();
		Interface.unitPanel.repaint();
	}

	public Object getBestEnemy() {
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


public class PathFinders {

	/**
	 * Display the selected Units movement action, or attack action range
	 * Can be used to attack or move.
	 */
	public static void selectedUnit() {
		if (Unit.SelectedUnitActionPhase) {
			selectedUnitAttack();
			return;
		}
		if (!Unit.selectedUnit.isAttacked() && !Unit.selectedUnit.isMoved()) {
			if (Unit.selectedUnit.getType().equals("HELICOPTER")) {
				PathFinders.fly(Unit.selectedUnit.getxPosition(), Unit.selectedUnit.getyPosition(),
						Unit.selectedUnit.info.movement);
			} else {
				PathFinders.move(Unit.selectedUnit.getxPosition(), Unit.selectedUnit.getyPosition(),
						Unit.selectedUnit.info.movement);
			}
		} else if (!Unit.selectedUnit.isAttacked() && Unit.selectedUnit.isMoved()) {
			PathFinders.attack(Unit.selectedUnit.getxPosition(), Unit.selectedUnit.getyPosition(),
					Unit.selectedUnit.info.range);
		}
	}

	/**
	 * Display the selected Units movement action, or attack action range
	 * Can't be used to move or attack
	 */
	public static void Hover(Unit unit) {

		if (Unit.SelectedUnitActionPhase) {
			return;
		}

		if (!unit.isAttacked() && !unit.isMoved() || !unit.getTeam().equals(TurnPanel.turnText)) {
			Interface.addUnitInfo(unit);
			if (unit.getType().equals("HELICOPTER")) {
				PathFinders.flyingHover(unit.getxPosition(), unit.getyPosition(), unit.info.movement, unit.info.range);
			} else {
				PathFinders.hover(unit.getxPosition(), unit.getyPosition(), unit.info.movement, unit.info.range);
			}
		} else if (!unit.isAttacked() && unit.isMoved() || unit.getTeam() != TurnPanel.turnText) {
			PathFinders.hoverAttack(unit.getxPosition(), unit.getyPosition(), unit.info.range);
		}
	}

	/**
	 * 
	 * shows and allow the terrains the change the properties to allow for
	 * neutral buildings to become buildings based on the location of the
	 * selectedUnit
	 * 
	 * @param x
	 *            (X position of the selected Unit)
	 * @param y
	 *            (Y position of the selected Unit)
	 */
	public static void capture(int x, int y) {
		System.out.println("Capture");
		try {
			Terrain.terrainArray[x + 1][y].highlight("CAPTURE");
		} catch (Exception e) {

		}
		try {
			Terrain.terrainArray[x - 1][y].highlight("CAPTURE");
		} catch (Exception e) {

		}
		try {
			Terrain.terrainArray[x][y + 1].highlight("CAPTURE");
		} catch (Exception e) {

		}
		try {
			Terrain.terrainArray[x][y - 1].highlight("CAPTURE");
		} catch (Exception e) {

		}

		Interface.tilePanel.repaint();
		Interface.tilePanel.revalidate();
	}

	/**
	 * Gives the possible range for attack for selectedUnits
	 * 
	 */
	public static void selectedUnitAttack() {
		PathFinders.attack(Unit.selectedUnit.getxPosition(), Unit.selectedUnit.getyPosition(),
				Unit.selectedUnit.info.range);

	}

	/**
	 * -------------------------------------------------------------------------
	 * ----------------------
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	private static void attack(int x, int y, int range) {
		Unit.SelectedUnitActionPhase = true;
		if (range > 0) {
			try {
				Terrain.terrainArray[x + 1][y].highlight("ATTACK");
				attack(x + 1, y, range - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x - 1][y].highlight("ATTACK");
				attack(x - 1, y, range - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x][y + 1].highlight("ATTACK");
				attack(x, y + 1, range - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x][y - 1].highlight("ATTACK");
				attack(x, y - 1, range - 1);
			} catch (Exception e) {

			}
			Interface.tilePanel.repaint();
			Interface.tilePanel.revalidate();

		}

	}

	private static void fly(int x, int y, int movement) {
		if (movement > 0) {
			try {
				Terrain.terrainArray[x + 1][y].highlight();
				fly(x + 1, y, movement - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x - 1][y].highlight();
				fly(x - 1, y, movement - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x][y + 1].highlight();
				fly(x, y + 1, movement - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x][y - 1].highlight();
				fly(x, y - 1, movement - 1);
			} catch (Exception e) {

			}
			Interface.tilePanel.repaint();
			Interface.tilePanel.revalidate();

		}
	}

	private static void flyingHover(int x, int y, int movement, int range) {
		if (movement > 0) {
			try {
				Terrain.terrainArray[x + 1][y].highlight();
				flyingHover(x + 1, y, movement - 1, range);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x - 1][y].highlight();
				flyingHover(x - 1, y, movement - 1, range);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x][y + 1].highlight();
				flyingHover(x, y + 1, movement - 1, range);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x][y - 1].highlight();
				flyingHover(x, y - 1, movement - 1, range);
			} catch (Exception e) {

			}
			Interface.tilePanel.repaint();
			Interface.tilePanel.revalidate();

		} else if (range > 0) {
			hoverAttack(x, y, range);
		}
	}

	private static void hover(int x, int y, int distance, int range) {
		if (distance > 0) {
			try {
				if (!Terrain.terrainArray[x + 1][y].isObstacle) {
					Terrain.terrainArray[x + 1][y].highlight();
					if (Terrain.terrainArray[x + 1][y].isWater()) {
						hover(x + 1, y, distance - 2, range);
					} else {
						hover(x + 1, y, distance - 1, range);
					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x - 1][y].isObstacle) {
					Terrain.terrainArray[x - 1][y].highlight();
					if (Terrain.terrainArray[x - 1][y].isWater()) {
						hover(x - 1, y, distance - 2, range);
					} else {
						hover(x - 1, y, distance - 1, range);
					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x][y - 1].isObstacle) {
					Terrain.terrainArray[x][y - 1].highlight();
					if (Terrain.terrainArray[x][y - 1].isWater()) {
						hover(x, y - 1, distance - 2, range);
					} else {
						hover(x, y - 1, distance - 1, range);
					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x][y + 1].isObstacle) {
					Terrain.terrainArray[x][y + 1].highlight();
					if (Terrain.terrainArray[x][y + 1].isWater()) {
						hover(x, y + 1, distance - 2, range);
					} else {
						hover(x, y + 1, distance - 1, range);
					}
				}
			} catch (Exception e) {

			}
			Interface.tilePanel.repaint();
			Interface.tilePanel.revalidate();

		} else if (range > 0) {
			hoverAttack(x, y, range);
		}
	}

	private static void hoverAttack(int x, int y, int range) {
		if (range > 0) {
			try {
				Terrain.terrainArray[x + 1][y].highlight("hoverAttack");
				hoverAttack(x + 1, y, range - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x - 1][y].highlight("hoverAttack");
				hoverAttack(x - 1, y, range - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x][y + 1].highlight("hoverAttack");
				hoverAttack(x, y + 1, range - 1);
			} catch (Exception e) {

			}
			try {
				Terrain.terrainArray[x][y - 1].highlight("hoverAttack");
				hoverAttack(x, y - 1, range - 1);
			} catch (Exception e) {

			}
			Interface.tilePanel.repaint();
			Interface.tilePanel.revalidate();

		}
	}

	private static void move(int x, int y, int distance) {
		if (distance > 0) {
			try {
				if (!Terrain.terrainArray[x + 1][y].isObstacle) {
					Terrain.terrainArray[x + 1][y].highlight();
					if (Terrain.terrainArray[x + 1][y].isWater()) {
						move(x + 1, y, distance - 2);
					} else {
						move(x + 1, y, distance - 1);
					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x - 1][y].isObstacle) {
					Terrain.terrainArray[x - 1][y].highlight();
					if (Terrain.terrainArray[x - 1][y].isWater()) {
						move(x - 1, y, distance - 2);
					} else {
						move(x - 1, y, distance - 1);
					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x][y - 1].isObstacle) {
					Terrain.terrainArray[x][y - 1].highlight();
					if (Terrain.terrainArray[x][y - 1].isWater()) {
						move(x, y - 1, distance - 2);
					} else {
						move(x, y - 1, distance - 1);
					}
				}
			} catch (Exception e) {

			}
			try {
				if (!Terrain.terrainArray[x][y + 1].isObstacle) {
					Terrain.terrainArray[x][y + 1].highlight();
					if (Terrain.terrainArray[x][y + 1].isWater()) {
						move(x, y + 1, distance - 2);
					} else {
						move(x, y + 1, distance - 1);
					}
				}
			} catch (Exception e) {

			}
			Interface.tilePanel.repaint();
			Interface.tilePanel.revalidate();

		}
	}

}

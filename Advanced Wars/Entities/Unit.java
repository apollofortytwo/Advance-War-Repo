import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import layout.TableLayout;

class Unit extends JLabel implements MouseListener {

	public static void resetUnits() {
		for (Unit x : Unit.UnitsArray) {
			x.setIcon(new ImageIcon(x.unitSprite));
			if (!x.getTeam().equals(TurnPanel.turnText)) {
				x.setMoved(true);
				x.setAttacked(true);
			} else {
				x.setMoved(false);
				x.setAttacked(false);
			}
		}
	}

	public static void setSelectedUnit(Unit unit) {
		Unit.selectedUnit = unit;
		Building.selectedBuilding = null;
	}

	public static void UnSelectedUnit() {
		Unit.SelectedUnitActionPhase = false;
		Unit.selectedUnit = null;

	}

	public static Unit selectedUnit = null;

	public static boolean SelectedUnitActionPhase = false;

	public static List<Unit> UnitsArray = new ArrayList<Unit>();

	public UnitInfoPanel infoPanel;
	private int xPosition, yPosition;

	private boolean moved = false, attacked = false;
	private String type, enemy, team;

	Image unitSprite, unitGrayyed;

	public UnitInfo info;

	private float unitInfluence;

	private HealthLabel healthLabel;

	/**
	 * JPanel that can be added to a Unit Panel cell a Unit can be provided to
	 * both players a Unit can attack the other players Units a Unit can change
	 * it's location on the UnitPanel(Move) through it's move function
	 * 
	 * Types of Units - Infantry quick to produce through buildings expendable -
	 * Artillery slow high damage high range slow to produce - Tank lots of
	 * health high damage - Helicopter High movement Can move over trees Doesn't
	 * get affected by mud/water
	 *
	 * @param xPosition
	 *            (X Position on the Unit Panel )
	 * @param xPosition
	 *            (Y Position on the Unit Panel )
	 * @param unit
	 *            (Type of Unit )
	 * @team team (Can be controlled by this player )
	 */
	public Unit(int xPosition, int yPosition, String unit, String team) {
		UnitsArray.add(this);
		this.setCordinates(xPosition, yPosition);
		this.type = unit;
		this.team = team;
		this.setEnemy();

		this.unitSprite = SpriteLoader.getSprite(team + unit);
		this.unitGrayyed = SpriteLoader.getSprite(team + unit + "_Sleep");

		info = new UnitInfo(unit);

		infoPanel = new UnitInfoPanel(this);
		infoPanel.updateToPanel();

		this.setIcon(new ImageIcon(this.unitSprite));

		setHealthLabel(new HealthLabel(this.info.health));

		this.add(getHealthLabel());

		this.addMouseListener(this);
		Interface.unitPanel.add(this, Integer.toString(xPosition) + ","
				+ Integer.toString(yPosition));
	}

	/**
	 * Ones an Enemy Unit is found on a tile that has the ability to attack The
	 * selected Unit will damage the enemy Unit If the enemy Unit's health
	 * depletes they are removed from the game
	 */
	private void attackUnit() {
		for (int x = 0; x < Terrain.terrainArray.length; x++) {
			for (int y = 0; y < Terrain.terrainArray.length; y++) {

				if (Terrain.terrainArray[x][y].getIsAttackable()
						&& Terrain.terrainArray[x][y].getYPosition() == this
								.getyPosition()
						&& Terrain.terrainArray[x][y].getXPosition() == this
								.getxPosition()) {

					this.info.health = this.info.health
							- Unit.selectedUnit.info.attack;
					getHealthLabel().updateHealth(this.info.health);

					Unit.selectedUnit.gray();
					new Explosion(this.xPosition, this.yPosition);

					Unit.selectedUnit.moved = true;
					Unit.selectedUnit.attacked = true;

					Unit.UnSelectedUnit();
					Terrain.restoreAllTileStatus();

					if (this.info.health <= 0) {
						dead();
					}
				}
			}
		}

	}

	/**
	 * how much threat does this unit and it's stats pose???
	 * 
	 * Value of Influence is based on: health, attack damage, movement, and
	 * range
	 */

	/**
	 * Removes the Unit from the game
	 */
	protected void dead() {
		Interface.unitPanel.remove(this);
		Interface.unitPanel.revalidate();
		Interface.unitPanel.repaint();
		if (this.team.equals("Blue")) {
			Interface.redTeamInfo.increaseNumberOfKills();
		} else {
			Interface.blueTeamInfo.increaseNumberOfKills();
		}
		Unit.UnitsArray.remove(this);

	}

	public String getEnemy() {
		return enemy;
	}

	public HealthLabel getHealthLabel() {
		return healthLabel;
	}

	public UnitInfo getInfo() {
		return info;
	}

	public String getTeam() {
		return team;
	}

	public String getType() {
		return type;
	}

	public float getUnitInfluence() {
		return unitInfluence;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void gray() {
		this.setIcon(new ImageIcon(this.unitGrayyed));
	}

	public boolean isAttacked() {
		return attacked;
	}

	public boolean isMoved() {
		return moved;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (Unit.selectedUnit != null
				&& !Unit.selectedUnit.team.equals(this.team)
				&& !Unit.selectedUnit.attacked) {
			this.attackUnit();
		}

		if (this.team.equals(TurnPanel.turnText)) {
			Interface.restore();
			System.out.println("New Unit selected");
			Interface.addUnitInfo(this);
			Unit.setSelectedUnit(this);
			Terrain.restoreAllTileStatus();
		} else {
			return;
		}

		PathFinders.selectedUnit();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (Unit.selectedUnit != null) {
			if (!Unit.selectedUnit.getTeam().equals(this.team)) {
				Interface.addUnitInfo(this);
				PathFinders.Hover(this);
			}
		} else if (Unit.selectedUnit == null) {
			Interface.addUnitInfo(this);
			PathFinders.Hover(this);

		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (Unit.selectedUnit != null) {
			if (!Unit.selectedUnit.getTeam().equals(this.team)) {
				Terrain.restoreAllTileStatus();
				PathFinders.selectedUnit();
				Interface.remove(team);
			}
		} else if (Unit.selectedUnit == null) {
			Interface.remove(team);
			Terrain.restoreAllTileStatus();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * If a certain Terrain is selected and has the ability to allow a Unit to
	 * move on it's location the Unit is transfered to that location.
	 * 
	 * @param x
	 *            (x Position of the move-able Terrain)
	 * @param y
	 *            (y Position of the move-able Terrain)
	 */
	public void moveUnit(int x, int y) {
		Terrain.restoreAllTileStatus();
		Interface.unitPanel.removeAll();
		Interface.unitPanel.setLayout(new TableLayout(TablePanel.size));
		Unit.selectedUnit.setCordinates(x, y);
		for (Unit z : Unit.UnitsArray) {
			Interface.unitPanel.add(z, z.getxPosition(), z.getyPosition());
		}
		Unit.selectedUnit.setMoved(true);
		Interface.addUnitInfo(selectedUnit);
		PathFinders.selectedUnit();
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public void setCordinates(int x, int y) {
		setxPosition(x);
		setyPosition(y);
	}

	private void setEnemy() {
		if (team.equals("Blue"))
			this.enemy = "Red";
		if (team.equals("Red"))
			this.enemy = "Blue";
	}

	public void setHealthLabel(HealthLabel healthLabel) {
		this.healthLabel = healthLabel;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public void setUnitInfluence(float unitInfluence) {
		this.unitInfluence = unitInfluence;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public void updateHealthLabel() {
		getHealthLabel().setText(Integer.toString(this.info.health));
	}

}

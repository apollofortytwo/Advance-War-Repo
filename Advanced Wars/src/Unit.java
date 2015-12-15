
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import layout.TableLayout;

class Unit extends JLabel implements MouseListener, Serializable {

	public static Unit selectedUnit = null;

	public static boolean SelectedUnitActionPhase = false;

	public static List<Unit> UnitsArray = new ArrayList<Unit>();

	public static void resetUnits() {
		for (Unit x : Unit.UnitsArray) {
			x.setIcon(new ImageIcon(x.getSprites().idle));
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

	public UnitInfoPanel infoPanel;
	private int xPosition, yPosition;

	private boolean moved = false, attacked = false;
	private String type, enemy, team;

	private Sprite sprites;

	public UnitInfo info;

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

		this.setSprites(new Sprite(team, type));

		info = new UnitInfo(unit);

		infoPanel = new UnitInfoPanel(this);
		infoPanel.updateToPanel();

		this.setIcon(new ImageIcon(sprites.idle));

		healthLabel = new HealthLabel(this.info.health);

		this.add(healthLabel);

		this.addMouseListener(this);
		Interface.unitPanel.add(this, Integer.toString(xPosition) + "," + Integer.toString(yPosition));
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
						&& Terrain.terrainArray[x][y].getYPosition() == this.getyPosition()
						&& Terrain.terrainArray[x][y].getXPosition() == this.getxPosition()) {

					this.info.health = this.info.health - Unit.selectedUnit.info.attack;
					healthLabel.updateHealth(this.info.health);

					Unit.selectedUnit.gray();
					Explosion.explode(this);

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
	 * Removes the Unit from the game
	 */
	private void dead() {
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

	public UnitInfo getInfo() {
		return info;
	}

	public Sprite getSprites() {
		return sprites;
	}

	public String getTeam() {
		return team;
	}

	public String getType() {
		return type;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void gray() {
		this.setIcon(new ImageIcon(this.getSprites().grayyed));
	}

	public boolean isAttacked() {
		return attacked;
	}

	public boolean isMoved() {
		return moved;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (Unit.selectedUnit != null && !Unit.selectedUnit.team.equals(this.team) && !Unit.selectedUnit.attacked) {
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
		Interface.unitPanel.setLayout(new TableLayout(Application.size));
		Unit.selectedUnit.setCordinates(x, y);
		for (int i = 0; i < Unit.UnitsArray.size(); i++) {
			Interface.unitPanel.add(Unit.UnitsArray.get(i), Unit.UnitsArray.get(i).getxPosition(),
					Unit.UnitsArray.get(i).getyPosition());
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

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	private void setSprites(Sprite sprites) {
		this.sprites = sprites;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public void updateHealthLabel() {
		healthLabel.setText(Integer.toString(this.info.health));
	}

}


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A stationary entity that is controlled by the Player has an assigned team that can
 * control it Added on to the Tile Panel Can produce new Units over a period of
 * time Can be destroyed by enemy Units
 * 
 * @author ApolloFortyTwo
 *
 */
public class Building extends JLabel implements MouseListener {
	public static Building selectedBuilding = null;
	public static List<Building> buildingsArray = new ArrayList<Building>();
	public static boolean hoverCheck = true;

	public static void loopThroughProduction() {
		for (Building x : Building.buildingsArray) {
			x.isProductionFinished();
		}
	}

	/**
	 * Increase the health of all created buildings by 1 Captured Building max
	 * out at 30 Normal Building max out at 50
	 */
	public static void regeneration() {
		for (Building x : Building.buildingsArray) {
			if (x.Captured && x.health < 30) {
				System.out.println("Caped");
				x.health++;
			} else if (!x.Captured && x.health < 50) {
				x.health++;
			}
			x.healthLabel.updateHealth(x.health);
			x.buildingPanel.updateInfo();
		}
	}

	public static void setSelectedBuilding(Building building) {
		Building.selectedBuilding = building;
		Unit.selectedUnit = null;
	}

	public boolean Captured = false;
	BuildingInfoPanel buildingPanel;
	private int xPosition;
	private int yPosition;
	public int health;
	public Sprite sprites;
	private boolean isDead = false;
	private int timeOfProduction, endTimeOfProduction;

	private String currentlyProducing;

	public String team;

	protected HealthLabel healthLabel;

	/**
	 * 
	 * @param x			(Location on the x axis of the TilePanel)
	 * @param y			(Location on the y axis of the TilePanel)
	 * @param team		(Which team is able to use this Building)
	 */
	public Building(int x, int y, String team) {
		this.team = team;
		this.health = 50;

		buildingPanel = new BuildingInfoPanel(this);
		setxPosition(x);
		setyPosition(y);
		this.setLayout(null);

		System.out.println(team);
		sprites = new Sprite(team);

		this.setIcon(new ImageIcon(sprites.idle));

		healthLabel = new HealthLabel(this.health);

		this.add(healthLabel);

		this.addMouseListener(this);

		Interface.tilePanel.add(this, Integer.toString(x) + "," + Integer.toString(y));
		Interface.tilePanel.repaint();
		Interface.tilePanel.revalidate();
		buildingPanel.updateInfo();
		return;
	}

	private void dead() {
		this.setIcon(new ImageIcon(sprites.destroyed));
		this.setDead(true);
		Interface.tilePanel.repaint();
		Interface.tilePanel.revalidate();
	}

	public String getCurrentlyProducing() {
		return currentlyProducing;
	}

	public int getEndTimeOfProduction() {
		return endTimeOfProduction;
	}

	public int getHealth() {
		return health;
	}

	public String getTeam() {
		return team;
	}

	public int getTimeOfProduction() {
		return timeOfProduction;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public boolean isDead() {
		return this.isDead;
	}

	
	private void isProductionFinished() {
		if (isDead) {
			endTimeOfProduction = 100000;
		}
		if (this.endTimeOfProduction == TurnPanelLabel.turnsElapsed && this.currentlyProducing != null) {
			Unit.UnitsArray.add(new Unit(this.getxPosition(), this.getyPosition(), this.currentlyProducing, this.team));
			System.out.println("Exported");
			this.currentlyProducing = null;
		} else if (this.currentlyProducing != null) {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		// Determine if the location that it is standing on has been marked as attackable
		if (Terrain.terrainArray[getxPosition()][getyPosition()].getIsAttackable()) {

			//Make sure that the attacker isn't on the same team as the building
			if (Unit.selectedUnit != null && Unit.selectedUnit.getTeam() != this.team) {
				System.out.println("Building at: " + getxPosition() + ", " + getyPosition() + " is being attacked");
				
				this.health = this.health - Unit.selectedUnit.info.attack;
				this.buildingPanel.updateInfo();
				healthLabel.updateHealth(this.health);
				Explosion.explode(this);

				if (this.health <= 0) {
					this.dead();
				}

				System.out.println("health: " + health);
				Unit.selectedUnit.setMoved(true);
				Unit.selectedUnit.setAttacked(true);
				Unit.selectedUnit.setIcon(new ImageIcon(Unit.selectedUnit.getSprites().grayyed));

				Terrain.restoreAllTileStatus();
			}
		}

		if (this.team == TurnPanelLabel.turnText) {
			Terrain.restoreAllTileStatus();
			Building.setSelectedBuilding(this);
			Interface.addBuildingInfo(this);

		} else {
			return;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (Building.selectedBuilding != null) {
			if (Building.selectedBuilding.getTeam() != this.team) {
				Interface.addBuildingInfo(this);

			}
		} else if (Building.selectedBuilding == null) {
			Interface.addBuildingInfo(this);
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (Building.selectedBuilding != null) {
			if (Building.selectedBuilding.getTeam() != this.team) {
				Interface.remove(team);
			}
		} else if (Building.selectedBuilding == null) {
			Interface.remove(team);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setCurrentlyProducing(String currentlyProducing) {
		this.currentlyProducing = currentlyProducing;
	}

	private void setDead(boolean b) {
		isDead = b;
	}

	public void setEndTimeOfProduction(int timeOfProduction) {
		this.endTimeOfProduction = TurnPanelLabel.turnsElapsed + timeOfProduction + 1;
		System.out.println("Setting endTimeOfProduction to: " + this.endTimeOfProduction);
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setTimeOfProduction() {
		this.timeOfProduction = TurnPanelLabel.turnsElapsed;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

}

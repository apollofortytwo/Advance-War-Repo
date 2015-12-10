/**
 * Acts like a Building 
 * Starts off with 1 hp
 * @author ApolloFortyTwo
 *
 */
public class CapturedBuilding extends Building {

	public CapturedBuilding(int x, int y, String team) {
		super(x, y, team);
		this.health = 1;
		this.Captured = true;
		this.healthLabel.updateHealth(this.health);
		this.buildingPanel.updateInfo();
	}

	//once dead the Building changes back into a NeutralBuilding
	public void dead() {
		NeutralBuilding.buildingsArray.add(new NeutralBuilding(this.getxPosition(), this.getyPosition()));
		Building.buildingsArray.remove(this);
		Interface.tilePanel.remove(this);
	}

}

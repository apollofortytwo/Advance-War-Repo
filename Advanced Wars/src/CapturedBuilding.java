/**
 * Acts like a Building Starts off with 1 hp
 * created by Neutral Buildings
 * 
 * 
 * @author ApolloFortyTwo
 *
 */
public class CapturedBuilding extends Building {

	/**
	 * 
	 * @param x		(x Position)
	 * @param y		(y Position )
	 * @param team	(used by which player)
	 */
	public CapturedBuilding(int x, int y, String team) {
		super(x, y, team);
		this.healthLabel.reset(30);
		this.health = 1;
		this.Captured = true;
		this.healthLabel.updateHealth(1);
		this.buildingPanel.updateInfo();
	}

	// once dead the Building changes back into a NeutralBuilding
	public void dead() {
		new NeutralBuilding(this.getxPosition(), this.getyPosition());
		Building.buildingsArray.remove(this);
		Interface.tilePanel.remove(this);
	}

}

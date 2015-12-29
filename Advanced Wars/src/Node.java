
public class Node {

	int xPosition;
	int yPosition;

	int distanceToEnemy;
	int moveCost;

	Node parent;
	Node enemy;

	boolean hasMovedUnit = false;
	boolean hasTerrain = false;

	Node(int xPosition, int yPosition, Node enemy) {
		this.yPosition = yPosition;
		this.xPosition = xPosition;
		this.enemy = enemy;

		this.distanceToEnemy = distanceToNode(this.xPosition, this.yPosition, this.enemy.xPosition,
				this.enemy.yPosition);
		
		for (int x = 0; x < Application.WIDTH; x++) {
			for (int y = 0; y < Application.HEIGHT; y++) {
				if(Terrain.terrainArray[x][y].isObstacle){
					if(this.xPosition == x && this.yPosition == y){
						this.hasTerrain = true;
					}
				}
			}
		}
		
		for(Unit unit: Unit.UnitsArray){
			if(unit.isMoved()){
				if(unit.getxPosition() == this.xPosition && unit.getyPosition() == this.yPosition){
					this.hasMovedUnit = true;
				}		
			}
		}
	}
	
	public void setParent(Node parent){
		if(this.parent  == null){
			this.parent = parent;
			this.moveCost = parent.moveCost + this.distanceToEnemy;
		}
	}
	
	

	private int distanceToNode(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey);
	}
}

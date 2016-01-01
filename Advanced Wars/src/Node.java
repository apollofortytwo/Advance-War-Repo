
public class Node {

	private int xPosition;
	private int yPosition;

	private int distanceToEnemy;

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public int getDistanceToEnemy() {
		return distanceToEnemy;
	}

	public void setDistanceToEnemy(int distanceToEnemy) {
		this.distanceToEnemy = distanceToEnemy;
	}

	public int getMoveCost() {
		return moveCost;
	}

	public void setMoveCost(int moveCost) {
		this.moveCost = moveCost;
	}

	public Node getEnemy() {
		return enemy;
	}

	public void setEnemy(Node enemy) {
		this.enemy = enemy;
	}

	public Node getParent() {
		return parent;
	}

	private int moveCost;

	private Node parent;
	private Node enemy;

	private boolean hasMovedUnit = false;
	private boolean hasTerrain = false;

	private NodeStatus status;

	Node(int xPosition, int yPosition, Node enemy) {
		this.status = NodeStatus.UNVISITED;

		this.yPosition = yPosition;
		this.xPosition = xPosition;
		this.enemy = enemy;

		if (this.enemy == null) {
			this.distanceToEnemy = 0;
		} else {
			this.distanceToEnemy = distanceToNode(this.xPosition, this.yPosition, this.enemy.xPosition,
					this.enemy.yPosition);
		}

		for (int x = 0; x < Application.WIDTH; x++) {
			for (int y = 0; y < Application.HEIGHT; y++) {
				if (Terrain.terrainArray[x][y].isObstacle) {
					if (this.xPosition == x && this.yPosition == y) {
						this.setStatus(NodeStatus.CLOSED);
					}
				}
			}
		}
		/*
		 * for (Unit unit : Unit.UnitsArray) { if (unit.isMoved()) { if
		 * (unit.getxPosition() == this.xPosition && unit.getyPosition() ==
		 * this.yPosition) { this.setHasMovedUnit(true); this.setStatus(2); } }
		 * }
		 */
	}

	public void setParent(Node parent) {

		if (this.status == NodeStatus.UNVISITED) {
			this.parent = parent;
			this.moveCost = parent.moveCost + this.distanceToEnemy;
			this.setStatus(NodeStatus.OPEN);
		}
	}

	private int distanceToNode(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey);
	}

	public NodeStatus getStatus() {
		return status;
	}

	public void setStatus(NodeStatus status) {
		this.status = status;
	}

	public boolean isHasMovedUnit() {
		return hasMovedUnit;
	}

	public void setHasMovedUnit(boolean hasMovedUnit) {
		this.hasMovedUnit = hasMovedUnit;
	}

	public boolean isHasTerrain() {
		return hasTerrain;
	}

	public void setHasTerrain(boolean hasTerrain) {
		this.hasTerrain = hasTerrain;
	}
}

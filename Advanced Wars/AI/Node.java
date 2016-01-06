public class Node {

	private int xPosition;
	private int yPosition;

	private int distanceToEnemy;
	private int moveCost;

	private Node parent;
	private Node enemy;

	private boolean hasMovedUnit = false;
	private boolean hasTerrain = false;

	private NodeStatus status;

	/**
	 * Acts like Terrain tiles. Used by AI's to find path to location
	 * 
	 * @param xPosition
	 *            Location of Node on X Axis
	 * @param yPosition
	 *            Location of Node on Y Axis
	 * @param enemy
	 *            Location that the AI want's to head towards
	 */
	Node(int xPosition, int yPosition, Node enemy) {
		this.status = NodeStatus.UNVISITED;

		this.yPosition = yPosition;
		this.xPosition = xPosition;
		this.enemy = enemy;

		if (this.enemy == null) {
			this.distanceToEnemy = 0;
		} else {
			this.distanceToEnemy = distanceToNode(this.xPosition,
					this.yPosition, this.enemy.xPosition, this.enemy.yPosition);
		}
		checkObstacle();
		checkMovedUnit();
		checkNeutralBuilding();
		checkBuilding();

	}

	private final void checkBuilding() {
		for (Building building : Building.buildingsArray) {
			if (building.getxPosition() == this.xPosition
					&& building.getyPosition() == this.yPosition) {
				this.setStatus(NodeStatus.CLOSED);
			}
		}
	}

	private final void checkMovedUnit() {
		for (Unit unit : Unit.UnitsArray) {
			if (unit.isMoved()) {
				if (unit.getxPosition() == this.xPosition
						&& unit.getyPosition() == this.yPosition) {
					this.setStatus(NodeStatus.CLOSED);
				}
			}
		}
	}

	private final void checkNeutralBuilding() {
		for (NeutralBuilding building : NeutralBuilding.nbuildingsArray) {
			if (building.getXPosition() == this.xPosition
					&& building.getXPosition() == this.yPosition) {
				this.setStatus(NodeStatus.CLOSED);
			}
		}
	}

	private final void checkObstacle() {
		for (int x = 0; x < TablePanel.WIDTH; x++) {
			for (int y = 0; y < TablePanel.HEIGHT; y++) {
				if (Terrain.terrainArray[x][y].isObstacle) {
					if (this.xPosition == x && this.yPosition == y) {
						this.setStatus(NodeStatus.CLOSED);
					}
				}
			}
		}
	}

	private int distanceToNode(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey);
	}

	public int getDistanceToEnemy() {
		return distanceToEnemy;
	}

	public Node getEnemy() {
		return enemy;
	}

	public int getMoveCost() {
		return moveCost;
	}

	public Node getParent() {
		return parent;
	}

	public NodeStatus getStatus() {
		return status;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public boolean isHasMovedUnit() {
		return hasMovedUnit;
	}

	public boolean isHasTerrain() {
		return hasTerrain;
	}

	public void setDistanceToEnemy(int distanceToEnemy) {
		this.distanceToEnemy = distanceToEnemy;
	}

	public void setEnemy(Node enemy) {
		this.enemy = enemy;
	}

	public void setHasMovedUnit(boolean hasMovedUnit) {
		this.hasMovedUnit = hasMovedUnit;
	}

	public void setHasTerrain(boolean hasTerrain) {
		this.hasTerrain = hasTerrain;
	}

	public void setMoveCost(int moveCost) {
		this.moveCost = moveCost;
	}

	public void setParent(Node parent) {

		if (this.status == NodeStatus.UNVISITED) { // The method will only
													// change if Node is
													// unvisited
			this.parent = parent;
			// Changes the Node to be used as a parent
			this.moveCost = parent.moveCost + this.distanceToEnemy;
			this.setStatus(NodeStatus.OPEN);
		}
	}

	public void setStatus(NodeStatus status) {
		this.status = status;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
}

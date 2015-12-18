
/*
 * Individual nodes that can be fitted together 
 * to find the quickest path on a grid.
 * 
 * 
 * 
 */

public class Node {

	private Node parent;
	private Node targetNode;
	private int distance;
	private int moveCost;

	private int xPosition;
	private int yPosition;
	private int state = 0;


	public Node(int x, int y, Node targetNode) {

		this.xPosition = x;

		this.yPosition = y;

		this.targetNode = targetNode;

		if (targetNode == null) {
			this.distance = 0;
		} else {
			this.distance = this.calculateDistance(x, y, targetNode.getxPosition(), targetNode.getyPosition());
		}
	}

	private int calculateDistance(int mx, int my, int ex, int ey) {
		return Math.abs(mx - ex) + Math.abs(my - ey);
	}

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;

	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;

	}

	public Node getTargetNode() {
		return targetNode;
	}

	public void setTargetNode(Node targetNode) {
		this.targetNode = targetNode;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getMoveCost() {
		return moveCost;
	}

	public void setMoveCost() {
		if (this.parent == null) {
			this.moveCost = this.distance;
		} else {
			this.moveCost = this.parent.moveCost + this.distance;
		}

	}

}

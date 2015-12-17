public class Node {

	Node parent = null;
	int state = 0;
	private int distance;
	private int moveCost;
	public int x, y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
		
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(Node endNode) {
		distance = Math.abs(x - endNode.x) + Math.abs(y - endNode.y);
	}

	public int getMoveCost() {
		return moveCost;
	}

	public void setMoveCost(int moveCost) {
		this.moveCost = moveCost;
	}

}


import java.util.ArrayList;
import java.util.Collections;

public class NodePath {

	private Node[][] nodeMap = new Node[Application.HEIGHT][Application.WIDTH];
	Node endNode;

	ArrayList<Node> path;

	NodePath(int startX, int startY, int endX, int endY) {
		endNode = new Node(endX, endY, endNode);
		nodeMap[endX][endY] = endNode;
		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {
				if (x == endX && y == endY) {

				} else {
					nodeMap[x][y] = new Node(x, y, endNode);
				}

			}
		}

		nodeMap[startX][startY].setState(1);
		nodeMap[startX][startY].setParent(null);
		nodeMap[startX][startY].setMoveCost();
		expand(findLowestOpen());

	}

	private void done() {
		path = new ArrayList<Node>();
		Node parent = endNode;
		path.add(parent);
		while (parent.getParent() != null) {
			parent = parent.getParent();
			path.add(parent);
		}
		System.out.println(path.size());
		Collections.reverse(path);
		

	}

	private boolean isblocked(int x, int y, Node parent) {

		if (Terrain.terrainArray[x][y].isObstacle) {
			return false;
		}
		for (Unit z : Unit.UnitsArray) {
			if (z.getxPosition() == x && z.getyPosition() == y) {
				return false;
			}
		}
		for (Building z : Building.buildingsArray) {
			if (z.getxPosition() == x && z.getyPosition() == y) {
				return false;
			}
		}
		for (NeutralBuilding z : NeutralBuilding.nbuildingsArray) {
			if (z.getXPosition() == x && z.getYPosition() == y) {
				return false;
			}
			
		}
		return true;

	}

	private void nextToEndNode(int x,int y, Node parent){
		if(x == endNode.getxPosition() + 1 && y == endNode.getyPosition()){
			endNode.setParent(parent);
			endNode.setxPosition(x);
			done();
			
		}else if(x == endNode.getxPosition() - 1 && y == endNode.getyPosition()){
			endNode.setParent(parent);
			endNode.setxPosition(x);
			done();
			
		}else if(x == endNode.getxPosition() && y == endNode.getyPosition() +1 ){
			endNode.setParent(parent);
			endNode.setyPosition(y);
			done();
			
		}else if(x == endNode.getxPosition() && y == endNode.getyPosition() -1 ){
			endNode.setParent(parent);
			endNode.setyPosition(y);
			done();
			
		}
	}
	private void expand(Node node) {
		this.nextToEndNode(node.getxPosition(), node.getyPosition(), node);
		try {
			if (isblocked(node.getxPosition() + 1, node.getyPosition(), node)) {
				if (nodeMap[node.getxPosition() + 1][node.getyPosition()].getState() == 0) {
					nodeMap[node.getxPosition() + 1][node.getyPosition()].setParent(node);
					nodeMap[node.getxPosition() + 1][node.getyPosition()].setMoveCost();
					nodeMap[node.getxPosition() + 1][node.getyPosition()].setState(1);
				}
			}

		} catch (Exception e) {

		}
		try {
			if (isblocked(node.getxPosition() - 1, node.getyPosition(), node)) {
				if (nodeMap[node.getxPosition() - 1][node.getyPosition()].getState() == 0) {
					nodeMap[node.getxPosition() - 1][node.getyPosition()].setParent(node);
					nodeMap[node.getxPosition() - 1][node.getyPosition()].setMoveCost();
					nodeMap[node.getxPosition() - 1][node.getyPosition()].setState(1);
				}
			}

		} catch (Exception e) {

		}
		try {
			if (isblocked(node.getxPosition(), node.getyPosition() + 1, node)) {
				if (nodeMap[node.getxPosition()][node.getyPosition() + 1].getState() == 0) {
					nodeMap[node.getxPosition()][node.getyPosition() + 1].setParent(node);
					nodeMap[node.getxPosition()][node.getyPosition() + 1].setMoveCost();
					nodeMap[node.getxPosition()][node.getyPosition() + 1].setState(1);
				}

			}

		} catch (Exception e) {

		}
		try {
			if (isblocked(node.getxPosition(), node.getyPosition() + 1, node)) {
				if (nodeMap[node.getxPosition()][node.getyPosition() - 1].getState() == 0) {
					nodeMap[node.getxPosition()][node.getyPosition() - 1].setParent(node);
					nodeMap[node.getxPosition()][node.getyPosition() - 1].setMoveCost();
					nodeMap[node.getxPosition()][node.getyPosition() - 1].setState(1);
				}
			}

		} catch (Exception e) {

		}
		if (endNode.getParent() == null) {
			node.setState(2);
			expand(findLowestOpen());
		} else {
			done();
			return;
		}

	}

	private Node findLowestOpen() {
		Node lowestNode = null;
		for (int y = 0; y < 20; y++) {
			for (int x = 0; x < 20; x++) {
				if (nodeMap[x][y].getState() == 1) {
					if (lowestNode == null) {
						lowestNode = nodeMap[x][y];
					} else {
						if (nodeMap[x][y].getMoveCost() < lowestNode.getMoveCost()) {
							lowestNode = nodeMap[x][y];
						}
					}

				}
			}
		}

		return lowestNode;

	}

	public Node[][] getNodeMap() {
		return nodeMap;
	}

	public void setNodeMap(Node[][] nodeMap) {
		this.nodeMap = nodeMap;
	}

}

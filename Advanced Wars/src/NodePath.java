
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodePath {

	List<Node> path = new ArrayList<Node>();
	Node[][] map = new Node[Application.HEIGHT][Application.WIDTH];

	Node endNode;
	Node startNode;

	public NodePath(Unit start, Point cord) {

		endNode = new Node(cord.x, cord.y, null);
		startNode = new Node(start.getxPosition(), start.getyPosition(), endNode);
		if (start.getxPosition() == cord.x && start.getyPosition() == cord.y) {
			this.createPath();
			return;
		}

		startNode.setStatus(NodeStatus.OPEN);

		initMap();

		startNode.setStatus(NodeStatus.OPEN);
		endNode.setStatus(NodeStatus.UNVISITED);

		expand(findClosestOpenNodeToEndNode());
	}

	private void initMap() {
		// create node map
		for (int x = 0; x < Application.WIDTH; x++) {

			for (int y = 0; y < Application.HEIGHT; y++) {

				if (x == endNode.getxPosition() && y == endNode.getyPosition()) {
					map[x][y] = endNode;

				} else if (x == startNode.getxPosition() && y == startNode.getyPosition()) {
					map[x][y] = startNode;

				} else {
					map[x][y] = new Node(x, y, endNode);

				}
			}

		}
	}

	public List<Node> getPath() {
		return path;
	}

	private Node findClosestOpenNodeToEndNode() {
		Node closest = null;

		for (int col = 0; col < 20; col++) { // go through the list
												// of Nodes
			for (int row = 0; row < 20; row++) {

				if (map[col][row].getStatus() == NodeStatus.OPEN) { // Check if
																	// the node
					// is
					// open(1)
					if (closest == null) {
						closest = map[col][row];
					} else {

						if (map[col][row].getMoveCost() < closest.getMoveCost()) {
							closest = map[col][row];
						}
					}

				}

			}
		}

		return closest;

	}

	private final void createPath() {
		addToPathLoopParents(endNode);
		// path.remove(0);
		Collections.reverse(path);

	}

	private final void addToPathLoopParents(Node node) {
		Terrain.terrainArray[node.getxPosition()][node.getyPosition()].highlight();
		path.add(node);
		if (node.getParent() == null) {
			return;
		}
		addToPathLoopParents(node.getParent());

	}

	/**
	 * If the NodePath wasn't able to find a path. It's because of the moved
	 * Unit blocking the Path. This Method re-makes the Node Path with no moved
	 * Unit blocking.
	 */
	private final void restart() {

		endNode = new Node(endNode.getxPosition(), endNode.getyPosition(), null);
		startNode = new Node(startNode.getxPosition(), startNode.getyPosition(), endNode);

		initMap();

		for (Unit unit : Unit.UnitsArray) {
			if (unit.isMoved())
				map[unit.getxPosition()][unit.getyPosition()].setStatus(NodeStatus.UNVISITED);
		}
		startNode.setStatus(NodeStatus.OPEN);
		endNode.setStatus(NodeStatus.UNVISITED);

		map[endNode.getxPosition()][endNode.getyPosition()] = endNode;
		map[startNode.getxPosition()][startNode.getyPosition()] = startNode;
		expand(findClosestOpenNodeToEndNode());

	}

	private final void expand(Node expand) {
		if (expand == null) {
			restart();
			return;
		}

		try {
			map[expand.getxPosition() + 1][expand.getyPosition()].setParent(expand);
		} catch (Exception e) {

		}
		try {
			map[expand.getxPosition() - 1][expand.getyPosition()].setParent(expand);
		} catch (Exception e) {

		}
		try {
			map[expand.getxPosition()][expand.getyPosition() + 1].setParent(expand);
		} catch (Exception e) {

		}
		try {
			map[expand.getxPosition()][expand.getyPosition() - 1].setParent(expand);
		} catch (Exception e) {

		}

		if (endNode.getParent() != null) {
			createPath();
			return;
		} else {
			expand.setStatus(NodeStatus.CLOSED);
			expand(findClosestOpenNodeToEndNode());
		}
	}
}

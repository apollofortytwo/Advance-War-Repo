
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodePath {

	List<Node> path = new ArrayList<Node>();
	Node[][] map = new Node[Application.HEIGHT][Application.WIDTH];

	Node endNode;
	Node startNode;

	public NodePath(Unit start, Unit end) {
		endNode = new Node(end.getxPosition(), end.getyPosition(), null);
		startNode = new Node(start.getxPosition(), start.getyPosition(), endNode);
		startNode.setStatus(1);
		initMap();
		map[endNode.getxPosition()][endNode.getyPosition()] = endNode;
		map[startNode.getxPosition()][startNode.getyPosition()] = startNode;

		expand(findClosestOpenNodeToEndNode());
	}

	private void initMap() {
		// create node map
		for (int x = 0; x < Application.WIDTH; x++) {
			for (int y = 0; y < Application.HEIGHT; y++) {
				if (x == endNode.getxPosition() && y == endNode.getyPosition()) {

				} else if (x == startNode.getxPosition() && y == startNode.getyPosition()) {

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

				if (map[col][row].getStatus() == 1) { // Check if the node
														// is
														// open(1)
					if (closest == null) {
						closest = map[col][row];
					}else{
						
						if (map[col][row].getMoveCost() < closest.getMoveCost()) {
							closest = map[col][row];
						}
					}

				}

			}
		}
		if (endNode.getParent() != null) {
			createPath();
		}
		if (closest == null){
			createPath();
		}
		return closest;

	}

	private final void createPath() {
		addToPath(endNode);
		System.out.println("PATH CREATED");
		System.out.println(path.size());
		Collections.reverse(path);

	}

	private final void addToPath(Node node) {
		path.add(node);
		if (node.getParent() == null) {
			return;
		}
		addToPath(node.getParent());

	}

	private final void expand(Node expand) {
		
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

		}else{
			expand.setStatus(2);
			expand(findClosestOpenNodeToEndNode());
			
		}
	}
}

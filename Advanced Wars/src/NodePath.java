
import java.util.ArrayList;
import java.util.List;

public class NodePath {

	List<Node> path = new ArrayList<Node>();
	Node[][] map = new Node[Application.HEIGHT][Application.WIDTH];

	Node endNode;
	Node startNode;

	public NodePath(Unit start, Unit end) {
		endNode = new Node(end.getxPosition(), end.getyPosition(), endNode);
		startNode = new Node(start.getxPosition(), end.getyPosition(), endNode);

		// create node map
		for (int x = 0; x < Application.WIDTH; x++) {
			for (int y = 0; y < Application.HEIGHT; y++) {
		
				if (end.getxPosition() != x && end.getyPosition() != y) {
					if (start.getxPosition() != x && start.getyPosition() != y) {
						new Node(x, y, endNode);
					}
				}

			}
		}
		
	}

	public List<Node> getPath() {
		return path;
	}
}

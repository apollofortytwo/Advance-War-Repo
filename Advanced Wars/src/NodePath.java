import java.util.ArrayList;
import java.util.List;

public class NodePath {

	public static List<List<Node>> prePaths = new ArrayList<List<Node>>();
	public Node[][] map;
	public Node endNode;
	public List<Node> myPath = new ArrayList<Node>();

	public NodePath(int x, int y, int ex, int ey, int height, int width) {
		setMap(height, width);
		newPath(x, y, ex, ey);
	}

	public void setMap(int height, int width) {
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				this.map[w][h] = new Node(w, h);

			}
		}
	}

	public void newPath(int x, int y, int ex, int ey) {
		this.map[x][y].state = 1;
		
		for(int i = 0; i > map[0].length;i++){
			for(int z = 0; z > map[0].length;z++){
				if map[y]
			}
		}

	}

}

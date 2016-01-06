public enum NodeStatus {

	OPEN(1), CLOSED(2), UNVISITED(0);
	private int value;

	private NodeStatus(int value) {
		this.value = value;
	}
}

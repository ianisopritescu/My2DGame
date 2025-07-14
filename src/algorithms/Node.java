package algorithms;

public class Node {
	Node parent;
	public int col;
	public int row;
	int gCost;
	int hCost;
	int fCost;
	boolean solid;
	boolean open;
	boolean checked;

	public Node(int row, int col) {
		this.col = col;
		this.row = row;
		this.parent = null;
		this.solid = false;
		this.open = false;
		this.checked = false;
		this.gCost = 0;
		this.hCost = 0;
		this.fCost = 0;
	}
}

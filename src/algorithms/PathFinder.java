package algorithms;

import main.GamePanel;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PathFinder {
	GamePanel gp;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	PriorityQueue<Node> openPq = new PriorityQueue<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, currentNode, goalNode;
	boolean goalReached = false;
	int step;
	private final int[] di = {1, 0, -1, 0};
	private final int[] dj = {0, -1, 0, 1};


	public PathFinder(GamePanel gp) {
		this.gp = gp;
		instantiateNode();
	}

	void instantiateNode() {
		node = new Node[gp.maxWorldRow][gp.maxWorldCol];

		for (int i = 0; i < gp.maxWorldRow; i++) {
			for (int j = 0; j < gp.maxWorldCol; j++) {
				node[i][j] = new Node(i, j);
			}
		}
	}

	void resetNodes() {
		for (int i = 0; i < gp.maxWorldRow; i++) {
			for (int j = 0; j < gp.maxWorldCol; j++) {
				node[i][j].open = false;
				node[i][j].checked = false;
				node[i][j].solid = false;
			}
		}

		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}

	public void setNodes(int startRow, int startCol, int goalRow, int goalCol) {
		resetNodes();

		startNode = node[startRow][startCol];
		currentNode = startNode;
		goalNode = node[goalRow][goalCol];
		openList.add(startNode);

		for (int i = 0; i < gp.maxWorldRow; i++) {
			for (int j = 0; j < gp.maxWorldCol; j++) {
				int tileNum = gp.tileM.mapTileNum[i][j];
				if (gp.tileM.tile[tileNum].collision) {
					node[i][j].solid = true;
				}

				getCost(node[i][j]);
			}
		}

		gp.objMap.forEach((key, value) -> {
			if (!value.name.equals("door") && !value.name.contains("key")) {
				node[key.y][key.x].solid = true;
			}
		});
	}

	void getCost(Node node) {
		// g cost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;

		// h cost
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;

		node.fCost = node.gCost + node.hCost;
	}

	public boolean search() {
		while(!goalReached && step < 500) {
			int col = currentNode.col;
			int row = currentNode.row;

			// Check the current node
			currentNode.checked = true;
			openList.remove(currentNode);

			if (row - 1 >= 0) {
				openNode(node[row - 1][col]);
			}
			if (row + 1 < gp.maxWorldRow) {
				openNode(node[row + 1][col]);
			}
			if (col - 1 >= 0) {
				openNode(node[row][col - 1]);
			}
			if (col + 1 < gp.maxWorldCol) {
				openNode(node[row][col + 1]);
			}

			// Find the best node
			int bestNodeIndex = 0;
			int bestNodeFCost = 999;

			for (int i = 0; i < openList.size(); i++) {
				if (openList.get(i).fCost < bestNodeFCost) {
					bestNodeIndex = i;
					bestNodeFCost = openList.get(i).fCost;
				} else if (openList.get(i).fCost == bestNodeFCost) {
					if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}

			if (openList.isEmpty()) {
				break;
			}

			currentNode = openList.get(bestNodeIndex);

			if (currentNode == goalNode) {
				goalReached = true;
				trackThePath();
			}
			step++;
		}
		return goalReached;
	}

	void openNode(Node node) {
		if (!node.open && !node.checked && !node.solid) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}

	void trackThePath() {
		Node current = goalNode;

		while (current != startNode) {
			pathList.addFirst(current);
			current = current.parent;
		}
	}
}

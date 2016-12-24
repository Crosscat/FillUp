package generation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

class Grid {
	private int width;
	private int height;
	
	private GridSlot[][] nodeGrid;

	@SuppressWarnings("unchecked")
	Grid(int height, int width) {
		this.width = width;
		this.height = height;
		
		nodeGrid = new GridSlot[width][height];
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				nodeGrid[x][y] = new GridSlot();
			}
		}
	}
	
	void insertNode(PathNode node) {
		getSlot(node.position).add(node);
	}
	
	String buildPuzzleString()
	{
		StringJoiner rows = new StringJoiner("\n");
		for (int y=0; y<height; y++) {
			StringJoiner row = new StringJoiner(",");
			
			for (int x=0; x<width; x++) {
				row.add(nodeGrid[x][y].slotCode());
			}
			rows.add(row.toString());
		}
		return rows.toString();
	}
	
	boolean tryAddWall(Point point) {
		GridSlot slot = getSlot(point);
		if (slot.nodes.size() > 0) {
			return false;
		} else {
			slot.isWall = true;
			return true;
		}
	}
	
	GridSlot getSlot(Point point) {
		return nodeGrid[point.X][point.Y];
	}
	
	boolean isPointAvailable(Point point, int touchesAllowed) {
		if (point.X < 0 || point.Y < 0 || point.X >= width || point.Y >= height) {
			return false; 
		}
		
		GridSlot slot = getSlot(point);		
		return !slot.isWall && slot.nodes.size() < touchesAllowed;
	}
	
	private static class GridSlot {
		List<PathNode> nodes;
		boolean isWall;
		
		GridSlot() {
			nodes = new ArrayList<>();
		}
		
		void add(PathNode node)
		{
			nodes.add(node);
		}
		
		String slotCode() {
			return ""+nodes.size();
		}
	}
}

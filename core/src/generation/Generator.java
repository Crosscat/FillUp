package generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

	private int gridWidth;
	private int gridHeight;
	private int numWalls;
	private int maxTouches;
	
	private Grid grid;
	private List<PathNode> path;
	
	private Random rand;
	
	
	public Generator(int gridWidth, int gridHeight, int numWalls, int maxTouches) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.numWalls = numWalls;
		this.maxTouches = maxTouches;
		
		rand = new Random();
	}
	
	public String createPuzzle(){
		grid = new Grid(gridWidth, gridHeight);
		
		buildInitialPath();
		buildWalls();
		
		for (int i=1; i<=maxTouches; i++) {
			pass(i);
		}
		
		return grid.buildPuzzleString();
	}
	
	private void buildInitialPath()
	{
		path = new ArrayList<>();
		
		Point start = randomPoint();
		Point end = randomPoint();
		start.X = 0;
		end.X = gridWidth - 1;
		
		Point cursor = start;
		
		do {
			createNode(cursor);
			
			if (cursor.X < end.X) {
				cursor = cursor.right();
			} 
			else if (cursor.Y < end.Y) 
			{
				cursor = cursor.below();
			}
			else if (cursor.Y > end.Y)
			{
				cursor = cursor.above();
			}
		} while (!cursor.equals(end));

		createNode(end);
	}
	
	private void buildWalls() {
		int i=0;
		while (i < numWalls) {
			boolean wallCreated = grid.tryAddWall(randomPoint()); 
			if (wallCreated) i++;
		}
	}
	
	private void pass(int touches) {
		for (int i=0; i< 5; i++) {
			List<Indent> indentCandidates = findIndentCandidates(touches);
		
			if (indentCandidates.size() > 0) {
				Indent selected = indentCandidates.get(rand.nextInt(indentCandidates.size()));
				insertNode(selected.newNodePoint1, selected.pathIndex+1);
				insertNode(selected.newNodePoint2, selected.pathIndex+2);
			}
		}
	}
	
	private List<Indent> findIndentCandidates(int touches) {
		List<Indent> indents = new ArrayList<Indent>();
		
		for (int i=0; i<path.size()-1; i++) {
			Point point = path.get(i).position;
			Point nextPoint = path.get(i+1).position;
			
			boolean horizontal = (point.Y == nextPoint.Y);			
			if (horizontal) {
				Point above1 = point.above();
				Point above2 = nextPoint.above();				
				checkForIndent(indents, i, touches, above1, above2);

				Point below1 = point.below();
				Point below2 = nextPoint.below();
				checkForIndent(indents, i, touches, below1, below2);
			} else {
				Point left1 = point.left();
				Point left2 = nextPoint.left();				
				checkForIndent(indents, i, touches, left1, left2);

				Point right1 = point.right();
				Point right2 = nextPoint.right();
				checkForIndent(indents, i, touches, right1, right2);
			}				
		}
		
		return indents;
	}
	
	private void checkForIndent(List<Indent> indents, int index, int touches, Point point1, Point point2) {
		if (grid.isPointAvailable(point1, touches) && grid.isPointAvailable(point2, touches))
		{
			indents.add(new Indent(index, point1, point2));
		}
	}
	
	private void createNode(Point point)
	{
		insertNode(point, -1);
	}
	
	private void insertNode(Point point, int index) {
		PathNode node = new PathNode();
		node.position = point;
		
		if (index < 0 ) {
			path.add(node);
		} else {
			path.add(index, node);
		}
		
		grid.insertNode(node);	
	}
	
	private Point randomPoint()
	{
		return new Point(rand.nextInt(gridWidth), rand.nextInt(gridHeight));
	}
	
	private class Indent {
		int pathIndex;
		Point newNodePoint1;
		Point newNodePoint2;
		
		Indent(int index, Point p1, Point p2) {
			pathIndex = index;
			newNodePoint1 = p1;
			newNodePoint2 = p2;
		}
	}

}


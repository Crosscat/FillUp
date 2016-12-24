package generation;

class Point {
	public int X;
	public int Y;
	
	Point(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public Point above()
	{
		return new Point(X, Y-1);
	}
	
	public Point below()
	{
		return new Point(X, Y+1);
	}
	
	public Point left()
	{
		return new Point(X-1, Y);
	}
	
	public Point right()
	{
		return new Point(X+1, Y);
	}
	
	public boolean equals(Point point)
	{
		return this.X == point.X && this.Y == point.Y;
	}
	
}

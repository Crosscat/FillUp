package com.gooeygames.fillup.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.gooeygames.fillup.rendering.Drawable;

public class Board extends Drawable{
	
	private Tile[][] tiles;
	private List<Tile> tileList;
	private Tile previousTile;
	private int boardWidth, boardHeight;

	public Board(char[][] boardState, float scale, int xPos, int yPos){
		this(boardState.length, boardState[0].length, scale, xPos, yPos);
		createBoard(boardState);
	}
	
	public Board(int boardWidth, int boardHeight, float scale, int xPos, int yPos){
		this.position = new Vector2(xPos, yPos);
		this.scale = scale;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}
	
	public void createBoard(char[][] boardState){
		tiles = new Tile[boardWidth][boardHeight];
		tileList = new ArrayList<Tile>();
		for (int i = 0; i < boardWidth; i++){
			for (int j = 0; j < boardHeight; j++){
				createTile(boardState[i][j], i, j);
			}
		}
	}
	
	private void createTile(char c, int xCoord, int yCoord){
		Tile t = new Tile(scale);
		float x = xCoord * scale * Tile.DEFAULTSIZE + position.x;
		float y = yCoord * scale * Tile.DEFAULTSIZE + position.y;
		Vector2 pos = new Vector2(x, y);

		t.setPoint(xCoord, yCoord);
		t.setPosition(pos);
		tiles[xCoord][yCoord] = t;
		tileList.add(t);
	}
	
	@Override
	public void draw(){
		tileList.forEach(t -> t.draw());
	}
	
	@Override
	public void update(float delta){
		tileList.forEach(t -> t.update(delta));
	}
	
	public boolean checkInput(Vector2 touchPos){
		boolean newTile = touchTile(getTileAtScreenPosition(touchPos));
		if (newTile){
			if (allTouched()){
				markAllTiles(Color.RED);
				return true;
			}
		}
		return false;
	}
	
	private boolean touchTile(Tile tile){
		if (tile == null || tile.isTouched() || !areAdjacent(previousTile, tile)) {
			return false;
		}
		tile.touch();
		if (previousTile != null){
			previousTile.setColor(Color.BLUE);
		}
		previousTile = tile;
		return true;
	}
	
	public Vector2 screenToTileCoord(Vector2 pos){
		float xCoord = (pos.x - position.x) / (scale * Tile.DEFAULTSIZE);
		float yCoord = (pos.y - position.y) / (scale * Tile.DEFAULTSIZE);
		if (xCoord < 0 || xCoord >= boardWidth || yCoord < 0 || yCoord >= boardHeight) {
			return null;
		}
		return new Vector2((int)xCoord, (int)yCoord);
	}
	
	public Tile getTileAtScreenPosition(Vector2 pos){
		Vector2 tileCoords = screenToTileCoord(pos);
		if (tileCoords == null) {
			return null;
		}
		return tiles[(int) tileCoords.x][(int) tileCoords.y];
	}
	
	public Tile getTile(int x, int y){
		return tiles[x][y];
	}
	
	public boolean areAdjacent(Tile previousTile, Tile newTile){
		if (previousTile == null) {
			return true;
		}
		Vector2 previousPos = previousTile.getPoint();
		Vector2 newPos = newTile.getPoint();
		
		int xDiff = (int) (previousPos.x - newPos.x);
		int yDiff = (int) (previousPos.y - newPos.y);
		if ((Math.abs(xDiff) == 1 && Math.abs(yDiff) == 0) || (Math.abs(yDiff) == 1 && Math.abs(xDiff) == 0)){
			return true;
		}
		return false;
	}
	
	public boolean allTouched(){
		for (Tile t : tileList){
			if (!t.isTouched()){
				return false;
			}
		}
		return true;
	}
	
	public void markAllTiles(Color color){
		tileList.forEach(t -> t.setColor(color));
	}
}

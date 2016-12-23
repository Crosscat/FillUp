package com.gooeygames.fillup.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.gooeygames.fillup.game.Board;
import com.gooeygames.fillup.game.Tile;

public class BoardTests {
	
	Board board;
	
	@Before
	public void setup(){
		board = getPrototypeBoard();
	}
	
	private Board getPrototypeBoard(){
		return new Board(new char[5][5], 1, 0, 0);
	}

	@Test
	public void testScreenToTileCoordTopRight(){
		Vector2 coords = board.screenToTileCoord(new Vector2(159, 159));
		Assert.assertEquals(4, coords.x, 0);
		Assert.assertEquals(4, coords.y, 0);
	}
	
	@Test
	public void testScreenToTileCoordBottomLeft(){
		Vector2 coords = board.screenToTileCoord(new Vector2(1, 1));
		Assert.assertEquals(0, coords.x, 0);
		Assert.assertEquals(0, coords.y, 0);
	}
	
	@Test
	public void testScreenToTileCoordOutsideTopRight(){
		Vector2 coords = board.screenToTileCoord(new Vector2(161, 161));
		Assert.assertEquals(null, coords);
	}
	
	@Test
	public void testScreenToTileCoordOutsideBottomLeft(){
		Vector2 coords = board.screenToTileCoord(new Vector2(-1, -1));
		Assert.assertEquals(null, coords);
	}
	
	@Test
	public void testAreAdjacentTrue(){
		Tile t1 = board.getTileAtScreenPosition(new Vector2(1, 1));
		Tile t2 = board.getTileAtScreenPosition(new Vector2(33, 1));
		Assert.assertEquals(true, board.areAdjacent(t1, t2));
	}
	
	@Test
	public void testAreAdjacentFalse(){
		Tile t1 = board.getTileAtScreenPosition(new Vector2(1, 1));
		Tile t2 = board.getTileAtScreenPosition(new Vector2(33, 33));
		Assert.assertEquals(false, board.areAdjacent(t1, t2));
	}
	
	@Test
	public void testGetTileAtPosition(){
		Tile t = board.getTileAtScreenPosition(new Vector2(1, 1));
		Assert.assertEquals(board.getTile(0, 0), t);
		Assert.assertNotEquals(board.getTile(0, 1), t);
	}
	
	@Test
	public void allTouched(){
		board.getTiles().forEach(t -> t.touch());
		Assert.assertEquals(true, board.allTouched());
	}
	
	@Test
	public void noneTouched(){
		Assert.assertEquals(false, board.allTouched());
	}
	
	@Test
	public void someTouched(){
		board.getTiles().get(0).touch();
		Assert.assertEquals(false, board.allTouched());
	}
}

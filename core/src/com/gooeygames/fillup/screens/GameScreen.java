package com.gooeygames.fillup.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.gooeygames.fillup.assets.Textures;
import com.gooeygames.fillup.game.Board;
import com.gooeygames.fillup.game.BoardStateGenerator;
import com.gooeygames.fillup.game.FillUp;
import com.gooeygames.fillup.rendering.Drawable;
import com.gooeygames.fillup.rendering.Renderer;

import generation.Generator;

public class GameScreen implements Screen{
	
	List<String> puzzles;
	List<Drawable> drawables;
	Board board;
	boolean restartFlag, resetFlag, acceptTouch;
	char[][] puzzle;

	@Override
	public void show() {
		try {
			puzzles = BoardStateGenerator.loadPuzzles("puzzles.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}
	
	private String getRandomPuzzle(){
		return puzzles.get(FillUp.random.nextInt(puzzles.size()));
	}
	
	private void initialize(){
		Generator generator = new Generator(6, 6, 5, 2);
		puzzle = BoardStateGenerator.generate(generator.createPuzzle());
		restartPuzzle();
	}
	
	private void restartPuzzle(){
		board = new Board(puzzle, 2, 50, 50);
		
		drawables = new ArrayList<>();
		drawables.add(board);
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isTouched() && acceptTouch){
			Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			
			restartFlag = board.checkInput(touchPos);
		}else if (!Gdx.input.isTouched()){
			acceptTouch = true;
		}
		
		for (Drawable drawable : drawables){
			drawable.update(delta);
		}
		
		Gdx.gl20.glClearColor(0f,.3f,0f,1f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Renderer.instance.startDraw();
		for (Drawable drawable : drawables){
			drawable.draw();
		}
		Renderer.instance.endDraw();
		
		if (Gdx.input.isKeyJustPressed(Keys.Q)){
			restartFlag = true;
		}
		if (Gdx.input.isKeyJustPressed(Keys.R)){
			resetFlag = true;
		}
		
		if (restartFlag || resetFlag){
			if (restartFlag){
				initialize();
			}else if (resetFlag){
				restartPuzzle();
			}
			restartFlag = false;
			resetFlag = false;
			acceptTouch = false;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		Textures.dispose();
	}

}

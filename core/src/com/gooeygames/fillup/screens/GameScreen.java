package com.gooeygames.fillup.screens;

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
import com.gooeygames.fillup.rendering.Drawable;
import com.gooeygames.fillup.rendering.Renderer;

public class GameScreen implements Screen{
	
	List<Drawable> drawables;
	Board board;
	boolean restartFlag;

	@Override
	public void show() {
		initialize();
	}
	
	private void initialize(){
		board = new Board(BoardStateGenerator.generate(), 2, 50, 50);;
		
		drawables = new ArrayList<>();
		drawables.add(board);
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isTouched() && !restartFlag){
			Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			
			restartFlag = board.checkInput(touchPos);
		}else if (!Gdx.input.isTouched()){
			restartFlag = false;
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
		
		if (restartFlag){
			initialize();
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

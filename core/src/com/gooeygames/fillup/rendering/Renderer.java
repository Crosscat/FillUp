package com.gooeygames.fillup.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Renderer {
	
	public static Renderer instance = new Renderer();
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	public Renderer(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		shapeRenderer = new ShapeRenderer();
	}
	
	public void startDraw(){
		batch.begin();
	}
	
	public void endDraw(){
		batch.end();
	}
	
	public void draw(Drawable d){
		draw(d.getTexture(), d.getPosition(), d.getBounds().width, d.getBounds().height, d.getColor());
	}
	
	public void draw(TextureRegion texture, Vector2 position, float width, float height){
		batch.draw(texture, position.x, position.y, width, height);
	}
	
	public void draw(TextureRegion texture, Vector2 position, float width, float height, Color color){
		batch.setColor(color);
		batch.draw(texture, position.x, position.y, width, height);
		batch.setColor(Color.WHITE);
	}
}

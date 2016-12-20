package com.gooeygames.fillup.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Drawable {

	protected TextureRegion texture;
	protected Vector2 position = new Vector2();
	protected Rectangle bounds = new Rectangle();
	protected Color color = Color.WHITE;
	protected float scale = 1;
	
	public TextureRegion getTexture() {
		return texture;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
		setBounds(new Rectangle(position.x, position.y, bounds.width, bounds.height));
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract void draw();
	
	public abstract void update(float delta);
		
}

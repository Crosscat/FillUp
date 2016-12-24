package com.gooeygames.fillup.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gooeygames.fillup.assets.TextureKey;
import com.gooeygames.fillup.rendering.AnimatedDrawable;
import com.gooeygames.fillup.rendering.AnimationSet;

public class Tile extends AnimatedDrawable{
	
	public static final int DEFAULTSIZE = 32;
	private int xPoint, yPoint;
	private int remainingTouches;
	
	public Tile(int touchCount){
		this(touchCount, 1);
	}
	
	public Tile(int touchCount, float scale){
		this.remainingTouches = touchCount;
		this.scale = scale;
		setBounds(new Rectangle(0, 0, DEFAULTSIZE * scale, DEFAULTSIZE * scale));
		refreshColor();
	}
	
	public Vector2 getPoint(){
		return new Vector2(xPoint, yPoint);
	}
	
	public void setPoint(int x, int y){
		xPoint = x;
		yPoint = y;
	}
	
	public void touch(){
		remainingTouches --;
		refreshColor();
	}
	
	public boolean isComplete(){
		return remainingTouches == 0;
	}

	@Override
	protected void setAnimations(AnimationSet<AnimationKey> animationSet) {
		addAnimation(AnimationKey.Default, TextureKey.Panel, 32, 32, 1, 0, false);
	}

	public void refreshColor() {
		color = getColor(remainingTouches);
	}
	
	private Color getColor(int touchesLeft){
		switch (touchesLeft){
		case 0:
			return Color.WHITE;
		case 1:
			return new Color(0, .75f, 0, 1);
		case 2:
			return new Color(0, .5f, 0, 1);
		case 3:
			return new Color(0, .25f, 0, 1);
		}
		return null;
	}

	
}

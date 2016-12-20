package com.gooeygames.fillup.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gooeygames.fillup.assets.TextureKey;
import com.gooeygames.fillup.rendering.AnimatedDrawable;
import com.gooeygames.fillup.rendering.AnimationSet;

public class Tile extends AnimatedDrawable{
	
	public static final int DEFAULTSIZE = 32;
	private int xPoint, yPoint;
	
	public Tile(){
		this(1);
	}
	
	public Tile(float scale){
		this.scale = scale;
		setBounds(new Rectangle(0, 0, DEFAULTSIZE * scale, DEFAULTSIZE * scale));
	}
	
	public Vector2 getPoint(){
		return new Vector2(xPoint, yPoint);
	}
	
	public void setPoint(int x, int y){
		xPoint = x;
		yPoint = y;
	}

	@Override
	protected void setAnimations(AnimationSet<AnimationKey> animationSet) {
		addAnimation(AnimationKey.Default, TextureKey.Panel, 32, 32, 1, 0, false);
	}

	
}

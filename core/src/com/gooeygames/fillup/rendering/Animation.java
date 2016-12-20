package com.gooeygames.fillup.rendering;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.gooeygames.fillup.game.FillUp;

public class Animation {
	private List<TextureRegion> textures;
	private int frameCount, currentFrame;
	private boolean isLoop, random, finished, flipped, frozen;
	private float frameCounter, frameDuration, frameWidth, frameHeight, scale;
	
	public Animation(Texture texture, int frameWidth, int frameHeight, int frameCount, float frameDuration, boolean isLoop){
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.frameCount = frameCount;
		this.frameDuration = frameDuration;
		this.isLoop = isLoop;
		currentFrame = 0;
		
		textures = new ArrayList<>();
		for (int i = 0; i < frameCount; i++){
			TextureRegion region = new TextureRegion(texture, i*frameWidth, 0, frameWidth, frameHeight);
			textures.add(region);
		}
	}
	
	public void restart(){
		frameCounter = 0;
		currentFrame = 0;
		finished = false;
		frozen = false;
	}
	
	public void desync(){
		currentFrame = FillUp.random.nextInt(frameCount);
		frameCounter = FillUp.random.nextInt((int)(frameDuration*100))/100f;
	}
	
	public void setRandom(){
		random = true;
	}
	
	public void setScale(float scale){
		this.scale = scale;
	}
	
	public void draw(Vector2 position, float scale, Color color){
		TextureRegion region = textures.get(currentFrame);
		
		if (flipped) {
			region.flip(true, false);
		}
		
		Renderer.instance.draw(region, position, frameWidth * scale, frameHeight * scale, color);
	}
	
	public void update(float delta){
		if (frozen) {
			return;
		}
		if (frameDuration == 0){
			return;
		}
		
		frameCounter += delta;
		while (frameCounter >= frameDuration){
			if (!random){
				if (currentFrame < frameCount - 1){
					currentFrame ++;
				}else if (isLoop){
					currentFrame = 0;
				}else if (!isLoop){
					finished = true;
				}
			}else{
				currentFrame = FillUp.random.nextInt(frameCount);
			}
			
			frameCounter -= frameDuration;
		}
	}
	
	public void setFrameDuration(float duration){
		frameDuration = duration;
		frameCounter = 0;
	}
	
	public boolean isFinished(){
		if (frameCount == 1) {
			return true;
		} else {
			return finished;
		}
	}
	
	public boolean isFlipped(){
		return flipped;
	}
	
	public void setFlipped(boolean b){
		flipped = b;
	}
	
	public void setFrozen(boolean b){
		frozen = b;
	}
	
	public int getCurrentFrame(){
		return currentFrame;
	}
}
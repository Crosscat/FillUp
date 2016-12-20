package com.gooeygames.fillup.rendering;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class AnimationSet<T> {
	Map<T, Animation> animationMap;
	Animation currentAnimation;
	
	public AnimationSet(){
		animationMap = new HashMap<T, Animation>();
	}
	
	public void addAnimation(T key, Animation value){
		animationMap.put(key, value);
	}
	
	public boolean hasAnimation(T key){
		return animationMap.get(key) != null;
	}
	
	public void showAnimation(T key){
		currentAnimation = animationMap.get(key);
		currentAnimation.restart();
	}
	
	public void flipAnimation(boolean b){
		currentAnimation.setFlipped(b);
	}
	
	public void flipAll(boolean b){
		for (T key: animationMap.keySet()){
			animationMap.get(key).setFlipped(b);
		}
	}
	
	public void update(float delta){
		currentAnimation.update(delta);
	}
	
	public void draw(Vector2 position, float scale, Color color){
		currentAnimation.draw(position, scale, color);
	}
	
	public Animation getAnimation(){
		return currentAnimation;
	}
}

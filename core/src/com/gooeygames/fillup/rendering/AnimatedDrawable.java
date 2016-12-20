package com.gooeygames.fillup.rendering;

import com.gooeygames.fillup.assets.TextureKey;
import com.gooeygames.fillup.assets.Textures;

public abstract class AnimatedDrawable extends Drawable{
	
	public enum AnimationKey{
		Default
	}

	protected AnimationSet<AnimationKey> animationSet;
	
	public AnimatedDrawable(){
		animationSet = new AnimationSet<AnimationKey>();
		setAnimations(animationSet);
		if (hasAnimation(AnimationKey.Default)){
			animationSet.showAnimation(AnimationKey.Default);
		}
	}
	
	@Override
	public void update(float delta){
		animationSet.update(delta);
	}
	
	@Override
	public void draw(){
		animationSet.draw(position, scale, color);
	}
	
	protected void addAnimation(AnimationKey animKey, TextureKey textureKey, int width, int height, int frames, float frameDuration, boolean isLoop){
		if (!Textures.isLoaded()) {
			return;
		}
		Animation anim = new Animation(Textures.get(textureKey), width, height, frames, frameDuration, isLoop);
		animationSet.addAnimation(AnimationKey.Default, anim);
	}
	
	protected boolean hasAnimation(AnimationKey animKey){
		return animationSet.hasAnimation(animKey);
	}
	
	protected abstract void setAnimations(AnimationSet<AnimationKey> animationSet);
}

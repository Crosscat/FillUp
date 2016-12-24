package com.gooeygames.fillup.game;

import com.gooeygames.fillup.assets.TextureKey;
import com.gooeygames.fillup.rendering.AnimatedDrawable;
import com.gooeygames.fillup.rendering.AnimationSet;

public class Cursor extends AnimatedDrawable{

	@Override
	protected void setAnimations(AnimationSet<AnimationKey> animationSet) {
		addAnimation(AnimationKey.Default, TextureKey.Cursor, 32, 32, 1, 0, false);
	}

}

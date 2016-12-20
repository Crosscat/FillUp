package com.gooeygames.fillup.game;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.gooeygames.fillup.assets.Textures;
import com.gooeygames.fillup.screens.GameScreen;

public class FillUp extends Game {
	
	Screen gameScreen = new GameScreen();
	
	public static Random random = new Random();
	
	@Override
	public void create() {
		Textures.load();
		setScreen(gameScreen);
	}
}

package com.gooeygames.fillup.assets;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {

	private static Map<TextureKey, Texture> textures = new HashMap<>();
	
	private static boolean isLoaded;
	
	public static void load(){
		textures.put(TextureKey.Panel, new Texture(Gdx.files.internal("textures/panel.png")));
		isLoaded = true;
	}
	
	public static Texture get(TextureKey key){
		return textures.get(key);
	}
	
	public static void dispose(){
		for (Texture texture : textures.values()){
			texture.dispose();
		}
	}
	
	public static boolean isLoaded(){
		return isLoaded;
	}
}


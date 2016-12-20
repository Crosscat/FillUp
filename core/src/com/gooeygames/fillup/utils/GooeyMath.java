package com.gooeygames.fillup.utils;

public class GooeyMath {

	public static float bounds(float value, float lower, float upper){
		if (value < lower) {
			return lower;
		}
		if (value > upper) {
			return upper;
		}
		return value;
	}
}

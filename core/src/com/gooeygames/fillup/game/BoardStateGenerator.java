package com.gooeygames.fillup.game;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class BoardStateGenerator {

	public static char[][] generate(String puzzle){
		
		char[][] boardState = stringTo2DCharArray(puzzle);
		
		return boardState;
	}
	
	public static char[][] stringTo2DCharArray(String input){
		String[] lines = input.split("\\r?\\n");
		int width = lines[0].split(",").length;
		
		char[][] parsedChars = new char[width][lines.length];
		
		for (int i = 0; i < lines.length; i++){
			String[] chars = lines[i].split(",");
			for (int j = 0; j < chars.length; j++){
				parsedChars[j][lines.length-1-i] = chars[j].charAt(0);
			}
		}
		
		return parsedChars;
	}
	
	public static List<String> loadPuzzles(String filePath) throws IOException{
		String puzzleData = FileUtils.readFileToString(new File(filePath), "UTF-8");
		List<String> puzzles = Arrays.asList(puzzleData.split("\\r?\\nxxx\\r?\\n"));
		return puzzles;
	}
}

package br.com.battlemonsters.controllers;

import java.util.List;
import java.util.Random;

public class Util {
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public static int randInt(int[] array) {
		int index = randInt(0, array.length);
	    return array[index - 1];
	}
	
	public static int randInt(List<Integer> list) {
		int index = randInt(1, list.size());
	    return list.get(index - 1);
	}
	
}

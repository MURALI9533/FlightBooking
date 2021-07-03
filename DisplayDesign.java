package com.me.textdesign;

public class DisplayDesign {
	private DisplayDesign() {
		throw new IllegalStateException();
	}
	public static String design(String word) {
		StringBuilder modWord = new StringBuilder();
		for(int i=word.length();i<30;i++) {
			modWord.append(" ");
		}
		word += modWord.toString();
		return word;
	}
}

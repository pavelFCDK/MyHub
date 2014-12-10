package com.articles;

public class Article {
	private static String id;
	private static String title;
	
	static public void setId(String i){
		id = i;
	}
	static public String getId(){
		return id;
	}
	
	static public void setTitle(String t){
		title = t;
	}
	static public String getTitle(){
		return title;
	}
}

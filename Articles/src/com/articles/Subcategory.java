package com.articles;

public class Subcategory {
	private static String category;
	private static String id;
	private static String name;
	
	static public void setName(String n){
		name = n;
	}
	static public String getName(){
		return name;
	}
	
	static public void setCategory(String c){
		category = c;
	}
	static public String getCategory(){
		return category;
	}
	
	static public void setId(String i){
		id = i;
	}
	static public String getId(){
		return id;
	}
}

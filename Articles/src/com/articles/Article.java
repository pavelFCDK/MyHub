package com.articles;

import android.graphics.Bitmap;

public class Article {
	private String id;
	private String title;
	private String subtilte;
	private String image;
	private Bitmap bitmap;
	private static String author;
	private static String content;

	
	private static String currentId;
	private static String currentTitle;
	
	Article(){};
	
	public void setId(String i){
		id = i;
	}
	public String getId(){
		return id;
	}
	
	public void setTitle(String t){
		title = t;
	}
	public String getTitle(){
		return title;
	}
	
	public void setSubtilte(String s){
		subtilte = s;
	}
	public String getSubtitle(){
		return subtilte;
	}
	
	public void setImage(String s){
		image = s; 
	}
	public String getImage(){
		return image;
	}
	
	public void setBitmap(Bitmap b){
		bitmap = b;
	}
	public Bitmap getBitmap(){
		return bitmap;
	}
	
	static public void setAuthor(String a){
		author = a;
	}
	static public String getAuthor(){
		return author;
	}
	
	static public void setContent(String c){
		content = c;
	}
	static public String getContent(){
		return content;
	}
	
	static public void setCurrentId(String i){
		currentId = i;
	}
	static public String getCurrentId(){
		return currentId;
	}
	
	static public void setCurrentTitle(String i){
		currentTitle = i;
	}
	static public String getCurrentTitle(){
		return currentTitle;
	}
}

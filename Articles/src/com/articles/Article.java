package com.articles;

public class Article {
	private static String id;
	private static String title;
	private static String author;
	private static String content;
	private static String subtilte;
	
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
}

package com.activity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.articles.Article;
import com.articles.R;
import com.articles.R.id;
import com.articles.R.layout;
import com.articles.R.menu;
import com.atask.ListArticle;

public class ArticlesListActivity extends Activity {

	private ProgressDialog pDialog;
	Map<String, String> articles = new LinkedHashMap<String, String>();
	ArrayList<Article> aList = new ArrayList<Article>();
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articles_list);
		
		lv = (ListView) findViewById(R.id.listViewArticles);
		
		ListArticle la = new ListArticle(ArticlesListActivity.this, pDialog, aList, lv, articles);
		la.execute();
		//new ListArticle().execute();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,
		    		  int position, long id) {
		        Article.setCurrentTitle(lv.getItemAtPosition(position).toString());
		        List<String> list = new ArrayList<String>(articles.keySet());
		        Article.setCurrentId(list.get(position));
		        //Log.d("asd", list.get(position));
				Intent i = new Intent(ArticlesListActivity.this, ArticleViewActivity.class);
				startActivity(i);
		      }
		    });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.articles_list, menu);
		return true;
	}
}
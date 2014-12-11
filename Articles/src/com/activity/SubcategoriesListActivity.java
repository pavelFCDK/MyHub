package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import com.articles.R;
import com.articles.Subcategory;
import com.articles.R.id;
import com.articles.R.layout;
import com.articles.R.menu;
import com.atask.ListSubcategory;

import android.inputmethodservice.Keyboard.Key;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SubcategoriesListActivity extends Activity {

	private static final String LIST_OF_ALL_CATEGORIES_URL = "http://figaro.service.yagasp.com/article/categories";
	private ProgressDialog pDialog;
	Map<String, String> subcategories = new LinkedHashMap<String, String>();
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subcategories_list);
		
		lv = (ListView) findViewById(R.id.listViewSubcategories);
		
		ListSubcategory ls = new ListSubcategory(SubcategoriesListActivity.this, pDialog, subcategories, lv);
		ls.execute();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,
		    		  int position, long id) {
		        Subcategory.setName(lv.getItemAtPosition(position).toString());
		        List<String> list = new ArrayList<String>(subcategories.keySet());
		        Subcategory.setId(list.get(position));
		        //Log.d("asd", list.get(position));
				Intent i = new Intent(SubcategoriesListActivity.this, ArticlesListActivity.class);
				startActivity(i);
		      }
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.subcategories_list, menu);
		return true;
	}
}

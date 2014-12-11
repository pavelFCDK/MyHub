package com.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.articles.Category;
import com.articles.R;
import com.articles.R.id;
import com.articles.R.layout;
import com.articles.R.menu;
import com.atask.ListCategory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CategoriesListActivity extends Activity{
	
	private ProgressDialog pDialog;
	private ListView lv;
    private ArrayList<String> categories = new ArrayList<String>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		lv = (ListView) findViewById(R.id.listViewCategories);
		
		ListCategory lc = new ListCategory(CategoriesListActivity.this, lv, categories, pDialog);
		lc.execute();
		
		 lv.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,
		    		  int position, long id) {
		        Category.setName(lv.getItemAtPosition(position).toString());
		        Log.d("asd", Category.getName());
				Intent i = new Intent(CategoriesListActivity.this, SubcategoriesListActivity.class);
				startActivity(i);
		      }
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

package com.articles;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	
	private static final String LIST_OF_ALL_CATEGORIES_URL = "http://figaro.service.yagasp.com/article/categories";
	
	private ProgressDialog pDialog;
	
	private ListView lv;
    
    private List<String> categories = new ArrayList<String>();
    private List<String> categoriesId = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		lv = (ListView) findViewById(R.id.listViewCategories);
		
		new ListCategory().execute();
		
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
	
	class ListCategory extends AsyncTask<String, String, String>{

		JSONObject c = null;
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CategoriesListActivity.this);
            pDialog.setMessage("Start progress...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			JSONParser jParser = new JSONParser();
			JSONArray json = jParser.getJSONFromUrl(LIST_OF_ALL_CATEGORIES_URL);
			for (int i = 0; i < json.length(); i++){
				try {
					categories.add(json.getJSONObject(i).getString("category"));
					//categoriesId.add(json.getJSONObject(i).getString("id"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}	
		
		 protected void onPostExecute(String file_url) {
	            // dismiss the dialog once product deleted
	            pDialog.dismiss();
	            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
	                    CategoriesListActivity.this, 
	                    android.R.layout.simple_list_item_1,
	                    categories);

	            lv.setAdapter(arrayAdapter); 
		 }

	}
}

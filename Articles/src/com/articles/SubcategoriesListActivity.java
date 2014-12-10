package com.articles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

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
		
		new ListSubcategory().execute();
		
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
	
	class ListSubcategory extends AsyncTask<String, String, String>{

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SubcategoriesListActivity.this);
            pDialog.setMessage("Start progress...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONParser jParser = new JSONParser();
			JSONArray json = jParser.getJSONFromUrl(LIST_OF_ALL_CATEGORIES_URL);
			if (Category.getName().equals("Actualités")){
				try {
					JSONArray arr = json.getJSONObject(0).getJSONArray("subcategories");
					for (int i = 0; i < arr.length(); i++){
						//Log.d("tag", arr.getJSONObject(i).toString());
						subcategories.put(arr.getJSONObject(i).getString("id"), arr.getJSONObject(i).getString("name"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (Category.getName().equals("Économie")){
				try {
					JSONArray arr = json.getJSONObject(1).getJSONArray("subcategories");
					for (int i = 0; i < arr.length(); i++){
						//Log.d("tag", arr.getJSONObject(i).toString());
						subcategories.put(arr.getJSONObject(i).getString("id"), arr.getJSONObject(i).getString("name"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (Category.getName().equals("Sport")){
				try {
					JSONArray arr = json.getJSONObject(2).getJSONArray("subcategories");
					for (int i = 0; i < arr.length(); i++){
						//Log.d("tag", arr.getJSONObject(i).toString());
						subcategories.put(arr.getJSONObject(i).getString("id"), arr.getJSONObject(i).getString("name"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (Category.getName().equals("Culture")){
				try {
					JSONArray arr = json.getJSONObject(3).getJSONArray("subcategories");
					for (int i = 0; i < arr.length(); i++){
						//Log.d("tag", arr.getJSONObject(i).toString());
						subcategories.put(arr.getJSONObject(i).getString("id"), arr.getJSONObject(i).getString("name"));
					}
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
            List<String> list = new ArrayList<String>(subcategories.values());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    SubcategoriesListActivity.this, 
                    android.R.layout.simple_list_item_1,
                    list);

            lv.setAdapter(arrayAdapter); 
		}
		
	}

}

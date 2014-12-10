package com.articles;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

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

public class ArticlesListActivity extends Activity {

	private static final String LIST_OF_ARTICLES = "http://figaro.service.yagasp.com/article/header/";
	private ProgressDialog pDialog;
	Map<String, String> articles = new LinkedHashMap<String, String>();
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articles_list);
		
		lv = (ListView) findViewById(R.id.listViewArticles);
		
		new ListArticle().execute();
		
		lv.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,
		    		  int position, long id) {
		        Article.setTitle(lv.getItemAtPosition(position).toString());
		        List<String> list = new ArrayList<String>(articles.keySet());
		        Article.setId(list.get(position));
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
	
	class ListArticle extends AsyncTask<String, String, String>{

		protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ArticlesListActivity.this);
            pDialog.setMessage("Start progress...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			JSONParser jParser = new JSONParser();
			JSONArray json = jParser.getJSONFromUrl(LIST_OF_ARTICLES+Subcategory.getId());
			/*try {
				Log.d("123",json.getJSONArray(1).getJSONObject(0).getString("title"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				for (int i = 0; i < json.getJSONArray(1).length(); i++){
					articles.put(json.getJSONArray(1).getJSONObject(i).getString("id"), json.getJSONArray(1).getJSONObject(i).getString("title"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
		
		protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            
            List<String> list = new ArrayList<String>(articles.values());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    ArticlesListActivity.this, 
                    android.R.layout.simple_list_item_1,
                    list);

            lv.setAdapter(arrayAdapter);
		}
		
	}

}

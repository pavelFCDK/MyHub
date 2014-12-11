package com.articles;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	ArrayList<Article> aList = new ArrayList<Article>();
	private ListView lv;
	
	public Bitmap getBitmapFromUrl(String url){
		InputStream is = null;
		try{
		    URL myFileUrl = new URL ("http://hotline.ua/img/tx/107/10771121.jpg");
			//URL myFileUrl = new URL ("http://www.lefigaro.fr/medias/2012/02/01/d2d33eb0-4cea-11e1-b3e7-1ae1182ab543.jpg");
			HttpURLConnection conn =
		      (HttpURLConnection) myFileUrl.openConnection();
		    conn.setDoInput(true);
		    conn.connect();
		 
		   is = conn.getInputStream();
	    } catch(Exception e){System.err.println(e.toString());};
		return BitmapFactory.decodeStream(is);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articles_list);
		
		lv = (ListView) findViewById(R.id.listViewArticles);
		
		new ListArticle().execute();
		
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
			String img = null;
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
					Article a = new Article();
					a.setTitle(json.getJSONArray(1).getJSONObject(i).getString("title"));
					a.setId(json.getJSONArray(1).getJSONObject(i).getString("id"));
					a.setSubtilte(json.getJSONArray(1).getJSONObject(i).getString("subtitle"));
					img = json.getJSONArray(1).getJSONObject(0).getJSONObject("thumb").getString("link").split("http://")[2];
					a.setImage("http://".concat(img));
					a.setBitmap(getBitmapFromUrl(a.getImage()));
					aList.add(a);
				}
				System.out.println(aList.get(0).getImage());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
		
		protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            
            ArrayList<String> list = new ArrayList<String>(articles.values());
            /*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    ArticlesListActivity.this, 
                    android.R.layout.simple_list_item_1,
                    list);*/
            ArticleAdapter ad = new ArticleAdapter(ArticlesListActivity.this, aList);
            lv.setAdapter(ad);
           // lv.setAdapter(arrayAdapter);
		}
		
	}

}

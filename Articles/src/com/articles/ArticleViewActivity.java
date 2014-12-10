package com.articles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ArticleViewActivity extends Activity {
	
	private static final String ARTICLE_URL = "http://figaro.service.yagasp.com/article/";
	private ProgressDialog pDialog;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_view);
		
		tv = (TextView)findViewById(R.id.textViewArticleContent);
		
		new ArticleView().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.article_view, menu);
		return true;
	}
	
	class ArticleView extends AsyncTask<String, String, String>{

		protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ArticleViewActivity.this);
            pDialog.setMessage("Start progress...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONParser jParser = new JSONParser();
			//List<NameValuePair> param = new ArrayList<NameValuePair>();
			//JSONArray json = jParser.getJSONFromUrl(ARTICLE_URL+Article.getId());
			JSONObject json = jParser.getJSONOBJfromURL(ARTICLE_URL+Article.getId());
			return null;
		}
		
		protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            tv.setText("12345");
		}
		
	}

}

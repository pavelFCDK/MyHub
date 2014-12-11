package com.atask;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.articles.Article;
import com.articles.JSONParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

public class ArticleView extends AsyncTask<String, String, String>{
	
	private static final String ARTICLE_URL = "http://figaro.service.yagasp.com/article/";
	private Context con;
	private ProgressDialog pDialog;
	private TextView tv;
	
	public ArticleView(Context c, ProgressDialog pd, TextView tv){
		con = c;
		pDialog = pd;
		this.tv = tv;
	}
	
	protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(con);
        pDialog.setMessage("Start progress...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		JSONParser jParser = new JSONParser();
		
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		JSONObject json = jParser.makeHttpRequest(
				ARTICLE_URL+Article.getCurrentId(), "GET", param);
		System.out.println(ARTICLE_URL+Article.getCurrentId());
		try {
			String a = new String(json.getString("author").getBytes("ISO-8859-1"), "UTF-8");
			Article.setAuthor(a);
			String c = new String(json.getString("content").getBytes("ISO-8859-1"), "UTF-8");
			Article.setContent(c);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected void onPostExecute(String file_url) {
        // dismiss the dialog once product deleted
        pDialog.dismiss();
        tv.setText("Title: ".concat(Article.getCurrentTitle().concat("\n")));
        tv.setText(tv.getText().toString().concat("Author: ".concat(Article.getAuthor().concat("\n"))));
        tv.setText(tv.getText().toString().concat(Html.fromHtml(Article.getContent()).toString().concat("\n")));
	}

}

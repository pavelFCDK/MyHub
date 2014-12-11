package com.atask;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.articles.JSONParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListCategory extends AsyncTask<String, String, String>{

	private ProgressDialog pDialog;
	private ListView lv;
	private Context con;
	private ArrayList<String> list;
	private static final String LIST_OF_ALL_CATEGORIES_URL = "http://figaro.service.yagasp.com/article/categories";
	
	public ListCategory(Context c, ListView lv, ArrayList<String> al, ProgressDialog pg){
		con = c;
		this.lv = lv;
		list = al;
		pDialog = pg;
	}
	JSONObject c = null;
	
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(con);
        pDialog.setMessage("Start progress...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
	
	
	 protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    con, 
                    android.R.layout.simple_list_item_1,
                    list);

            lv.setAdapter(arrayAdapter); 
	 }

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
				JSONParser jParser = new JSONParser();
				JSONArray json = jParser.getJSONFromUrl(LIST_OF_ALL_CATEGORIES_URL);
				for (int i = 0; i < json.length(); i++){
					try {
						list.add(json.getJSONObject(i).getString("category"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return null;
	}

}

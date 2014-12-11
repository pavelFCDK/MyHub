package com.atask;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import com.articles.Category;
import com.articles.JSONParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListSubcategory extends AsyncTask<String, String, String>{

	private static final String LIST_OF_ALL_CATEGORIES_URL = "http://figaro.service.yagasp.com/article/categories";
	private Context con;
	private ProgressDialog pDialog;
	private Map<String, String> subcategories = new LinkedHashMap<String, String>();
	private ListView lv;
	
	public ListSubcategory(Context c, ProgressDialog pd, Map m, ListView lv){
		con = c;
		pDialog = pd;
		subcategories = m;
		this.lv = lv;
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
                con, 
                android.R.layout.simple_list_item_1,
                list);

        lv.setAdapter(arrayAdapter); 
	}

}

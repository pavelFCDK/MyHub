package com.articles;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.R.drawable;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleAdapter extends BaseAdapter{
	
	Context ctx;
	LayoutInflater lInflater;
	ArrayList<Article> objects;
	
	ArticleAdapter(Context context, ArrayList<Article> products) {
	    ctx = context;
	    objects = products;
	    lInflater = (LayoutInflater) ctx
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objects.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		InputStream is = null;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.article_item, parent, false);
	    }

	    Article a = getArticle(position);

	    ((TextView) view.findViewById(R.id.textViewATitle)).setText(a.getTitle());
	    ((TextView) view.findViewById(R.id.textViewASubTitle)).setText(a.getSubtitle() + "");
	    ((ImageView) view.findViewById(R.id.imageView1)).setImageBitmap(a.getBitmap());
	    //((ImageView) view.findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_test_image);

	    return view;
	}
	
	 Article getArticle(int position) {
	    return (Article)getItem(position);
	  }
	 
	 public static Drawable LoadImageFromWebOperations(String url) {
		    try {
		        InputStream is = (InputStream) new URL(url).getContent();
		        Drawable d = Drawable.createFromStream(is, "src name");
		        return d;
		    } catch (Exception e) {
		        return null;
		    }
		} 

}

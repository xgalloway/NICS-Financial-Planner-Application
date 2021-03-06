package com.example.support;

import com.example.nics_application.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author Team 16
 *
 */

public class TransactionAdapter extends ArrayAdapter<String> {

	private Context context;
	private int resource;
	private String[] data;
	
	/**
	 * Instantiates variables
	 * 
	 * @param context Database information
	 * @param resource
	 * @param list
	 */
	
	public TransactionAdapter(Context context, int resource, String[] list) {
		super(context, resource, list);
		this.context = context;
		this.resource = resource;
		this.data = list;
	}
	
	/**
	 * Sets color of text based on position
	 * 
	 * @param position Position of view
	 * @param convertView
	 * @param paren
	 * @return The view
	 */
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = super.getView(position, convertView, parent);
		TextView textView = (TextView)v.findViewById(R.id.balanceTextView);
		String item = data[position];
		if(item.startsWith("+")) {
	    	textView.setTextColor(Color.GREEN);
	    } else if (item.startsWith("-")) {
	    	textView.setTextColor(Color.RED);
	    } else {
	    	textView.setTextColor(Color.WHITE);
	    }
			/*LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(resource, parent, false);
		    //String test = convertView.toString();
		    TextView textView = (TextView)convertView.findViewById(R.id.balanceTextView);
		    */
		    textView.setText(item);
		    
		    
		    return v;
		
		//return parent;
		
	}

}

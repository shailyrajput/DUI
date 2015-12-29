package com.scms.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dui.R;
import com.scms.CustomInterface.UpdateCommonInterface;
import com.scms.dui.NineViewActivity;
import com.scms.dui.utils.OptionUtils;


public class NineViewAdapter  extends BaseAdapter {
	private Context context;
	private	ArrayList<OptionUtils>  list;
	LayoutInflater inflater = null;
	ArrayList<String> getValue;
	public NineViewAdapter(Context acontext, ArrayList<OptionUtils> optionlist, ArrayList<String> getValue) {
		context = acontext;
		list = optionlist;
		this.getValue = getValue;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
	if(convertView == null)
	{
		holder = new ViewHolder();
		convertView = inflater.inflate(R.layout.nine_screen_list_item, null);
		holder.checbox = (CheckBox)convertView.findViewById(R.id.checbox);
		holder.main_text = (TextView)convertView.findViewById(R.id.main_text);
		holder.picker_text = (TextView)convertView.findViewById(R.id.picker_text);
		holder.lower_text = (TextView)convertView.findViewById(R.id.lower_text);
		
		convertView.setTag(holder);
	}
	else
		holder = (ViewHolder)convertView.getTag();
		holder.picker_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			/*UpdateCommonInterface commonInterface = new NineViewActivity();
			commonInterface.setAdapter();
			getValue.add("");
				*/

			}
		});
         holder.main_text.setText(list.get(position).getOptionText());
	    // holder.lower_text.setBackground(list.get(position).get("phoneId"));
		return convertView;
	}
 class ViewHolder
 {
	 CheckBox checbox;
	 TextView main_text,picker_text,lower_text;
 }
}

package com.scms.dui.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.scms.dui.ContactDUILawyer;
import com.scms.dui.Dashboard;

public class Utils {

	public static TextView txtOption1, txtOption2, txtOption3, txtOption4,
			txtOption5;
	public static RadioButton radio1, radio2, radio3, radio4, radio5;
	public static Button next, prev;
	public static TextView question_text;
	private Context mContext;

	public Utils(Context context) {
		mContext = context;
	}

	public static void alertDilogWithOkBtn(final Context context, String tittle,
			String message) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		alertDialogBuilder.setTitle(tittle);
		alertDialogBuilder.setMessage(message).setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					
					}
				});
		


		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	public static void alertDialogTwoButton(final Context context,String title, String msg) {
		AlertDialog.Builder alBuilder = new AlertDialog.Builder(context);
		alBuilder.setTitle(title);
		alBuilder
				.setMessage(msg)
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(context, Dashboard.class);
						SingletonClass.getInstance().clearAll();
						context.startActivity(intent);
						((Activity) context).finish();	
						
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alBuilder.create();
		alertDialog.show();

	}

	
	public static void alertDialogToMoveContact(final Context context,
			String title, String msg,final View view) {
		AlertDialog.Builder alBuilder = new AlertDialog.Builder(context);
		alBuilder.setTitle(title);
		alBuilder
				.setMessage(msg)
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(context, ContactDUILawyer.class);
						SingletonClass.getInstance().clearAll();
						context.startActivity(intent);
						((Activity) context).finish();
						
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
						
								if(view instanceof RadioButton)
								{
									((RadioButton) view).setChecked(true);
								}else
								if(view instanceof CheckBox)
								{
									((CheckBox) view).setChecked(false);
								}
								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alBuilder.create();
		alertDialog.show();

	}

	public JSONObject readJsonFile() {
		JSONObject jsonObj = null;
		JSONArray tempJsonArr = null;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(mContext
					.getAssets().open("questiondata.json")));

			// do reading, usually loop until end of file reading
			String mLine;
			while ((mLine = reader.readLine()) != null) {
				builder.append(mLine);
			}
			// Log.v("JSON File Data", builder.toString());
			jsonObj = new JSONObject(builder.toString());
			// tempJsonArr = new JSONArray(builder.toString());
			System.out.println("### json array in read method :"
					+ tempJsonArr.length());
			// jsonObj = tempJsonObj.getJSONObject("Data");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return jsonObj;
	}

	public String loadJSONFromAsset() {
		String json = null;
		try {
			InputStream is = mContext.getAssets().open("questiondata.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}
	
}

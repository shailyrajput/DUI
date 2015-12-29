package com.scms.dui;

import com.example.dui.R;
import com.scms.noghteh.JustifiedTextView;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TermOfUse extends Activity implements OnClickListener {

	private Button termofuse_contact;
	private JustifiedTextView term_of_use_text;

	private void init() {
		termofuse_contact = (Button) findViewById(R.id.termofuse_contact);
		term_of_use_text = (JustifiedTextView) findViewById(R.id.term_of_use_text);
		term_of_use_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		term_of_use_text.setLineSpacing(5);
		term_of_use_text.setTextColor(Color.WHITE);
		term_of_use_text.setText(getResources().getString(
				R.string.term_of_use_text));
		term_of_use_text.setAlignment(Align.LEFT);
		termofuse_contact.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.term_of_use_activity);
		init();
		setCustomActionBar();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.termofuse_contact:
			Intent intent = new Intent(TermOfUse.this, ContactDUILawyer.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}

	private void setCustomActionBar() {
		// getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		ActionBar mActionBar = getActionBar();
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mCustomView = getLayoutInflater().inflate(
				R.layout.custom_action_bar, null);
		mActionBar.setCustomView(mCustomView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		final TextView txt = (TextView) findViewById(R.id.actiontxt);
		txt.setText("Terms of Use");
		final Button backbtn = (Button) findViewById(R.id.custom_view_backbtn);
		backbtn.setVisibility(View.VISIBLE);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}
}

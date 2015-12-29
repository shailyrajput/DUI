package com.scms.dui;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.dui.R;
import com.scms.utility.MyUIApplication;

public class Dashboard extends Activity implements OnClickListener {

	private Button dui_ques_btn, dui_contact_btn, dui_faq_btn, dui_term_btn;
	int deviceHeight,deviceWidth;
	private TextView questionnaire_text,contact_text,frequently_text,use_text;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
		getDeviceParams();
		init();
        setViews();
		setCustomActionBar();
	}

	private void setViews() {
		questionnaire_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, MyUIApplication.determineTextSize(questionnaire_text.getTypeface(),(int) (0.35f * .1f * deviceHeight)));
		contact_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, MyUIApplication.determineTextSize(contact_text.getTypeface(),(int) (0.35f * .1f * deviceHeight)));
		frequently_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, MyUIApplication.determineTextSize(frequently_text.getTypeface(),(int) (.35f * .1f * deviceHeight)));
		use_text.setTextSize(TypedValue.COMPLEX_UNIT_PX, MyUIApplication.determineTextSize(use_text.getTypeface(),(int) (.35f * .1f * deviceHeight)));
		
	}

	private void getDeviceParams() {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		deviceHeight = displaymetrics.heightPixels;
		deviceWidth = displaymetrics.widthPixels;
	}

	private void init() {
		dui_ques_btn = (Button) findViewById(R.id.dui_question_btn);
		dui_contact_btn = (Button) findViewById(R.id.dui_contact_btn);
		dui_faq_btn = (Button) findViewById(R.id.dui_faq_btn);
		dui_term_btn = (Button) findViewById(R.id.dui_term_con_btn);

		questionnaire_text = (TextView) findViewById(R.id.questionnaire_text);
		contact_text = (TextView) findViewById(R.id.contact_text);
		frequently_text = (TextView) findViewById(R.id.frequently_text);
		use_text = (TextView) findViewById(R.id.use_text);

		dui_ques_btn.setOnClickListener(this);
		dui_contact_btn.setOnClickListener(this);
		dui_faq_btn.setOnClickListener(this);
		dui_term_btn.setOnClickListener(this);

	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dui_contact_btn:
			Intent contactIntent = new Intent(Dashboard.this,ContactDUILawyer.class);
			startActivity(contactIntent);

			break;
		case R.id.dui_faq_btn:
			Intent faqIntent = new Intent(Dashboard.this, FAQActivity.class);
			startActivity(faqIntent);
			break;

		case R.id.dui_term_con_btn:
			Intent termIntent = new Intent(Dashboard.this, TermOfUse.class);
			startActivity(termIntent);
			break;
		case R.id.dui_question_btn:
			Intent quesIntent = new Intent(Dashboard.this, DuiQuestion.class);
			startActivity(quesIntent);
			finish();
			break;

		default:
			break;
		}

	}

	private void setCustomActionBar() {
	
		getActionBar().setHomeButtonEnabled(true);
		ActionBar mActionBar = getActionBar();
		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mCustomView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
		mActionBar.setCustomView(mCustomView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		final TextView txt = (TextView) findViewById(R.id.actiontxt);
		txt.setText("Fight Your DUI Charge");
		// final Button backbtn = (Button)
		// findViewById(R.id.custom_view_backbtn);
		// backbtn.setVisibility(View.VISIBLE);
		// backbtn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// finish();
		// }
		// });
		mActionBar.setDisplayShowCustomEnabled(true);

	}

}

package com.scms.dui;

import com.example.dui.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContactDUILawyer extends Activity implements OnClickListener {
	private Button backbtn, contactbtn;
	private TextView emailTxt, contact_toll_no_txt, contact_ottawa_number_txt,
			contact_toronoto_number_txt, contact_montreal_num_txt,
			contact_website;
	String tollno, ottawano, tornotono, montrealno, websiteURL;

	private void init() {
		// backbtn = (Button) findViewById(R.id.contact_backbtn);
		contactbtn = (Button) findViewById(R.id.contact_form_btn);
		emailTxt = (TextView) findViewById(R.id.contact_email_txt);
		contact_toll_no_txt = (TextView) findViewById(R.id.contact_toll_no_txt);
		contact_ottawa_number_txt = (TextView) findViewById(R.id.contact_ottawa_number_txt);
		contact_toronoto_number_txt = (TextView) findViewById(R.id.contact_toronoto_number_txt);
		contact_montreal_num_txt = (TextView) findViewById(R.id.contact_montreal_num_txt);
		contact_website = (TextView) findViewById(R.id.contact_website);
		contact_toll_no_txt.setOnClickListener(this);
		contact_ottawa_number_txt.setOnClickListener(this);
		contact_toronoto_number_txt.setOnClickListener(this);
		contact_montreal_num_txt.setOnClickListener(this);
		emailTxt.setOnClickListener(this);

		contactbtn.setOnClickListener(this);
		// backbtn.setOnClickListener(this);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_dui_activity);
		init();
		setCustomActionBar();

		// WebView webView = (WebView) findViewById(R.id.webView1);
		// webView.loadData(
		// String.format(htmlText, R.string.contact_details_text),
		// "text/html", "utf-8");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// case R.id.contact_backbtn:
		// finish();
		// break;
		case R.id.contact_form_btn:

			Intent nextActivity = new Intent(this, ContactForm.class);
			startActivity(nextActivity);
			// push from bottom to top
			overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
			break;
		case R.id.contact_website:
			websiteURL = contact_website.getText().toString();
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(websiteURL));
			startActivity(i);
			break;
		case R.id.contact_email_txt:

			break;
		case R.id.contact_toll_no_txt:
			tollno = contact_toll_no_txt.getText().toString();
			callDialer(tollno);
			break;
		case R.id.contact_ottawa_number_txt:
			ottawano = contact_ottawa_number_txt.getText().toString();
			callDialer(ottawano);
			break;
		case R.id.contact_toronoto_number_txt:
			tornotono = contact_toronoto_number_txt.getText().toString();
			callDialer(tornotono);
			break;
		case R.id.contact_montreal_num_txt:
			montrealno = contact_montreal_num_txt.getText().toString();
			callDialer(montrealno);
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
		txt.setText("Contact a DUI Lawyer");
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

	private void callDialer(String number) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:" + number));
		startActivity(intent);
	}

}

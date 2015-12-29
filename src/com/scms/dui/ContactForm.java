package com.scms.dui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dui.R;
import com.scms.dui.webservice.Utility;
import com.scms.dui.webservice.Webservice;

public class ContactForm extends Activity implements OnClickListener {
	private Button cancelbtn, submitbtn;
	private EditText edName, edEmail, edPhoneNo, edDesc;
	private String name, email, phoneNo, description;

	private void init() {
		edName = (EditText) findViewById(R.id.contact_name);
		edEmail = (EditText) findViewById(R.id.contact_emailid);
		edPhoneNo = (EditText) findViewById(R.id.contact_phone_no);
		edDesc = (EditText) findViewById(R.id.contact_desc);
		cancelbtn = (Button) findViewById(R.id.cont_form_cancel_btn);
		submitbtn = (Button) findViewById(R.id.cont_form_submit_btn);
		cancelbtn.setOnClickListener(this);
		submitbtn.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_form_activity);
		init();
		setCustomActionBar();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cont_form_cancel_btn:
			finish();
			// push from top to bottom
			overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
			break;
		case R.id.cont_form_submit_btn:

			submitAction();
			// finish();
			// push from top to bottom
			// overridePendingTransition(R.anim.push_down_in,
			// R.anim.push_down_out);
			break;

		default:
			break;
		}

	}

	private void submitAction() {
		/*
		 * get Values.
		 */
		name = edName.getText().toString();
		email = edEmail.getText().toString();
		phoneNo = edPhoneNo.getText().toString();
		description = edDesc.getText().toString();
		final List<org.apache.http.NameValuePair> nameValuePairs = new ArrayList<org.apache.http.NameValuePair>();
		if (name != null) {
			if (name.length() != 0) {
				nameValuePairs.add(new BasicNameValuePair("name", name));
				System.out.println("=== himani contact name :" + name);

			}

		}
		if (email != null) {
			if (email.length() != 0) {
				nameValuePairs.add(new BasicNameValuePair("email", email));
				System.out.println("=== himani contact email id  :" + email);

			}
		}

		if (phoneNo != null) {
			if (phoneNo.length() != 0) {
				nameValuePairs.add(new BasicNameValuePair("mobile", phoneNo));
				System.out.println("=== himani contact phone number  :"
						+ phoneNo);

			}
		}

		if (description != null) {
			if (description.length() != 0) {
				nameValuePairs.add(new BasicNameValuePair("description",
						description));
				System.out.println("=== himani contact description :"
						+ description);

			}
		}
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
			}

		};

		new Thread() {
			public void run() {
				HttpResponse httpResponse = Utility.postDataOnUrl(
						Webservice.SEND_MAIL, nameValuePairs);
				try {
					String response = EntityUtils.toString(httpResponse
							.getEntity());
					System.out
							.println("=== himani contact webservice response is :"
									+ response);

				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);

			};

		}.start();

	}

	private void setCustomActionBar() {
		// getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		ActionBar mActionBar = getActionBar();

		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		// mActionBar.setStackedBackgroundDrawable(new ColorDrawable(
		// Color.TRANSPARENT));
		// mActionBar.setSplitBackgroundDrawable(new ColorDrawable(Color
		// .parseColor("#330000ff")));

		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mCustomView = getLayoutInflater().inflate(
				R.layout.custom_action_bar, null);

		mActionBar.setCustomView(mCustomView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.custom_view_relative);
		// relativeLayout.setBackgroundColor(99000000);
		relativeLayout.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#D9035cab")));

		final TextView txt = (TextView) findViewById(R.id.actiontxt);
		txt.setVisibility(View.VISIBLE);
		// txt.setBackgroundDrawable(new ColorDrawable(Color
		// .parseColor("#A6000000")));
		txt.setText("Contact a DUI Lawyer");
		final Button backbtn = (Button) findViewById(R.id.custom_view_backbtn);
		backbtn.setVisibility(View.VISIBLE);
		backbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// finish();
			}
		});

		mActionBar.setDisplayShowCustomEnabled(true);

	}
}

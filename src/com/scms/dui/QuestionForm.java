package com.scms.dui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.dui.R;
import com.scms.dui.utils.Utils;
import com.scms.utility.Constant;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionForm extends Activity implements OnClickListener {
	private EditText edName, edEmail;
	private Button nextbtn;
	private String name, email;
	private Context mContext;
	private String token = "QuestionFrom";

	private void init() {
		mContext = QuestionForm.this;
		edName = (EditText) findViewById(R.id.question_form_name);
		edEmail = (EditText) findViewById(R.id.question_form_email);
		nextbtn = (Button) findViewById(R.id.question_form_nextbtn);
		nextbtn.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_form_activity);
		setCustomActionBar();
		init();
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
		txt.setText("DUI Questionnaire");
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.question_form_nextbtn:
			name = edName.getText().toString().trim();
			email = edEmail.getText().toString().trim();
			Constant.name = name;
			Constant.email = email;
			if (name.length() == 0) {
				Utils.alertDilogWithOkBtn(mContext, "Attention!",
						"Please provide your name.");

			} else if (email.length() != 0) {
				if (!validateEmail(email)) {
					Utils.alertDilogWithOkBtn(mContext, "Attention!",
							"Please enter a valid email address.");
				} else {
					// Intent intent = new Intent(QuestionForm.this,
					// QuestionOne.class);
					Intent intent = new Intent(QuestionForm.this,
							SingleChoiceFirstActivity.class);
					startActivity(intent);

				}
			} else {
				// Intent intent = new Intent(QuestionForm.this,
				// QuestionOne.class);
				Intent intent = new Intent(QuestionForm.this,
						SingleChoiceFirstActivity.class);
				intent.putExtra("token", token);
				startActivity(intent);
				finish();
				overridePendingTransition(0, 0);
			}
			break;
		default:
			break;
		}
	}

	protected boolean validateEmail(String email) {
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}

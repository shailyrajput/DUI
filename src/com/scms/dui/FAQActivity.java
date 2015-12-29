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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FAQActivity extends Activity implements OnClickListener {
	private Button backbtn;
	private TextView text;
	private JustifiedTextView anstext1, anstext2, anstext3, anstext4, anstext5,
			anstext6;

	private void init() {
		// backbtn = (Button) findViewById(R.id.faq_backbtn);
		// backbtn.setOnClickListener(this);
		text = (TextView) findViewById(R.id.faq_seven_ans);
		anstext1 = (JustifiedTextView) findViewById(R.id.faq_ans_one_txt);
		anstext1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		anstext1.setLineSpacing(5);
		anstext1.setTextColor(Color.WHITE);
		anstext1.setText(getResources().getString(R.string.faq_answer_one));
		anstext1.setAlignment(Align.LEFT);

		anstext2 = (JustifiedTextView) findViewById(R.id.faq_ans_two_txt);
		anstext2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		anstext2.setLineSpacing(5);
		anstext2.setTextColor(Color.WHITE);
		anstext2.setText(getResources().getString(R.string.faq_answer_two));
		anstext2.setAlignment(Align.LEFT);

		anstext3 = (JustifiedTextView) findViewById(R.id.faq_ans_three_txt);
		anstext3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		anstext3.setLineSpacing(5);
		anstext3.setTextColor(Color.WHITE);
		anstext3.setText(getResources().getString(R.string.faq_answer_three));
		anstext3.setAlignment(Align.LEFT);

		anstext4 = (JustifiedTextView) findViewById(R.id.faq_ans_four_txt);
		anstext4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		anstext4.setLineSpacing(5);
		anstext4.setTextColor(Color.WHITE);
		anstext4.setText(getResources().getString(R.string.faq_answer_four));
		anstext4.setAlignment(Align.LEFT);

		anstext5 = (JustifiedTextView) findViewById(R.id.faq_ans_five_txt);
		anstext5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		anstext5.setLineSpacing(5);
		anstext5.setTextColor(Color.WHITE);
		anstext5.setText(getResources().getString(R.string.faq_answer_five));
		anstext5.setAlignment(Align.LEFT);

		anstext6 = (JustifiedTextView) findViewById(R.id.faq_ans_six_txt);
		anstext6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
		anstext6.setLineSpacing(5);
		anstext6.setTextColor(Color.WHITE);
		anstext6.setText(getResources().getString(R.string.faq_answer_six));
		anstext6.setAlignment(Align.LEFT);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faq_dui_activity);
		init();
		setCustomActionBar();
		SpannableString ss = new SpannableString(getResources().getString(
				R.string.faq_answer_seven));
		ClickableSpan clickableSpan = new ClickableSpan() {
			@Override
			public void onClick(View textView) {
				Intent anotheract = new Intent(FAQActivity.this,
						ContactDUILawyer.class);
				startActivity(anotheract);
				finish();
			}
		};

		ClickableSpan clickableSpan1 = new ClickableSpan() {
			@Override
			public void onClick(View textView) {
				Intent anotheract = new Intent(FAQActivity.this,
						ContactDUILawyer.class);
				startActivity(anotheract);
				finish();
			}
		};

		ss.setSpan(clickableSpan, 0, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(clickableSpan1, 39, 71, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		text.setText(ss);
		text.setMovementMethod(LinkMovementMethod.getInstance());

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.faq_backbtn:
		// finish();

		// break;

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
		txt.setText("F.A.Q");
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

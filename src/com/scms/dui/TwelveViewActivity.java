package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.Utils;

public class TwelveViewActivity extends Activity implements OnClickListener {

	private Activity activity = TwelveViewActivity.this;

	private RadioButton thirteen1_rd2, thirteen1_rd1;
	private RadioGroup radiogroup;
	private TextView thirteen_question, question_thirdteen_question_no,
			thirteen_Sub_question, thirteen_question2,
			thirteen_first_option_text1;
	private String question = "Question";
	private Button prev_btn, next_btn;
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private AlertDialog alertDialog;
	private int postion;
	private String activtiy_no,back_activity;
	private SingletonClass singletonClass;
	private String main_question, answer, subquestion, subanswer, desc1, desc2,
			desc3, desc4, desc5, descQ1, descQ2, descQ3, descQ4, descQ5,
			checked;

	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;
	private int savePosition;
	private HashMap<Object, Object> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description_with_single_choice);
		setCustomActionBar();
		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;
		postion = getIntent().getIntExtra("Position", 0);
		back_activity = getIntent().getStringExtra("back_activity");
		savePosition = getIntent().getIntExtra("saveposition", 0);

		map= new HashMap<Object, Object>();
		init();
		assignClicks();
	}

	private void setCustomActionBar() {
		// getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		ActionBar mActionBar = getActionBar();
		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mCustomView = getLayoutInflater().inflate(	R.layout.custom_action_bar, null);
		mActionBar.setCustomView(mCustomView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		final TextView txt = (TextView) findViewById(R.id.actiontxt);
		txt.setText("DUI Questionnaire");
		final Button backbtn = (Button) findViewById(R.id.custom_view_backbtn);
		backbtn.setVisibility(View.VISIBLE);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.alertDialogTwoButton(activity, "Attention!",
						"Going back will close this questionaire and all answers will be deleted.");
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	private void init() {

		thirteen1_rd1 = (RadioButton) findViewById(R.id.thirteen1_rd1);
		thirteen1_rd2 = (RadioButton) findViewById(R.id.thirteen1_rd2);

		radiogroup = (RadioGroup) findViewById(R.id.radiogroup);

		saveDataList = new ArrayList<SaveQuestionDataUtils>();
		questionNo = Integer.parseInt(questionData.get(postion).getQuesNo());
		main_question = questionData.get(postion).getQues();

		question_thirdteen_question_no = (TextView) findViewById(R.id.question_thirdteen_question_no);
		thirteen_question = (TextView) findViewById(R.id.thirteen_question);
		thirteen_Sub_question = (TextView) findViewById(R.id.thirteen_Sub_question);
		thirteen_first_option_text1 = (TextView) findViewById(R.id.thirteen_first_option_text1);

		next_btn = (Button) findViewById(R.id.next_btn);
		prev_btn = (Button) findViewById(R.id.prev_btn);

		System.out.println(" === questionData :"
				+ (questionData.get(postion).getQuesNo()));
		question_thirdteen_question_no.setText(question + " "
				+ (savePosition + 1));
		thirteen_question.setText((questionData.get(postion).getQues()));

		thirteen_Sub_question.setText((questionData.get(postion)
				.getSecondQues()));

		subquestion = questionData.get(postion).getSecondQues();
		sub_optionlist = questionData.get(postion).getSecondOptionArr();

		System.out.println(" === sub_optionlist :" + sub_optionlist.size());

		thirteen1_rd1.setText(sub_optionlist.get(0).getText());
		thirteen1_rd2.setText(sub_optionlist.get(1).getText());
		
		conditionCheck();

	}

	private void conditionCheck() {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		map = singletonClass
				.getMap()
				.get(main_question);

		if (map != null) {
			back_activity = "" + map.get("back_activity");
			if (map.get("desc_Value") != null) {
				thirteen_first_option_text1.setText("" + map.get("desc_Value"));
			}
			if (map.get("subanswer_Value") != null) {
				if (sub_optionlist.get(0).getText().equalsIgnoreCase("" + map.get("subanswer_Value"))) {
					thirteen1_rd1.setChecked(true);
				}
				if (sub_optionlist.get(1).getText().equalsIgnoreCase("" + map.get("subanswer_Value"))) {
					thirteen1_rd2.setChecked(true);
				}

			}
			desc1 = "" + map.get("desc_Value");

			subanswer = "" + map.get("subanswer_Value");

			activtiy_no = "" + map.get("activtiy_no");

		}
		
		

		if (back_activity.equalsIgnoreCase("9"))
			if (singletonClass
					.getMap()
					.get("When the police told you at the roadside that you had the right to speak to a lawyer "
							+ "was he\bshe reading it, or telling it to you from memory?") != null) {
				singletonClass
						.getMap()
						.remove("When the police told you at the roadside that you had the right to speak to a lawyer "
								+ "was he\bshe reading it, or telling it to you from memory?");
			}
	}

	private void assignClicks() {

		next_btn.setOnClickListener(this);
		prev_btn.setOnClickListener(this);
		thirteen_first_option_text1.setOnClickListener(this);

		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				System.out
						.println("SingleChoiceQuestion.init().new OnCheckedChangeListener() {...}.onCheckedChanged()");
				int select_id = radiogroup.getCheckedRadioButtonId();
				System.out.println("=== select_id :" + select_id);

				if (select_id == R.id.thirteen1_rd1) {
					Log.e("thirteen1_rd1", "matched");

					subanswer = passRadioData(thirteen1_rd1.getText());
					activtiy_no = passRadioActivity(sub_optionlist.get(0)
							.getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
				}
				if (select_id == R.id.thirteen1_rd2) {
					Log.e("thirteen1_rd2", "matched");

					subanswer = passRadioData(thirteen1_rd2.getText());
					activtiy_no = passRadioActivity(sub_optionlist.get(1)
							.getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
				}

			}

		});

	}

	public String passRadioActivity(String radio_activity) {

		return radio_activity;

	}

	public String passRadioData(CharSequence radiotext) {

		return radiotext.toString();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_btn:
			moveActivity();
			break;

		case R.id.prev_btn:
			
			backMove();

			break;

		case R.id.thirteen_first_option_text1:
			openNumberPicker(R.id.thirteen_first_option_text1);
			break;

		default:
			break;
		}

	}

	private void backMove() {
		if (back_activity.equalsIgnoreCase("9")) {
			savePosition = savePosition - 1;
			postion = Integer.parseInt(back_activity);
			System.out.println("===back_activity" + back_activity);
			Intent intent = new Intent(activity, NineViewActivity.class);
			System.out.println(" ==== savePosition" + savePosition);
			intent.putExtra("saveposition", savePosition);
			intent.putExtra("Position", postion - 1);
			startActivity(intent);
		}

		if (back_activity.equalsIgnoreCase("11")) {
			savePosition = savePosition - 1;
			postion = Integer.parseInt(back_activity);
			System.out.println("===back_activity" + back_activity);
			Intent intent = new Intent(activity, SingleChoiceElevenActivity.class);
			System.out.println(" ==== savePosition" + savePosition);
			intent.putExtra("saveposition", savePosition);
			intent.putExtra("Position", postion - 1);
			startActivity(intent);
		}

	}

	private void openNumberPicker(final int id) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				activity);
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.picker, null);

		TimePicker timePicker = (TimePicker) layout
				.findViewById(R.id.timePicker);
		timePicker.setVisibility(View.GONE);
		final NumberPicker numberPicker = (NumberPicker) layout
				.findViewById(R.id.numberPicker);
		numberPicker.setVisibility(View.VISIBLE);
		numberPicker.setMaxValue(60);
		numberPicker.setMinValue(0);
		Button done = (Button) layout.findViewById(R.id.done);
		Button cancel = (Button) layout.findViewById(R.id.cancel);
		timePicker.setIs24HourView(true);

		alertDialogBuilder.setView(layout);
		alertDialog = alertDialogBuilder.create();
		alertDialog.show();

		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = "" + numberPicker.getValue();
				desc1 = number;
				thirteen_first_option_text1.setText(number);
				alertDialog.dismiss();
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onCancel();
			}
		});

	}

	protected void onCancel() {
		alertDialog.dismiss();
	}

	private void moveActivity() {
		SaveQuestionDataUtils savedataUtils = new SaveQuestionDataUtils();

		savedataUtils.setQuestionNo(questionNo);
		savedataUtils.setDesc1(desc1);
		savedataUtils.setQuestion(main_question);
		savedataUtils.setSubanswer(subanswer);
		savedataUtils.setSubquestion(subquestion);
		saveDataList.add(savedataUtils);
//		singletonClass.addList(savePosition, saveDataList);
		
		map.put("back_activity", back_activity);
		map.put("desc_Value", desc1);
		map.put("activtiy_no",activtiy_no );
		map.put("sub_question", subquestion);
		
		map.put("subanswer_Value", subanswer);
		
		singletonClass.addMap(main_question, map);
		System.out.println("=== In twelve Activity Arraylist size is :"+ singletonClass.getList().size());


		if (activtiy_no != null && thirteen_first_option_text1.getText().length() != 0 ) {

			if (activtiy_no.equalsIgnoreCase("13")) {
				postion = Integer.parseInt(activtiy_no);
				System.out.println(" === postion  :" + postion);
				Intent intent = new Intent(activity, ThirteenViewActivty.class);
				intent.putExtra("Position", postion - 1);
				intent.putExtra("back_activity","12");
				intent.putExtra("saveposition", savePosition + 1);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}

		} else {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(TwelveViewActivity.this, "Attention!",
					"Please answer All questions.");
		}

	}

	@Override
	public void onBackPressed() {
	}

}

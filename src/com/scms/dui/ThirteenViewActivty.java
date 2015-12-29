package com.scms.dui;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.dui.R;
import com.scms.dui.utils.OptionUtils;
import com.scms.dui.utils.QuestionUtils;
import com.scms.dui.utils.SaveQuestionDataUtils;
import com.scms.dui.utils.SingletonClass;
import com.scms.dui.utils.SubOptionUtils;
import com.scms.dui.utils.Utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ThirteenViewActivty extends Activity implements OnClickListener {
	private Activity activity = ThirteenViewActivty.this;

	private LinearLayout thirteen_sub_question_layout;
	private RadioButton thirteen1_rd2, thirteen1_rd1;
	private RadioGroup radiogroup;
	private TextView thirteen_question, question_thirdteen_question_no,
			thirteen_Sub_question, thirteen_question2,
			thirteen_first_option_text1;
	private SingletonClass singletonClass;

	private String question = "Question";
	private Button prev_btn, next_btn;
	private ArrayList<OptionUtils> optionlist;
	private ArrayList<SubOptionUtils> sub_optionlist;
	private ArrayList<QuestionUtils> questionData;
	private AlertDialog alertDialog;
	private int postion;
	private String activtiy_no,back_activity;
	private int savePosition;

	private String main_question, answer, subquestion, subanswer, desc1, desc2,
			desc3, desc4, desc5, descQ1, descQ2, descQ3, descQ4, descQ5,
			checked;

	private int questionNo;
	private ArrayList<SaveQuestionDataUtils> saveDataList;

	private HashMap<Object, Object> map;
	private boolean getchecked=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_thirteen_view);
		setCustomActionBar();
		optionlist = new ArrayList<OptionUtils>();
		singletonClass = SingletonClass.getInstance();
		questionData = SplashScreen.QuestionArrList;
		postion = getIntent().getIntExtra("Position", 0);
		savePosition = getIntent().getIntExtra("saveposition", 0);
		
		back_activity = getIntent().getStringExtra("back_activity");
		map = new HashMap<Object, Object>();
		System.out.println("=== himani save position in 13 Activity :"+ savePosition);
		init();
		assignClicks();
	}

	private void setCustomActionBar() {
		// getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		ActionBar mActionBar = getActionBar();
		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mCustomView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
		mActionBar.setCustomView(mCustomView);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		final TextView txt = (TextView) findViewById(R.id.actiontxt);
		txt.setText("DUI Questionnaire");
		final Button backbtn = (Button) findViewById(R.id.custom_view_backbtn);
		backbtn.setVisibility(View.VISIBLE);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.alertDialogTwoButton(activity, "Attention!","Going back will close this questionaire and all answers will be deleted.");
			}
		});
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	

	private void init() {
		thirteen_sub_question_layout = (LinearLayout) findViewById(R.id.thirteen_sub_question_layout);

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

		System.out.println(" === questionData :"+ (questionData.get(postion).getQuesNo()));
		question_thirdteen_question_no.setText(question + " "+ (savePosition+1));
		thirteen_question.setText((questionData.get(postion).getQues()));

		thirteen_Sub_question.setText((questionData.get(postion).getSecondQues()));
		optionlist = questionData.get(postion).getOptionArr();

		sub_optionlist = optionlist.get(1).getSubQuesOptionArr();

		System.out.println(" === optionlist :" + optionlist.size());
		System.out.println(" === sub_optionlist :" + sub_optionlist.size());

		thirteen_Sub_question.setText(optionlist.get(1).getSubQues());
		subquestion = thirteen_Sub_question.getText().toString();

		thirteen1_rd1.setText(optionlist.get(0).getOptionText());
		thirteen1_rd2.setText(optionlist.get(1).getOptionText());
		
		conditionCheck();

	}

	private void conditionCheck() {

		HashMap<Object, Object> map = new HashMap<Object, Object>();

		map = singletonClass.getMap().get(main_question);

		if (map != null) {
			back_activity = "" + map.get("back_activity");

                System.out.println("===map::"+map);
				if (optionlist.get(0).getOptionText().equalsIgnoreCase("" + map.get("subanswer_Value"))) {
					thirteen1_rd1.setChecked(true);
				}
				if (optionlist.get(1).getOptionText().equalsIgnoreCase("" + map.get("subanswer_Value"))) {

					thirteen1_rd2.setChecked(true);
					thirteen_sub_question_layout.setVisibility(View.VISIBLE);
					if (map.get("desc_Value") != null) {
						thirteen_first_option_text1.setText(""+ map.get("desc_Value"));
					}
				}
				


			desc1 = "" + map.get("desc_Value");

			subanswer = "" + map.get("subanswer_Value");

			activtiy_no = "" + map.get("activtiy_no");
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

					thirteen_sub_question_layout.setVisibility(View.GONE);
					getchecked=false;
					subanswer = passRadioData(thirteen1_rd1.getText());
					activtiy_no = passRadioActivity(optionlist.get(0).getActivity());
					System.out.println(" === activtiy_no :" + activtiy_no);
				}
				if (select_id == R.id.thirteen1_rd2) {
					Log.e("thirteen1_rd2", "matched");
                    getchecked=false;
					thirteen_sub_question_layout.setVisibility(View.VISIBLE);
					

					subanswer = passRadioData(thirteen1_rd2.getText());
					
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
		savePosition = savePosition - 1;
		postion = Integer.parseInt(back_activity);
		System.out.println("===back_activity" + back_activity);
		Intent intent = new Intent(activity, TwelveViewActivity.class);
		System.out.println(" ==== savePosition" + savePosition);
		intent.putExtra("saveposition", savePosition);
		intent.putExtra("Position", postion - 1);
		startActivity(intent);

	}

	private void openNumberPicker(final int id) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.picker, null);

		TimePicker timePicker = (TimePicker) layout.findViewById(R.id.timePicker);
		timePicker.setVisibility(View.GONE);
		final NumberPicker numberPicker = (NumberPicker) layout.findViewById(R.id.numberPicker);
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
		savedataUtils.setAnswer(subanswer);
		savedataUtils.setSubquestion(subquestion);
		saveDataList.add(savedataUtils);
	//	singletonClass.addList(savePosition, saveDataList);
		
		map.put("desc_Value", desc1);
		map.put("sub_question", subquestion);
		map.put("back_activity", back_activity);
		map.put("activtiy_no", activtiy_no);
		map.put("subanswer_Value", subanswer);
		
		singletonClass.addMap(main_question, map);
		if(thirteen_first_option_text1.getText().length()!=0)
		{	
			activtiy_no = passRadioActivity(sub_optionlist.get(0).getActivity());
		}
		
		System.out.println("");
		if (activtiy_no != null ) {

			if (activtiy_no.equalsIgnoreCase("14")) {
				postion = Integer.parseInt(activtiy_no);
				System.out.println(" === postion  :" + postion);
				Intent intent = new Intent(activity,DoubleSingleChoicefourteenActivity.class);
				intent.putExtra("Position", postion - 1);
				intent.putExtra("back_activity","13");
				intent.putExtra("saveposition", savePosition + 1);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();

			}

		} else {
			Log.e("Alert", " show");
			Utils.alertDilogWithOkBtn(ThirteenViewActivty.this, "Attention!",
					"Please answer All questions.");
		}

	}

	@Override
	public void onBackPressed() {
	}

}
